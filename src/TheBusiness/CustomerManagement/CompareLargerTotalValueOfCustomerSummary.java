/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness.CustomerManagement;

import java.util.Comparator;

/**
 *
 * @author csong
 */
public class CompareLargerTotalValueOfCustomerSummary implements Comparator<CustomerSummary>{
    
    public int compare(CustomerSummary cs1, CustomerSummary cs2) {
        // compare the total price
        // largest getting sort first
        return cs2.getOrdertotal() - cs1.getOrdertotal();
    }
}
