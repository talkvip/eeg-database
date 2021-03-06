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
 *   FileServiceImpl.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.core.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import cz.zcu.kiv.eegdatabase.data.dao.DataFileDao;
import cz.zcu.kiv.eegdatabase.data.pojo.DataFile;

public class FileServiceImpl implements FileService {

    protected Log log = LogFactory.getLog(getClass());

    private DataFileDao fileDAO;
    private SessionFactory factory;

    @Required
    public void setFileDAO(DataFileDao fileDAO) {
        this.fileDAO = fileDAO;
    }

    @Required
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    @Transactional
    public Integer create(DataFile newInstance) {

        try {

            Blob createBlob = factory.getCurrentSession().getLobHelper().createBlob(newInstance.getFileContentStream(), newInstance.getFileContentStream().available());
            newInstance.setFileContent(createBlob);

            return fileDAO.create(newInstance);

        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            return null;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public DataFile read(Integer id) {
        return fileDAO.read(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DataFile> readByParameter(String parameterName, Object parameterValue) {
        return fileDAO.readByParameter(parameterName, parameterValue);
    }

    @Override
    @Transactional
    public void update(DataFile transientObject) {
        fileDAO.update(transientObject);
    }

    @Override
    @Transactional
    public void delete(DataFile persistentObject) {
        fileDAO.delete(persistentObject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DataFile> getAllRecords() {
        return fileDAO.getAllRecords();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DataFile> getRecordsAtSides(int first, int max) {
        return fileDAO.getRecordsAtSides(first, max);
    }

    @Override
    @Transactional(readOnly = true)
    public int getCountRecords() {
        return fileDAO.getCountRecords();
    }

    @Override
    public List<DataFile> getUnique(DataFile example) {
        return fileDAO.findByExample(example);
    }

    @Override
    @Transactional
    public FileDTO getFile(int fileId) {

        try {
            DataFile dataFileEntity = fileDAO.read(fileId);
            FileDTO dto = new FileDTO();

            File tmpFile;
            tmpFile = File.createTempFile("dataFile", ".tmp");
            tmpFile.deleteOnExit();
            FileOutputStream out = new FileOutputStream(tmpFile);
            IOUtils.copyLarge(dataFileEntity.getFileContent().getBinaryStream(), out);
            out.close();
            dto.setFile(tmpFile);
            dto.setFileName(dataFileEntity.getFilename());
            dto.setMimetype(dataFileEntity.getMimetype());

            return dto;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return null;
        }

    }
}
