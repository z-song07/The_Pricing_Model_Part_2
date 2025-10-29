/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness.Supplier;

import TheBusiness.ProductManagement.ProductCatalog;

/**
 *
 * @author csong
 */
public class SupplierSummary {
    Supplier supplier;
    ProductCatalog productCatalog;
    public SupplierSummary(Supplier s) {
        supplier = s;
        productCatalog = s.getProductCatalog();   
    }
    
    public int getSupplierTotalSales() {
       int totalsales = productCatalog.generatProductPerformanceReport().getTotalSales();
       return totalsales;
    }
    
    public int getNumberOfUniqueCustomers() {
       int count = productCatalog.getUniqueCustomers().size();
       return count;
    }
    
    public float getLoyaltyScore(int totalCustomers) {
        int customersForThisSupplier = this.getNumberOfUniqueCustomers();
        float loyaltyScore = customersForThisSupplier / totalCustomers;
        return loyaltyScore;
    }
}
