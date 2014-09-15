package cz.zcu.kiv.eegdatabase.wui.core.order;

import java.util.List;

import cz.zcu.kiv.eegdatabase.data.pojo.Order;
import cz.zcu.kiv.eegdatabase.wui.core.GenericService;

public interface OrderService extends GenericService<Order, Integer> {
    
    List<Order> getAllOrdersForPerson(int personId);
    
    Order getOrderForDetail(int orderId);
}
