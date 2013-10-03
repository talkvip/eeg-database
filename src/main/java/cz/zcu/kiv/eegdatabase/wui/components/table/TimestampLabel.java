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
 *   TimestampLabel.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.components.table;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.wicket.markup.html.basic.Label;

import com.ibm.icu.text.SimpleDateFormat;

import cz.zcu.kiv.eegdatabase.wui.app.session.EEGDataBaseSession;

/**
 * Simple Label with formater for Timestamp object.
 * 
 * @author Jakub Rinkes
 * 
 */
public class TimestampLabel extends Label {

    private static final long serialVersionUID = -6164869657967617094L;

    public TimestampLabel(String id, Timestamp object, String formatPatter) {
        super(id,
                object == null ? "" : new SimpleDateFormat(formatPatter, EEGDataBaseSession.get().getLocale()).format((Date) object));
    }
}
