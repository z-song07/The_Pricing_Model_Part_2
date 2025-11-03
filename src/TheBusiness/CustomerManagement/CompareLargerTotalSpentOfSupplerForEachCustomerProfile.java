/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness.CustomerManagement;

import TheBusiness.Supplier.Supplier;
import java.util.Comparator;

/**
 *
 * @author csong
 */
public class CompareLargerTotalSpentOfSupplerForEachCustomerProfile implements Comparator<CustomerProfile>{

    Supplier supplier;
    public CompareLargerTotalSpentOfSupplerForEachCustomerProfile(Supplier s) {
        supplier = s;
    }
    
    @Override
    public int compare(CustomerProfile cp1, CustomerProfile cp2) {
        // compare the total price
        // largest getting sort first
        return cp2.getTotalSpentOfSupplier(supplier) - cp1.getTotalSpentOfSupplier(supplier);
    }
}
