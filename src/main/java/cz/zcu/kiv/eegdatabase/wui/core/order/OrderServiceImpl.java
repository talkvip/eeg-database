package cz.zcu.kiv.eegdatabase.wui.core.order;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

import cz.zcu.kiv.eegdatabase.data.dao.OrderDao;
import cz.zcu.kiv.eegdatabase.data.pojo.Order;
import cz.zcu.kiv.eegdatabase.wui.core.GenericServiceImpl;

public class OrderServiceImpl extends GenericServiceImpl<Order, Integer> implements OrderService {
    
    protected Log log = LogFactory.getLog(getClass());
    private OrderDao dao;
    
    public OrderServiceImpl(OrderDao orderDao) {
        super(orderDao);
        setDao(orderDao);
    }
    
    public void setDao(OrderDao dao) {
        this.dao = dao;
    }
}
