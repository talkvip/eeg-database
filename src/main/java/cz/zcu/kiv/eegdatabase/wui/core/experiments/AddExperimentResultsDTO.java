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
 *   AddExperimentResultsDTO.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.core.experiments;

import cz.zcu.kiv.eegdatabase.wui.core.dto.IdentifiDTO;
import org.apache.wicket.markup.html.form.upload.FileUpload;

import java.io.File;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Prasek
 * Date: 26.3.13
 * Time: 20:23
 * To change this template use File | Settings | File Templates.
 */
public class AddExperimentResultsDTO extends IdentifiDTO implements Serializable {
    private FileUpload resultFile;

    public FileUpload getResultFile() {
        return resultFile;
    }

    public void setResultFile(FileUpload resultFile) {
        this.resultFile = resultFile;
    }

}
