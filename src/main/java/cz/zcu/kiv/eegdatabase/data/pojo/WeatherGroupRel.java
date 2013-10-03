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
 *   WeatherGroupRel.java, 2013/10/02 00:01 Jakub Rinkes
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
@javax.persistence.Table(name="WEATHER_GROUP_REL")
public class WeatherGroupRel implements java.io.Serializable {
    @EmbeddedId
  private WeatherGroupRelId id;
    @ManyToOne
    @JoinColumn(name = "RESEARCH_GROUP_ID")
  private ResearchGroup researchGroup;
    @ManyToOne
    @JoinColumn(name = "WEATHER_ID")
  private Weather weather;

  public WeatherGroupRel() {
  }

  public WeatherGroupRel(WeatherGroupRelId id, ResearchGroup researchGroup, Weather weather) {
    this.id = id;
    this.researchGroup = researchGroup;
    this.weather = weather;
  }

  public WeatherGroupRelId getId() {
    return this.id;
  }

  public void setId(WeatherGroupRelId id) {
    this.id = id;
  }

  public ResearchGroup getResearchGroup() {
    return this.researchGroup;
  }

  public void setResearchGroup(ResearchGroup researchGroup) {
    this.researchGroup = researchGroup;
  }

  public Weather getWeather() {
    return this.weather;
  }

  public void setWeather(Weather weather) {
    this.weather = weather;
  }

}

