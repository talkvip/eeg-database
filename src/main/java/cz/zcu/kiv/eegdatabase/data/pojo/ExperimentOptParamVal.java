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
 *   ExperimentOptParamVal.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.pojo;

// Generated 2.12.2013 0:56:28 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 * ExperimentOptParamVal generated by hbm2java
 */
@Entity
@Table(name = "EXPERIMENT_OPT_PARAM_VAL")
public class ExperimentOptParamVal implements java.io.Serializable {

	private ExperimentOptParamValId id;
	private Experiment experiment;
	private ExperimentOptParamDef experimentOptParamDef;
	private String paramValue;

	public ExperimentOptParamVal() {
	}

	public ExperimentOptParamVal(ExperimentOptParamValId id,
			Experiment experiment, ExperimentOptParamDef experimentOptParamDef,
			String paramValue) {
		this.id = id;
		this.experiment = experiment;
		this.experimentOptParamDef = experimentOptParamDef;
		this.paramValue = paramValue;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "experimentId", column = @Column(name = "EXPERIMENT_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "experimentOptParamDefId", column = @Column(name = "EXPERIMENT_OPT_PARAM_DEF_ID", nullable = false, precision = 22, scale = 0)) })
	public ExperimentOptParamValId getId() {
		return this.id;
	}

	public void setId(ExperimentOptParamValId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXPERIMENT_ID", nullable = false, insertable = false, updatable = false)
	public Experiment getExperiment() {
		return this.experiment;
	}

	public void setExperiment(Experiment experiment) {
		this.experiment = experiment;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EXPERIMENT_OPT_PARAM_DEF_ID", nullable = false, insertable = false, updatable = false)
	public ExperimentOptParamDef getExperimentOptParamDef() {
		return this.experimentOptParamDef;
	}

	public void setExperimentOptParamDef(
			ExperimentOptParamDef experimentOptParamDef) {
		this.experimentOptParamDef = experimentOptParamDef;
	}

	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "PARAM_VALUE", nullable = false)
	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}
