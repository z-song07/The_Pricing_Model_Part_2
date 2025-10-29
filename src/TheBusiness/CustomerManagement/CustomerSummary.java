/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.CustomerManagement;

import TheBusiness.OrderManagement.Order;

/**
 *
 * @author kal bugrara
 */
public class CustomerSummary {
    CustomerProfile customer;
    int ordertotal;
    
    public CustomerSummary(CustomerProfile cp){
        customer = cp;
        ordertotal = cp.getTotalPricePerformance();
    }

    public int getOrdertotal() {
        return ordertotal;
    }

    public CustomerProfile getCustomer() {
        return customer;
    }
    
    
}
