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
 *   ExperimentsOptParamService.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.core.experiments.param;

import java.util.List;

import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentOptParamDef;
import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentOptParamDefGroupRel;
import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentOptParamVal;
import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentOptParamValId;
import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroup;
import cz.zcu.kiv.eegdatabase.wui.core.GenericService;

public interface ExperimentsOptParamService extends GenericService<ExperimentOptParamDef, Integer> {

    List<ExperimentOptParamDef> getItemsForList();

    boolean canDelete(int id);

    List<ExperimentOptParamDef> getRecordsByGroup(int groupId);

    void createDefaultRecord(ExperimentOptParamDef experimentOptParamDef);

    List<ExperimentOptParamDef> getDefaultRecords();

    boolean hasGroupRel(int id);

    void deleteGroupRel(ExperimentOptParamDefGroupRel experimentOptParamDefGroupRel);

    ExperimentOptParamDefGroupRel getGroupRel(int experimentOptParamDefId, int researchGroupId);

    void createGroupRel(ExperimentOptParamDefGroupRel experimentOptParamDefGroupRel);

    void createGroupRel(ExperimentOptParamDef experimentOptParamDef, ResearchGroup researchGroup);

    boolean isDefault(int id);

    // ExperimentOptParamVal

    ExperimentOptParamValId create(ExperimentOptParamVal newInstance);

    ExperimentOptParamVal read(ExperimentOptParamValId id);

    List<ExperimentOptParamVal> readValByParameter(String parameterName, Object parameterValue);

    void update(ExperimentOptParamVal transientObject);

    void delete(ExperimentOptParamVal persistentObject);

    List<ExperimentOptParamVal> getAllValRecords();

    List<ExperimentOptParamVal> getValRecordsAtSides(int first, int max);

    int getCountRecords();

    List<ExperimentOptParamVal> getUnique(ExperimentOptParamVal example);
}
