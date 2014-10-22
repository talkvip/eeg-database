package cz.zcu.kiv.eegdatabase.data.dao;

import java.util.Set;

import cz.zcu.kiv.eegdatabase.data.pojo.Order;

public interface OrderDao extends GenericDao<Order, Integer> {
    
    Order getOrderForDetail(int orderId);
    
    boolean isExperimentPurchased(int experimentId, int personId);
    
    boolean isExperimentPackagePurchased(int experimentPackageId, int personId);
    
    Set<Integer> getPurchasedExperimentId(int personId);
    
    Set<Integer> getPurchasedExperimentPackageId(int personId);
}
