package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.pojo.Order;

public class SimpleOrderDao extends SimpleGenericDao<Order, Integer> implements OrderDao {

    public SimpleOrderDao() {
        super(Order.class);
    }
}
