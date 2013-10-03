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
 *   GenericModel.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package org.apache.wicket.extensions.ajax.markup.html.autocomplete;

import org.apache.wicket.model.IModel;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 11.5.13
 * Time: 9:59
 */
public class GenericModel<T> implements IModel<T>, Serializable {
    protected T modelObject;

    public GenericModel(T modelObject){
        this.modelObject = modelObject;
    }

    @Override
    public T getObject() {
        return modelObject;
    }

    @Override
    public void setObject(T modelObject) {
        this.modelObject = modelObject;
    }

    @Override
    public void detach() {
    }
}
