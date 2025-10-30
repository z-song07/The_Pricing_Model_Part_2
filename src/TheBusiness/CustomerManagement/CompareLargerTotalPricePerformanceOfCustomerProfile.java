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
public class CompareLargerTotalPricePerformanceOfCustomerProfile implements Comparator<CustomerProfile>{
    
    public int compare(CustomerProfile cp1, CustomerProfile cp2) {
        // compare the total price
        // largest getting sort first
        return cp2.getTotalPricePerformance() - cp1.getTotalPricePerformance();
    }
}
