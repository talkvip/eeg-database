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
 *   FileUtils.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.components.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.util.io.IOUtils;

import cz.zcu.kiv.eegdatabase.wui.components.form.input.FileDownloadStreamWriter;
import cz.zcu.kiv.eegdatabase.wui.core.file.FileDTO;

/**
 * Utilities class for files.
 * 
 * @author Jakub Rinkes
 * 
 */
public class FileUtils {

    protected static Log log = LogFactory.getLog(FileUtils.class);

    /**
     * Prepared request handler for file download.
     * 
     * @param file
     * @return
     */
    public static ResourceStreamRequestHandler prepareDownloadFile(final FileDTO file) {

        if (file == null || file.getFile() == null)
            return null;

        FileDownloadStreamWriter stream = new FileDownloadStreamWriter(file.getFile(), file.getMimetype());

        return new ResourceStreamRequestHandler(stream).setFileName(file.getFileName());
    }
    
    /**
     * Prepare byte array from file.
     * 
     * @param file
     * @return
     */
    public static byte[] getFileContent(File file) {

        if (file == null) {
            return new byte[0];
        } else {
            try {
                
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] byteArray = IOUtils.toByteArray(fileInputStream);
                fileInputStream.close();
                return byteArray;
                
            } catch (FileNotFoundException e) {
                log.error(e.getMessage(), e);
                return new byte[0];
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                return new byte[0];
            }
        }

    }
}
