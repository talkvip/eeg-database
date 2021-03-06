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
 *   PersonFacadeImpl.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.core.person;

import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.logic.controller.search.SearchRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.Map;

public class PersonFacadeImpl implements PersonFacade {

    protected Log log = LogFactory.getLog(getClass());

    PersonService personService;

    @Required
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public Integer create(Person person) {
        return personService.create(person);
    }

    @Override
    public Person getPersonByHash(String hashCode) {
        return personService.getPersonByHash(hashCode);
    }

    @Override
    public void delete(Person person) {
        personService.delete(person);
    }

    @Override
    public void update(Person person) {
        personService.update(person);
    }

    @Override
    public boolean usernameExists(String userName) {
        return personService.usernameExists(userName);
    }

    @Override
    public Person getPerson(String username) {
        return personService.getPerson(username);
    }

    @Override
    public void changeUserPassword(String userName, String password) {
        personService.changeUserPassword(userName, password);
    }

    @Override
    public boolean isPasswordEquals(String userName, String password) {
        return personService.isPasswordEquals(userName, password);
    }

    @Override
    public void forgottenPassword(Person person) {
        personService.forgottenPassword(person);
    }

    @Override
    public Person read(Integer id) {
        return personService.read(id);
    }

    @Override
    public List<Person> readByParameter(String parameterName, Object parameterValue) {
        return personService.readByParameter(parameterName, parameterValue);
    }

    @Override
    public List<Person> getAllRecords() {
        return personService.getAllRecords();
    }

    @Override
    public List<Person> getRecordsAtSides(int first, int max) {
        return personService.getRecordsAtSides(first, max);
    }

    @Override
    public int getCountRecords() {
        return personService.getCountRecords();
    }

    @Override
    public List<Person> getUnique(Person example) {
        return personService.getUnique(example);
    }

    @Override
    public Person getPersonByFbUid(String fbUid) {
        return personService.getPersonByFbUid(fbUid);
    }

    @Override
    public List<Person> getPersonsWherePendingRequirement() {
        return personService.getPersonsWherePendingRequirement();
    }

    @Override
    public boolean fbUidExists(String id) {
        return personService.fbUidExists(id);
    }

    @Override
    public List<Person> getSupervisors() {
        return personService.getSupervisors();
    }

    @Override
    public Person getLoggedPerson() {
        return personService.getLoggedPerson();
    }

    @Override
    public Map getInfoForAccountOverview(Person loggedPerson) {
        return personService.getInfoForAccountOverview(loggedPerson);
    }


    @Override
    public boolean userNameInGroup(String userName, int groupId) {
        return personService.userNameInGroup(userName, groupId);
    }

    @Override
    public List<Person> getPersonSearchResults(List<SearchRequest> requests) {
        return personService.getPersonSearchResults(requests);
    }

    @Override
    public int getCountForList() {
        return personService.getCountForList();
    }

    @Override
    public List<Person> getDataForList(int start, int limit) {
        return personService.getDataForList(start, limit);
    }

    @Override
    public Person getPersonForDetail(int id) {
        return personService.getPersonForDetail(id);
    }

    public void initialize(Person person){
        personService.initialize(person);
    }

}
