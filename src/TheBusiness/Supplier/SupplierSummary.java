/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness.Supplier;

import TheBusiness.CustomerManagement.CompareLargerTotalPricePerformanceOfCustomerProfile;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.ProductManagement.ProductCatalog;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.Comparator;

/**
 *
 * @author csong
 */
public class SupplierSummary {
    Supplier supplier;
    ProductCatalog productCatalog;
    Set<CustomerProfile> uniqueCustomers;
    double totalsales;
    public SupplierSummary(Supplier s) {
        supplier = s;
        productCatalog = s.getProductCatalog();
        totalsales = productCatalog.generatProductPerformanceReport().getTotalSales();
    }
    
    public double getSupplierTotalSales() {
       return totalsales;
    }
    
    public int getNumberOfUniqueCustomers() {
       int count = productCatalog.getUniqueCustomers().size();
       return count;
    }
    
    // in percentage
    public double getLoyaltyScore(int totalCustomers) {
        double customersForThisSupplier = this.getNumberOfUniqueCustomers();
        double loyaltyScore = customersForThisSupplier / totalCustomers * 100;
        
        double roundedScore = Math.round(loyaltyScore * 100.0) /100.0;
        return roundedScore;
    }

    public String getSupplierName() {
        return supplier.getName();
    }
    
    public double getAvgSpendPerCustomer() {
        double avg = 0.0;
        double uniqueCustomers = this.getNumberOfUniqueCustomers();
        
        // handles uniqueCustomers cannot be zero since cannot divide by 0
        if (uniqueCustomers != 0) {
            avg = totalsales / uniqueCustomers;
        }
        return Math.round(avg * 100) / 100; // 2 decimals
    }
    
 
    public List<CustomerProfile> getSortedUniqueCustomers() {
        uniqueCustomers = productCatalog.getUniqueCustomers();
        List<CustomerProfile> sortedUniqueCustomers = new ArrayList<>(uniqueCustomers);
        
        Comparator compareOrderTotal = new CompareLargerTotalPricePerformanceOfCustomerProfile();
        Collections.sort(sortedUniqueCustomers, compareOrderTotal);
        return sortedUniqueCustomers;
    }
    
    // in percentage
    public double getTop5SalesScore() {
        double top5SalesScore = 0.0;
        double top5TotalSales = 0.0;
        List<CustomerProfile> top5Customers = Collections.emptyList(); // to avoid Null Pointer Exception
        List<CustomerProfile> sortedCustomers = this.getSortedUniqueCustomers();
        // check the list size is it  > 5
        if(sortedCustomers.size() > 5) {
            top5Customers = sortedCustomers.subList(0, 5); // index 5 excluded
        } else {
            top5Customers = sortedCustomers;
        }
        
        for (CustomerProfile cp: top5Customers) {
            top5TotalSales = top5TotalSales + cp.getTotalPricePerformance();
        }
        
        // handles totalsales cannot be zero since cannot divide by 0
        if (totalsales != 0) {
            top5SalesScore = top5TotalSales / totalsales * 100; // percentage
        }
        
        return Math.round(top5SalesScore * 100) /100; // 2 decimals
    }
}
