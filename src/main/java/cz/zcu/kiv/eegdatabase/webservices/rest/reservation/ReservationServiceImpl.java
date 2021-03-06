/*******************************************************************************
 * This file is part of the EEG-database project
 * 
 *   ==========================================
 *  
 *   Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *  
 *  ***********************************************************************************************************************
 *  
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *   the License. You may obtain a copy of the License at
 *  
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  
 *   Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *   an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *   specific language governing permissions and limitations under the License.
 *  
 *  ***********************************************************************************************************************
 *  
 *   ReservationServiceImpl.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.webservices.rest.reservation;

import cz.zcu.kiv.eegdatabase.data.dao.PersonDao;
import cz.zcu.kiv.eegdatabase.data.dao.ResearchGroupDao;
import cz.zcu.kiv.eegdatabase.data.dao.ReservationDao;
import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroup;
import cz.zcu.kiv.eegdatabase.data.pojo.Reservation;
import cz.zcu.kiv.eegdatabase.webservices.rest.common.exception.RestServiceException;
import cz.zcu.kiv.eegdatabase.webservices.rest.reservation.wrappers.ReservationData;
import cz.zcu.kiv.eegdatabase.webservices.rest.reservation.wrappers.ReservationDataList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Reservation service implementation class.
 *
 * @author Petr Miko
 */

@Service
public class ReservationServiceImpl implements ReservationService {

    private final static Log log = LogFactory.getLog(ReservationServiceImpl.class);
    private final SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
    @Autowired(required = true)
    @Qualifier("reservationDao")
    private ReservationDao reservationDao;
    @Autowired(required = true)
    @Qualifier("researchGroupDao")
    private ResearchGroupDao researchGroupDao;
    @Autowired(required = true)
    @Qualifier("personDao")
    private PersonDao personDao;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ReservationDataList getToDate(String date) throws RestServiceException {
        GregorianCalendar calendarFrom = new GregorianCalendar();
        GregorianCalendar calendarTo = new GregorianCalendar();
        try {
            log.debug(sf.parse(date));
            calendarFrom.setTime(sf.parse(date));
            calendarTo.setTime(sf.parse(date));
            calendarTo.add(Calendar.DAY_OF_MONTH, 1);
            List<Reservation> reservations = reservationDao.getReservationsBetween(calendarFrom, calendarTo);
            List<ReservationData> data = new ArrayList<ReservationData>(reservations.size());

            for (Reservation r : reservations) {
                data.add(new ReservationData(r.getReservationId(),
                        r.getResearchGroup().getTitle(),
                        r.getResearchGroup().getResearchGroupId(),
                        r.getStartTime(),
                        r.getEndTime(),
                        r.getPerson().getGivenname() + " " + r.getPerson().getSurname(),
                        r.getPerson().getEmail(),
                        canRemoveReservation(r)));
            }
            return new ReservationDataList(data);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RestServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ReservationDataList getFromToDate(String fromDate, String toDate) throws RestServiceException {
        GregorianCalendar fromCalendar = new GregorianCalendar();
        GregorianCalendar toCalendar = new GregorianCalendar();
        try {
            fromCalendar.setTime(sf.parse(fromDate));
            toCalendar.setTime(sf.parse(toDate));
            List<Reservation> reservations = reservationDao.getReservationsBetween(fromCalendar, toCalendar);
            List<ReservationData> data = new ArrayList<ReservationData>(reservations.size());

            for (Reservation r : reservations) {
                data.add(new ReservationData(r.getReservationId(),
                        r.getResearchGroup().getTitle(),
                        r.getResearchGroup().getResearchGroupId(),
                        r.getStartTime(),
                        r.getEndTime(),
                        r.getPerson().getGivenname() + " " + r.getPerson().getSurname(),
                        r.getPerson().getEmail(),
                        canRemoveReservation(r)));
            }

            return new ReservationDataList(data);
        } catch (Exception e) {
            log.error(String.format("From date: %s | To date: %s | Error: %s", fromDate, toDate, e.getMessage()), e);
            throw new RestServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ReservationData create(ReservationData reservationData) throws RestServiceException {
        try {
            ResearchGroup group = researchGroupDao.read(reservationData.getResearchGroupId());
            Person user = personDao.getLoggedPerson();

            if (group == null)
                throw new RestServiceException("Existing group Id must be specified");
            if (reservationData.getFromTime() == null)
                throw new RestServiceException("Start time must be specified");
            if (reservationData.getToTime() == null)
                throw new RestServiceException("End time must be specified");
            if (!isInGroup(user, group.getResearchGroupId()))
                throw new RestServiceException("You are not a member of " + group.getTitle() + " group!");

            Reservation reservation = new Reservation();

            reservation.setResearchGroup(group);
            reservation.setPerson(user);
            reservation.setStartTime(new Timestamp(reservationData.getFromTime().getTime()));
            reservation.setEndTime(new Timestamp(reservationData.getToTime().getTime()));
            reservation.setCreationTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));

            int id = reservationDao.createChecked(reservation);

            reservationData.setReservationId(id);
            reservationData.setResearchGroupId(group.getResearchGroupId());
            reservationData.setResearchGroup(group.getTitle());
            reservationData.setCreatorName(user.getGivenname() + " " + user.getSurname());
            reservationData.parseMail(user.getEmail());
            return reservationData;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RestServiceException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(int reservationId) throws RestServiceException {
        try {
            Reservation reservation = reservationDao.read(reservationId);

            if (reservation == null) {
                throw new RestServiceException("No reservation with id " + reservationId);
            } else {
                if (canRemoveReservation(reservation)) {
                    reservationDao.delete(reservation);
                } else
                    throw new RestServiceException("You are not administrator nor member of the group!");
            }
        } catch (Exception e) {
            log.error(e);
            throw new RestServiceException(e);
        }
    }

    /**
     * Checks if is user a member of specified research group.
     *
     * @param user            user
     * @param researchGroupId research group identifier
     * @return true if is user in group
     */
    private boolean isInGroup(Person user, int researchGroupId) {
        if (!user.getResearchGroupMemberships().isEmpty()) {
            for (ResearchGroup g : researchGroupDao.getResearchGroupsWhereMember(user)) {
                if (g.getResearchGroupId() == researchGroupId) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check method, is user allowed to remove reservation record.
     *
     * @param reservation reservation record
     * @return true if user can remove reservation
     */
    private boolean canRemoveReservation(Reservation reservation) {
        Person user = personDao.getLoggedPerson();
        return isInGroup(user, reservation.getResearchGroup().getResearchGroupId()) || "ROLE_ADMIN".equalsIgnoreCase(user.getAuthority());
    }
}
