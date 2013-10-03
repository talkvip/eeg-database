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
 *   ExperimentOptParamDefGroupRel.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.pojo;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * @author František Liška
 */
@Entity
@javax.persistence.Table(name = "EXPERIMENT_OPT_PARAM_GROUP_REL")
public class ExperimentOptParamDefGroupRel implements java.io.Serializable {
    @EmbeddedId
    private ExperimentOptParamDefGroupRelId id;

    @ManyToOne
    @JoinColumn(name = "RESEARCH_GROUP_ID")
    private ResearchGroup researchGroup;
    @ManyToOne
    @JoinColumn(name = "EXPERIMENT_OPT_PARAM_DEF_ID")
    private ExperimentOptParamDef experimentOptParamDef;

    public ExperimentOptParamDefGroupRel() {
    }

    public ExperimentOptParamDefGroupRel(ExperimentOptParamDefGroupRelId id, ResearchGroup researchGroup, ExperimentOptParamDef experimentOptParamDef) {
        this.id = id;
        this.researchGroup = researchGroup;
        this.experimentOptParamDef = experimentOptParamDef;
    }

    public ExperimentOptParamDefGroupRelId getId() {
        return this.id;
    }

    public void setId(ExperimentOptParamDefGroupRelId id) {
        this.id = id;
    }

    public ResearchGroup getResearchGroup() {
        return this.researchGroup;
    }

    public void setResearchGroup(ResearchGroup researchGroup) {
        this.researchGroup = researchGroup;
    }

    public ExperimentOptParamDef getExperimentOptParamDef() {
        return this.experimentOptParamDef;
    }

    public void setExperimentOptParamDef(ExperimentOptParamDef experimentOptParamDef) {
        this.experimentOptParamDef = experimentOptParamDef;
    }

}

