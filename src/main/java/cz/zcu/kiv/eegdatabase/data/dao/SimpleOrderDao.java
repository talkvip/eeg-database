package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.pojo.Order;

public class SimpleOrderDao extends SimpleGenericDao<Order, Integer> implements OrderDao {

    public SimpleOrderDao() {
        super(Order.class);
    }

    @Override
    public Order getOrderForDetail(int orderId) {
        
            String query = "from Order o "
                    + "left join fetch o.person "
                    + "left join fetch o.items as items "
                    + "left join fetch items.experiment as exp "
                    + "left join fetch items.experimentPackage as pck "
                    + "left join fetch exp.scenario "
                    + "left join fetch pck.researchGroup "
                    + "where o.id = :orderId";
            return (Order) getSessionFactory().getCurrentSession().createQuery(query).setParameter("orderId", orderId).uniqueResult();
    }
}
