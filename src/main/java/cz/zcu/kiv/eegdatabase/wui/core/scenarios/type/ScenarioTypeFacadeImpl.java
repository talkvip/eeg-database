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
 *   ScenarioTypeFacadeImpl.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.core.scenarios.type;

import cz.zcu.kiv.eegdatabase.data.pojo.ScenarioType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class ScenarioTypeFacadeImpl implements ScenarioTypeFacade {
    
    protected Log log = LogFactory.getLog(getClass());

    ScenarioTypeService service;

    @Required
    public void setService(ScenarioTypeService service) {
        this.service = service;
    }

    @Override
    public Integer create(ScenarioType newInstance) {
        return service.create(newInstance);
    }

    @Override
    public ScenarioType read(Integer id) {
        return service.read(id);
    }

    @Override
    public List<ScenarioType> readByParameter(String parameterName, Object parameterValue) {
        return service.readByParameter(parameterName, parameterValue);
    }

    @Override
    public void update(ScenarioType transientObject) {
        service.update(transientObject);
    }

    @Override
    public void delete(ScenarioType persistentObject) {
        service.delete(persistentObject);
    }

    @Override
    public List<ScenarioType> getAllRecords() {
        return service.getAllRecords();
    }

    @Override
    public List<ScenarioType> getRecordsAtSides(int first, int max) {
        return service.getRecordsAtSides(first, max);
    }

    @Override
    public int getCountRecords() {
        return service.getCountRecords();
    }

    @Override
    public List<ScenarioType> getUnique(ScenarioType example) {
        return service.getUnique(example);
    }

}
