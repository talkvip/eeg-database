/**
 * *****************************************************************************
 * This file is part of the EEG-database project
 *
 * ==========================================
 *
 * Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *
 *  ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 *  ***********************************************************************************************************************
 *
 * Experiment.java, 2013/10/02 00:01 Jakub Rinkes
 * ****************************************************************************
 */
package cz.zcu.kiv.eegdatabase.data.pojo;
// Generated 19.1.2010 23:18:53 by Hibernate Tools 3.2.1.GA

import cz.zcu.kiv.eegdatabase.data.annotation.Indexed;
import cz.zcu.kiv.eegdatabase.data.annotation.SolrField;
import cz.zcu.kiv.eegdatabase.data.annotation.SolrId;
import cz.zcu.kiv.eegdatabase.data.elasticsearch.entities.ExperimentElastic;
import cz.zcu.kiv.eegdatabase.data.elasticsearch.entities.GenericParameter;
import cz.zcu.kiv.eegdatabase.logic.indexing.IndexField;
import cz.zcu.kiv.eegdatabase.logic.util.SignalProcessingUtils;
import cz.zcu.kiv.formgen.annotation.Form;
import cz.zcu.kiv.formgen.annotation.FormItem;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.GenericGenerator;

/**
 * Experiment generated by hbm2java
 */
@Form("experiment")
@Entity
@Indexed
@Table(name = "EXPERIMENT")
public class Experiment implements Serializable {

	@SolrId
	private int experimentId;
	@Indexed
	private Weather weather; //being transformed to GenericParameter
	private Person personBySubjectPersonId;
	private Scenario scenario;
	private Person personByOwnerId;
	private ResearchGroup researchGroup;
	private Digitization digitization;//being transformed to GenericParameter
	private SubjectGroup subjectGroup;
	private Artifact artifact;
	private ElectrodeConf electrodeConf;
	@FormItem(label = "zacatek")
	private Timestamp startTime;
	@FormItem(label = "konec")
	private Timestamp endTime;
	@SolrField(name = IndexField.TEMPERATURE)
	@FormItem(label = "teplota")
	private int temperature;//being transformed to GenericParameter
	private boolean privateExperiment;
	@SolrField(name = IndexField.TEXT)
	@FormItem(label = "poznamka")
	private String environmentNote;
	private Set<Person> persons = new HashSet<Person>(0);
	private Set<Hardware> hardwares = new HashSet<Hardware>(0);//being transformed to GenericParameter
	private Set<Pharmaceutical> pharmaceuticals = new HashSet<Pharmaceutical>(0);//being transformed to GenericParameter
	private Set<Disease> diseases = new HashSet<Disease>(0);//being transformed to GenericParameter
	private Set<ProjectType> projectTypes = new HashSet<ProjectType>(0);//being transformed to GenericParameter
	private Set<Software> softwares = new HashSet<Software>(0);//being transformed to GenericParameter
	private Set<ArtifactRemoveMethod> artifactRemoveMethods = new HashSet<ArtifactRemoveMethod>(0);
	private Set<DataFile> dataFiles = new HashSet<DataFile>(0);
	private Set<History> histories = new HashSet<History>(0);
	private Set<ExperimentOptParamVal> experimentOptParamVals = new HashSet<ExperimentOptParamVal>(0);
	private Set<ExperimentPackageConnection> experimentPackageConnections = new HashSet<ExperimentPackageConnection>(0);

	private ExperimentElastic elasticExperiment = new ExperimentElastic();

	public void setElasticExperiment(ExperimentElastic e) {
		this.elasticExperiment = e;
	}

	@Transient
	public ExperimentElastic getElasticExperiment() {
		return this.elasticExperiment;
	}

	public Experiment() {
	}

	public Experiment(Weather weather, Person personBySubjectPersonId,
					Scenario scenario, Person personByOwnerId,
					ResearchGroup researchGroup, Digitization digitization,
					SubjectGroup subjectGroup, Artifact artifact,
					ElectrodeConf electrodeConf) {
		this.setWeather(weather);
		this.setPersonBySubjectPersonId(personBySubjectPersonId);
		this.setScenario(scenario);
		this.setPersonByOwnerId(personByOwnerId);
		this.setResearchGroup(researchGroup);
		this.setDigitization(digitization);
		this.setSubjectGroup(subjectGroup);
		this.setArtifact(artifact);
		this.setElectrodeConf(electrodeConf);
	}

	public Experiment(Weather weather, Person personBySubjectPersonId,
					Scenario scenario, Person personByOwnerId,
					ResearchGroup researchGroup, Digitization digitization,
					SubjectGroup subjectGroup, Artifact artifact,
					ElectrodeConf electrodeConf, Timestamp startTime,
					Timestamp endTime, int temperature, boolean privateExperiment,
					String environmentNote, Set<Person> persons,
					Set<Hardware> hardwares, Set<Pharmaceutical> pharmaceuticals,
					Set<Disease> diseases, Set<ProjectType> projectTypes,
					Set<Software> softwares,
					Set<ArtifactRemoveMethod> artifactRemoveMethods,
					Set<DataFile> dataFiles, Set<History> histories,
					Set<ExperimentOptParamVal> experimentOptParamVals) {
		this.setWeather(weather);
		this.setPersonBySubjectPersonId(personBySubjectPersonId);
		this.setScenario(scenario);
		this.setPersonByOwnerId(personByOwnerId);
		this.setResearchGroup(researchGroup);
		this.setDigitization(digitization);
		this.setSubjectGroup(subjectGroup);
		this.setArtifact(artifact);
		this.setElectrodeConf(electrodeConf);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setTemperature(temperature);
		this.setPrivateExperiment(privateExperiment);
		this.setEnvironmentNote(environmentNote);
		this.setPersons(persons);
		this.setHardwares(hardwares);
		this.setPharmaceuticals(pharmaceuticals);
		this.setDiseases(diseases);
		this.setProjectTypes(projectTypes);
		this.setSoftwares(softwares);
		this.setArtifactRemoveMethods(artifactRemoveMethods);
		this.setDataFiles(dataFiles);
		this.setHistories(histories);
		this.setExperimentOptParamVals(experimentOptParamVals);
	}

	@Transient
	public List<GenericParameter> getGenericParameters() {
		return this.elasticExperiment.getParams();
	}

	public void setGenericParameters(List<GenericParameter> params) {
		this.elasticExperiment.setParams(params);
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "EXPERIMENT_ID", nullable = false, precision = 22, scale = 0)
	public int getExperimentId() {
		return this.experimentId;
	}

	public void setExperimentId(int experimentId) {
		this.experimentId = experimentId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WEATHER_ID", nullable = false)
	public Weather getWeather() {
		return this.weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SUBJECT_PERSON_ID", nullable = false)
	public Person getPersonBySubjectPersonId() {
		return this.personBySubjectPersonId;
	}

	public void setPersonBySubjectPersonId(Person personBySubjectPersonId) {
		this.personBySubjectPersonId = personBySubjectPersonId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCENARIO_ID", nullable = false)
	public Scenario getScenario() {
		return this.scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OWNER_ID", nullable = false)
	public Person getPersonByOwnerId() {
		return this.personByOwnerId;
	}

	public void setPersonByOwnerId(Person personByOwnerId) {
		this.personByOwnerId = personByOwnerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESEARCH_GROUP_ID", nullable = false)
	public ResearchGroup getResearchGroup() {
		return this.researchGroup;
	}

	public void setResearchGroup(ResearchGroup researchGroup) {
		this.researchGroup = researchGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DIGITIZATION_ID", nullable = false)
	public Digitization getDigitization() {
		return this.digitization;
	}

	public void setDigitization(Digitization digitization) {
		this.digitization = digitization;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBJECT_GROUP_ID", nullable = false)
	public SubjectGroup getSubjectGroup() {
		return this.subjectGroup;
	}

	public void setSubjectGroup(SubjectGroup subjectGroup) {
		this.subjectGroup = subjectGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ARTEFACT_ID", nullable = false)
	public Artifact getArtifact() {
		return this.artifact;
	}

	public void setArtifact(Artifact artifact) {
		this.artifact = artifact;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ELECTRODE_CONF_ID", nullable = false)
	public ElectrodeConf getElectrodeConf() {
		return this.electrodeConf;
	}

	public void setElectrodeConf(ElectrodeConf electrodeConf) {
		this.electrodeConf = electrodeConf;
	}

	@Column(name = "START_TIME", length = 7)
	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIME", length = 7)
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "TEMPERATURE", precision = 22, scale = 0)
	public int getTemperature() {
		return this.temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	@Column(name = "PRIVATE", precision = 1, scale = 0)
	public boolean isPrivateExperiment() {
		return this.privateExperiment;
	}

	public void setPrivateExperiment(boolean privateExperiment) {
		this.privateExperiment = privateExperiment;
	}

	@Column(name = "ENVIRONMENT_NOTE")
	public String getEnvironmentNote() {
		return this.environmentNote;
	}

	public void setEnvironmentNote(String environmentNote) {
		this.environmentNote = environmentNote;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "experiments")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "experiments")
	public Set<Hardware> getHardwares() {
		return this.hardwares;
	}

	public void setHardwares(Set<Hardware> hardwares) {
		this.hardwares = hardwares;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "experiments")
	public Set<Pharmaceutical> getPharmaceuticals() {
		return this.pharmaceuticals;
	}

	public void setPharmaceuticals(Set<Pharmaceutical> pharmaceuticals) {
		this.pharmaceuticals = pharmaceuticals;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "experiments")
	public Set<Disease> getDiseases() {
		return this.diseases;
	}

	public void setDiseases(Set<Disease> diseases) {
		this.diseases = diseases;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "experiments")
	public Set<ProjectType> getProjectTypes() {
		return this.projectTypes;
	}

	public void setProjectTypes(Set<ProjectType> projectTypes) {
		this.projectTypes = projectTypes;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "experiments")
	public Set<Software> getSoftwares() {
		return this.softwares;
	}

	public void setSoftwares(Set<Software> softwares) {
		this.softwares = softwares;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "experiments")
	public Set<ArtifactRemoveMethod> getArtifactRemoveMethods() {
		return this.artifactRemoveMethods;
	}

	public void setArtifactRemoveMethods(
					Set<ArtifactRemoveMethod> artifactRemoveMethods) {
		this.artifactRemoveMethods = artifactRemoveMethods;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "experiment")
	public Set<DataFile> getDataFiles() {
		return this.dataFiles;
	}

	public void setDataFiles(Set<DataFile> dataFiles) {
		this.dataFiles = dataFiles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "experiment")
	public Set<History> getHistories() {
		return this.histories;
	}

	public void setHistories(Set<History> histories) {
		this.histories = histories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "experiment")
	public Set<ExperimentOptParamVal> getExperimentOptParamVals() {
		return this.experimentOptParamVals;
	}

	public void setExperimentOptParamVals(
					Set<ExperimentOptParamVal> experimentOptParamVals) {
		this.experimentOptParamVals = experimentOptParamVals;
	}

	@OneToMany(mappedBy = "experiment")
	public Set<ExperimentPackageConnection> getExperimentPackageConnections() {
		return experimentPackageConnections;
	}

	public void setExperimentPackageConnections(Set<ExperimentPackageConnection> experimentPackageConnections) {
		this.experimentPackageConnections = experimentPackageConnections;
	}

	@Transient
	public Date getFinishDate() {
		if (endTime != null) {
			return new Date(endTime.getTime());
		} else {
			Date now = new Date();
			endTime = new Timestamp(now.getTime());
			return now;
		}
	}

	@Transient
	public Date getStartDate() {
		if (startTime != null) {
			return new Date(startTime.getTime());
		} else {
			Date now = new Date();
			startTime = new Timestamp(now.getTime());
			return now;
		}
	}

	public void setFinishDate(Date date) {
		endTime = new Timestamp(date.getTime());
	}

	public void setStartDate(Date date) {
		startTime = new Timestamp(date.getTime());
	}
}
