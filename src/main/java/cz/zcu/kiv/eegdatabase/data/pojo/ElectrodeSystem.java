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
 *   ElectrodeSystem.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.pojo;

import cz.zcu.kiv.eegdatabase.data.annotation.SolrField;
import cz.zcu.kiv.eegdatabase.data.annotation.SolrId;
import cz.zcu.kiv.eegdatabase.logic.indexing.IndexField;
import cz.zcu.kiv.formgen.annotation.FormId;
import cz.zcu.kiv.formgen.annotation.FormItem;
import cz.zcu.kiv.formgen.annotation.PreviewLevel;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * ElectrodeSystem generated by hbm2java
 */
@Form
@Entity
@Table(name = "ELECTRODE_SYSTEM")
public class ElectrodeSystem implements Serializable {

	@SolrId
	@FormId
	private int electrodeSystemId;
	@SolrField(name = IndexField.TITLE)
	@FormItem(preview = PreviewLevel.MAJOR)
	private String title;
	@SolrField(name = IndexField.TEXT)
	@FormItem(preview = PreviewLevel.MINOR)
	private String description;
	private int defaultNumber;
	private Set<ElectrodeConf> electrodeConfs = new HashSet<ElectrodeConf>(0);
	private Set<ResearchGroup> researchGroups = new HashSet<ResearchGroup>(0);

	public ElectrodeSystem() {
	}

	public ElectrodeSystem(String title, String description, int defaultNumber,
			Set<ElectrodeConf> electrodeConfs, Set<ResearchGroup> researchGroups) {
		this.title = title;
		this.description = description;
		this.defaultNumber = defaultNumber;
		this.electrodeConfs = electrodeConfs;
		this.researchGroups = researchGroups;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ELECTRODE_SYSTEM_ID", nullable = false, precision = 22, scale = 0)
	public int getElectrodeSystemId() {
		return this.electrodeSystemId;
	}

	public void setElectrodeSystemId(int electrodeSystemId) {
		this.electrodeSystemId = electrodeSystemId;
	}

	@Column(name = "TITLE", precision = 22, scale = 0)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "IS_DEFAULT", precision = 1, scale = 0)
	public int getDefaultNumber() {
		return this.defaultNumber;
	}

	public void setDefaultNumber(int defaultNumber) {
		this.defaultNumber = defaultNumber;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "electrodeSystem")
	public Set<ElectrodeConf> getElectrodeConfs() {
		return this.electrodeConfs;
	}

	public void setElectrodeConfs(Set<ElectrodeConf> electrodeConfs) {
		this.electrodeConfs = electrodeConfs;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ELECTRODE_SYSTEM_GROUP_REL", joinColumns = { @JoinColumn(name = "ELECTRODE_SYSTEM_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "RESEARCH_GROUP_ID", nullable = false, updatable = false) })
	public Set<ResearchGroup> getResearchGroups() {
		return this.researchGroups;
	}

	public void setResearchGroups(Set<ResearchGroup> researchGroups) {
		this.researchGroups = researchGroups;
	}

}
