package cz.zcu.kiv.eegdatabase.wui.core.order;

import java.util.List;

import cz.zcu.kiv.eegdatabase.data.pojo.Order;
import cz.zcu.kiv.eegdatabase.wui.core.GenericFacade;

public interface OrderFacade extends GenericFacade<Order, Integer> {
    
    List<Order> getAllOrdersForPerson(int personId);
    
    Order getOrderForDetail(int orderId);
}
