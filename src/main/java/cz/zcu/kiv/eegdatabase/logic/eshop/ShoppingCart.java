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
 *   ShoppingCart.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.logic.eshop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cz.zcu.kiv.eegdatabase.data.pojo.Experiment;
import cz.zcu.kiv.eegdatabase.data.pojo.Order;
import cz.zcu.kiv.eegdatabase.data.pojo.OrderItem;

/**
 * Object of ShoppingCart. Keeps track of experiments placed in an order, order's total price. Provides basic methods
 * for manipulation with its content - add/remove experiment.
 * User: jfronek
 * Date: 4.3.2013
 */
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 9082679939344208647L;
    
    private Order order;
    
    public ShoppingCart() {
        
        this.order = new Order();
    }

    public List<Experiment> getOrder() {
        
        List<Experiment> list = new ArrayList<Experiment>();
        
        for(OrderItem item : order.getItems())
            list.add(item.getExperiment());
        
        return list;
    }

    public double getTotalPrice(){
        BigDecimal total = BigDecimal.ZERO;
        for(OrderItem item : order.getItems()){
            total = total.add(item.getPrice());
        }
        return total.doubleValue(); // TODO change double to bigdecimal
    }

    public void addToCart(Experiment experiment){
        // Each experiment can be put into cart only once.
        if(!isInCart(experiment)){
            order.getItems().add(new OrderItem(experiment));
        }
    }

    public boolean isInCart(Experiment experiment){
        for(OrderItem ex : order.getItems()){
            if(experiment.getExperimentId() == ex.getExperiment().getExperimentId()){
                return true;
            }
        }
        return false;
    }

    public void removeFromCart(Experiment experiment){
        for(int index = 0; index < order.getItems().size(); index++){
            if(experiment.getExperimentId() == order.getItems().get(index).getExperiment().getExperimentId()){
                order.getItems().remove(index);
            }
        }
    }
    public boolean isEmpty(){
        return order.getItems().isEmpty();
    }

    public int size(){
        return order.getItems().size();
    }


}
