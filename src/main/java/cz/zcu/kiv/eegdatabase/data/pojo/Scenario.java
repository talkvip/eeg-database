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
 *   Scenario.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.pojo;

// Generated 2.12.2013 0:56:28 by Hibernate Tools 3.4.0.CR1
import cz.zcu.kiv.eegdatabase.webservices.rest.common.utils.BlobSerializer;
import java.io.Serializable;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * Scenario generated by hbm2java
 */
@Entity
@Table(name = "SCENARIO")
@XmlRootElement
public class Scenario implements Serializable, Comparable<Scenario> {

	private int scenarioId;
	private Person person;
	private ResearchGroup researchGroup;
	private String title;
	private int scenarioLength;
	private boolean privateScenario;
	private String description;
	private String scenarioName;
	private String mimetype;
	private Set<History> histories = new HashSet<History>(0);
	private Set<Experiment> experiments = new HashSet<Experiment>(0);
	private boolean userMemberOfGroup;
	private Blob scenarioFile;
	private String group;
	private Boolean availableFile;

	@Transient
	public boolean isUserMemberOfGroup() {
		return userMemberOfGroup;
	}

	public void setUserMemberOfGroup(boolean userMemberOfGroup) {
		this.userMemberOfGroup = userMemberOfGroup;
	}

	@Transient
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Transient
	public Boolean getAvailableFile() {
		return availableFile;
	}

	public void setAvailableFile(Boolean availableFile) {
		this.availableFile = availableFile;
	}

	public Scenario() {
	}

	public Scenario(Person person, ResearchGroup researchGroup) {
		this.person = person;
		this.researchGroup = researchGroup;
	}

	public Scenario(Person person, ResearchGroup researchGroup, String title,
					int scenarioLength, boolean privateScenario, String description,
					String scenarioName, String mimetype, Set<History> histories,
					Set<Experiment> experiments) {
		this.person = person;
		this.researchGroup = researchGroup;
		this.title = title;
		this.scenarioLength = scenarioLength;
		this.privateScenario = privateScenario;
		this.description = description;
		this.scenarioName = scenarioName;
		this.mimetype = mimetype;
		this.histories = histories;
		this.experiments = experiments;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "SCENARIO_ID", nullable = false, precision = 22, scale = 0)
	public int getScenarioId() {
		return this.scenarioId;
	}

	public void setScenarioId(int scenarioId) {
		this.scenarioId = scenarioId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OWNER_ID", nullable = false)
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RESEARCH_GROUP_ID", nullable = false)
	public ResearchGroup getResearchGroup() {
		return this.researchGroup;
	}

	public void setResearchGroup(ResearchGroup researchGroup) {
		this.researchGroup = researchGroup;
	}

	@Column(name = "TITLE", unique = true)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "SCENARIO_LENGTH", precision = 22, scale = 0)
	public int getScenarioLength() {
		return this.scenarioLength;
	}

	public void setScenarioLength(int scenarioLength) {
		this.scenarioLength = scenarioLength;
	}

	@Column(name = "PRIVATE", precision = 1, scale = 0)
	public boolean isPrivateScenario() {
		return this.privateScenario;
	}

	public void setPrivateScenario(boolean privateScenario) {
		this.privateScenario = privateScenario;
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

	@Column(name = "SCENARIO_NAME")
	public String getScenarioName() {
		return this.scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	@Column(name = "MIMETYPE")
	public String getMimetype() {
		return this.mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "scenario")
	public Set<History> getHistories() {
		return this.histories;
	}

	public void setHistories(Set<History> histories) {
		this.histories = histories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "scenario")
	public Set<Experiment> getExperiments() {
		return this.experiments;
	}

	public void setExperiments(Set<Experiment> experiments) {
		this.experiments = experiments;
	}

	@XmlJavaTypeAdapter(BlobSerializer.class)
	@Basic(fetch=FetchType.LAZY)
	@Lob
	@Column(name = "SCENARIO_FILE", nullable = true)
	public Blob getScenarioFile() {
		return this.scenarioFile;
	}

	public void setScenarioFile(Blob scenarioFile) {
		this.scenarioFile = scenarioFile;
	}
	
	@Override
	public int compareTo(Scenario scen) {
		return this.title.compareTo(scen.getTitle());
	}
}
