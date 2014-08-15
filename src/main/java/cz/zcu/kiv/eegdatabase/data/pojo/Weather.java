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
 *   Weather.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.pojo;

// Generated 2.12.2013 0:56:28 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cz.zcu.kiv.formgen.annotation.FormId;
import cz.zcu.kiv.formgen.annotation.FormItem;
import cz.zcu.kiv.formgen.annotation.PreviewLevel;

/**
 * Weather generated by hbm2java
 */
@Form
@Entity
@Table(name = "WEATHER")
public class Weather implements java.io.Serializable {

	@FormId
	private int weatherId;
	@FormItem
	private String description;
	@FormItem(required = true, preview = PreviewLevel.MAJOR)
	private String title;
	private int defaultNumber;
	private Set<Experiment> experiments = new HashSet<Experiment>(0);
	private Set<ResearchGroup> researchGroups = new HashSet<ResearchGroup>(0);

	public Weather() {
	}

	public Weather(String title) {
		this.title = title;
	}

	public Weather(String description, String title, int defaultNumber,
			Set<Experiment> experiments, Set<ResearchGroup> researchGroups) {
		this.description = description;
		this.title = title;
		this.defaultNumber = defaultNumber;
		this.experiments = experiments;
		this.researchGroups = researchGroups;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "WEATHER_ID", nullable = false, precision = 22, scale = 0)
	public int getWeatherId() {
		return this.weatherId;
	}

	public void setWeatherId(int weatherId) {
		this.weatherId = weatherId;
	}

	@Column(name = "DESCRIPTION", length = 30)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "TITLE", nullable = false, length = 30)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "IS_DEFAULT", precision = 1, scale = 0)
	public int getDefaultNumber() {
		return this.defaultNumber;
	}

	public void setDefaultNumber(int defaultNumber) {
		this.defaultNumber = defaultNumber;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "weather")
	public Set<Experiment> getExperiments() {
		return this.experiments;
	}

	public void setExperiments(Set<Experiment> experiments) {
		this.experiments = experiments;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "WEATHER_GROUP_REL", joinColumns = { @JoinColumn(name = "WEATHER_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "RESEARCH_GROUP_ID", nullable = false, updatable = false) })
	public Set<ResearchGroup> getResearchGroups() {
		return this.researchGroups;
	}

	public void setResearchGroups(Set<ResearchGroup> researchGroups) {
		this.researchGroups = researchGroups;
	}

}
