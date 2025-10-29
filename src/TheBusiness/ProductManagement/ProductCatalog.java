/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.ProductManagement;

import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.Supplier.Supplier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author kal bugrara
 */
public class ProductCatalog {

    String type;
    ArrayList<Product> products; //list of products initially empty
    String supplierName;
    Set<CustomerProfile> uniquecustomers;
//    public ProductCatalog(String n) {
//        type = n;
//        products = new ArrayList();  ///create the list of elements otherwise it is null
//    }
//// new ProductCatalog(); or new ProductCatalog("Printers");
//    public ProductCatalog(    ) {
//        type = "unknown";
//        products = new ArrayList();
//    }
    
    // pass in the supplier
    public ProductCatalog(String s,String t) {
        type = t;
        products = new ArrayList();  ///create the list of elements otherwise it is null
        supplierName = s;
        uniquecustomers = new HashSet<>();
        
    }
    
    public ProductCatalog(String s) {
        type = "unknown";
        products = new ArrayList();  ///create the list of elements otherwise it is null
        supplierName = s;
        uniquecustomers = new HashSet<>();
    }
    
    public Product newProduct(int fp, int cp, int tp) {
        Product p = new Product(supplierName, fp, cp, tp);
        products.add(p);
        return p;
    }
    public Product newProduct(String n, int fp, int cp, int tp) {
        Product p = new Product(supplierName, n,fp, cp, tp);
        products.add(p);
        return p;
    }

    public ProductsReport generatProductPerformanceReport() {
        ProductsReport productsreport = new ProductsReport();

        for (Product p : products) {

            ProductSummary ps = new ProductSummary(p);
            productsreport.addProductSummary(ps);
        }
        return productsreport;
    }

    public ArrayList<Product> getProductList(){
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    
    public Set<CustomerProfile> getUniqueCustomers() {
        for (Product p: products) {
            uniquecustomers.addAll(p.getUniqueCustomers());
        }
        
        return uniquecustomers;
    }
}
