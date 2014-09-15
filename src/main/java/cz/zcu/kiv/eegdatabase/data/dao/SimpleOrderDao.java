package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.pojo.Order;

public class SimpleOrderDao extends SimpleGenericDao<Order, Integer> implements OrderDao {

    public SimpleOrderDao() {
        super(Order.class);
    }

    @Override
    public Order getOrderForDetail(int orderId) {
        
            String query = "from Order o left join fetch o.person left join fetch o.items where o.id = :orderId";
            return (Order) getSessionFactory().getCurrentSession().createQuery(query).setParameter("orderId", orderId).uniqueResult();
    }
}
