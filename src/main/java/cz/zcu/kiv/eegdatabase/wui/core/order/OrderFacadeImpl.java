package cz.zcu.kiv.eegdatabase.wui.core.order;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

import cz.zcu.kiv.eegdatabase.data.pojo.Order;
import cz.zcu.kiv.eegdatabase.wui.core.GenericFacadeImpl;

public class OrderFacadeImpl extends GenericFacadeImpl<Order, Integer> implements OrderFacade {
    
    protected Log log = LogFactory.getLog(getClass());
    private OrderService service;
    
    public OrderFacadeImpl(OrderService orderService) {
        super(orderService);
        setService(orderService);
    }
    
    @Required
    public void setService(OrderService service) {
        this.service = service;
    }
}
