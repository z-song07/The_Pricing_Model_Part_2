/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness.Supplier;

import TheBusiness.Business.Business;
import TheBusiness.CustomerManagement.CompareLargerTotalSpentOfSupplerForEachCustomerProfile;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.OrderManagement.MasterOrderList;
import TheBusiness.OrderManagement.Order;
import TheBusiness.OrderManagement.OrderItem;
import TheBusiness.ProductManagement.Product;
import TheBusiness.ProductManagement.ProductCatalog;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.Comparator;
import java.util.HashSet;

/**
 *
 * @author csong
 */
public class SupplierSummary {
    Supplier supplier;
    Business business;
    public SupplierSummary(Supplier s, Business b) {
        business = b;
        supplier = s;
    }
    
    public int getSupplierTotalSales() {
       return supplier.getProductCatalog().generatProductPerformanceReport().getTotalSales();
    }
    
    public Set<CustomerProfile> getUniqueCustomers() {
        Set<CustomerProfile> uniqueCustomers = new HashSet<>();
        MasterOrderList masterOrderList = business.getMasterOrderList();
        // go through all orders and check if the order contains the product from this supplier
        for (Order o: masterOrderList.getOrders()) {
            if(checkOrderContainsProduct(o)) {
                uniqueCustomers.add(o.getCustomer());
            }
        }
        return uniqueCustomers;
    }
    
    //check if the order contains the product from this supplier
    private boolean checkOrderContainsProduct(Order o) {
        ProductCatalog catalog = supplier.getProductCatalog();
        
        for (OrderItem oi: o.getOrderitems()) {
            Product p = oi.getSelectedProduct();
            if(catalog.getProductList().contains(p)) {
                return true;
            }
        }
        return false;
    }
    
    public int getNumberOfUniqueCustomers() {
       return this.getUniqueCustomers().size();
    }
    
    // in percentage
    // Number of unique customers of this supplier divided by number of all customers
    public double getLoyaltyScore(int totalCustomers) {
        double customersForThisSupplier = this.getNumberOfUniqueCustomers();
        double loyaltyScore = customersForThisSupplier / (double) totalCustomers * 100; // in percentage
        
        //round
        double roundedScore = Math.round(loyaltyScore * 100.0) /100.0;
        return roundedScore;
    }

    public String getSupplierName() {
        return supplier.getName();
    }
    

    // Total sales divided by number of unique customers
    public double getAvgSpendPerCustomer() {
        double avg = 0.0;
        double uniqueCustomers = this.getNumberOfUniqueCustomers();
        double totalsales = this.getSupplierTotalSales();
        
        // handles uniqueCustomers cannot be zero since cannot divide by 0
        if (uniqueCustomers != 0) {
            avg = totalsales / uniqueCustomers;
        }
        return Math.round(avg * 100.0) / 100.0; // convert to 2 decimals
    }
    
 
    public List<CustomerProfile> getSortedUniqueCustomers() {
        Set<CustomerProfile> uniqueCustomers = this.getUniqueCustomers();
        List<CustomerProfile> sortedUniqueCustomers = new ArrayList<>(uniqueCustomers);
        
        Comparator<CustomerProfile> compareOrderTotal = new CompareLargerTotalSpentOfSupplerForEachCustomerProfile(supplier);
        Collections.sort(sortedUniqueCustomers, compareOrderTotal);
        return sortedUniqueCustomers;
    }
    
    // in percentage
    // Total sales to top 5 Customers divided by total sales
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
            top5TotalSales = top5TotalSales + cp.getTotalSpentOfSupplier(supplier);
        }
        
        // handles totalsales cannot be zero since cannot divide by 0
        double totalsales = this.getSupplierTotalSales();
        if (totalsales != 0) {
            top5SalesScore = top5TotalSales / totalsales * 100; // percentage
        }
        
        return Math.round(top5SalesScore * 100.0) /100.0; // 2 decimals
    }


}
