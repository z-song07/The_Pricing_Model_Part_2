/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserInterface.Main;

/**
 *
 * @author csong
 */
// import untility packages
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

import MarketingManagement.MarketingPersonDirectory;
import MarketingManagement.MarketingPersonProfile;
import TheBusiness.Business.Business;
import TheBusiness.CustomerManagement.CustomerDirectory;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.OrderManagement.MasterOrderList;
import TheBusiness.OrderManagement.Order;
import TheBusiness.OrderManagement.OrderItem;
import TheBusiness.Personnel.Person;
import TheBusiness.Personnel.PersonDirectory;
import TheBusiness.ProductManagement.Product;
import TheBusiness.ProductManagement.ProductCatalog;
import TheBusiness.SalesManagement.SalesPersonDirectory;
import TheBusiness.SalesManagement.SalesPersonProfile;
import TheBusiness.Supplier.Supplier;
import TheBusiness.Supplier.SupplierDirectory;
import TheBusiness.UserAccountManagement.UserAccount;
import TheBusiness.UserAccountManagement.UserAccountDirectory;


public class ConfigureANewBusiness {
   
    // random generator
    private static final Random RandomGenerator = new Random();
    
    static Business initialize() {
        Business business = new Business("Xerox");
        SupplierDirectory supplierdirectory = business.getSupplierDirectory();
        PersonDirectory personDirectory = business.getPersonDirectory();
        SalesPersonDirectory salesPersonDirectory = business.getSalesPersonDirectory();
        CustomerDirectory customerDirectory = business.getCustomerDirectory();
        MasterOrderList masterOrderList = business.getMasterOrderList();
        
        // generate 50 random suppliers
        ArrayList<Boolean> RandomSupplierPicker = randomUniquePicksGenerator(30, 50); 
        //System.out.println(RandomSupplierPicker.toString());
        for (int i = 1; i <= 50; i++) {
            Supplier supplier = supplierdirectory.newSupplier("Supplier " + i);
            ProductCatalog catalog = supplier.getProductCatalog();

            // generate 50 products for 30 selected suppliers
            // check if the supplier is getting chosen to add product
            if (RandomSupplierPicker.get(i - 1)) {
                for (int j = 1; j <= 50; j++) {
                    String productName = "Product " + i + "-" + j;

                    int floorPrice = 1000 + (int)(Math.random() * 1000);// 1000 to 1999

                    int ceilingPrice = floorPrice + 1000 + (int)(Math.random() * 2000);//above floorPrice

                    int targetPrice = floorPrice + (int)((ceilingPrice - floorPrice) / 2);
                    Product newProduct = catalog.newProduct(productName, floorPrice, ceilingPrice, targetPrice);
                    System.out.println("I am product #" + j);
                }
            }
        }
        

        // generate 5 salespersons
        for (int i = 1; i <=5; i++) {
            Person newPerson = personDirectory.newPerson("Salesperson " + i);
            salesPersonDirectory.newSalesPersonProfile(newPerson);
        }

        // get salesperson directory size
        int salespersonDirectorySize = salesPersonDirectory.getSalespersonlist().size();
        // to make it easier to view all customers, I will assign them to one salesperson
        SalesPersonProfile selectedSalesPerson = salesPersonDirectory.getSalespersonlist().get(0);
        
        // generate 300 customers
        // make 300 people, 300 Customer profiles
        for (int i=1; i <= 300; i++) {
            Person newPerson = personDirectory.newPerson("Customer " + i);
            CustomerProfile newCustomer = customerDirectory.newCustomerProfile(newPerson);
            
            // generate 1-3 orders for each customer
            int numOrderIndexBound = RandomGenerator.nextInt(3);
            for (int j = 0; j <= numOrderIndexBound; j++) {
                // get random salesperson
                // int salesPersonIndex = RandomGenerator.nextInt(salespersonDirectorySize);
                // SalesPersonProfile selectedSalesPerson = salesPersonDirectory.getSalespersonlist().get(salesPersonIndex);
                
                // create order
                //Order newOrder = masterOrderList.newOrder(newCustomer, selectedSalesPerson);
                Order newOrder = masterOrderList.newOrder(newCustomer, selectedSalesPerson);
                
                //add up to 10 order items
                int numOrderItemIndexBound = RandomGenerator.nextInt(10);
                for (int k = 0; k <= numOrderIndexBound; k++) {
                    // there are 50 suppliers
                    // need to re-select if suppliers product list is empty
                    int productCatalogLength = 0;
                    Supplier selectedSupplier;
                    do {
                        int selectedSupplierIndex = RandomGenerator.nextInt(50);
                        selectedSupplier = supplierdirectory.getSupplierList().get(selectedSupplierIndex);
                        productCatalogLength = selectedSupplier.getProductCatalog().getProductList().size();
                    } while (productCatalogLength == 0);


                    ProductCatalog catalog = selectedSupplier.getProductCatalog();
                    // need to pick a product out of 50 products
                    int selectedProductIndex = RandomGenerator.nextInt(50);
                    Product selectedProduct = catalog.getProductList().get(selectedProductIndex);
                    
                    // actual price is within product price range
                    int randomActualPrice = RandomGenerator.nextInt(selectedProduct.getFloorPrice(), selectedProduct.getCeilingPrice()+ 1); 
                    int randomQuantity = RandomGenerator.nextInt(1, 6); // 1-5 quantities
                 
                    OrderItem newOrderItem = newOrder.newOrderItem(selectedProduct, randomActualPrice, randomQuantity);
                    
                }
            }
        
        
        }
        
        // Create User accounts that link to specific profiles
        UserAccountDirectory uadirectory = business.getUserAccountDirectory();
        UserAccount ua1 = uadirectory.newUserAccount(selectedSalesPerson, "Sales", "XXXX"); /// order products for one of the customers and performed by a sales person
        
        return business;
    }
    
    
    // choosing pickNum of elements out of totalNum of elements
    // pickNum is number of elements getting choose, totalNum is the total number of elements
    static ArrayList<Boolean> randomUniquePicksGenerator(int pickNum, int totalNum) {
        int excludedNum = totalNum - pickNum; // excludedNum is number of element excluded
        ArrayList<Boolean> randomPicks = new ArrayList<Boolean>();

        // only generate the random picks arraylist with booleans if excludedNum is > 0
        if(excludedNum > 0) {
            // generate the picks
            for (int i = 0; i < pickNum; i++) {
                randomPicks.add(true);
            }

            // generate for excludes
            for (int i = 0; i <excludedNum; i++ ) {
                randomPicks.add(false);
            }

            // shuffle the randomPicks list
            Collections.shuffle(randomPicks);
        }
        return randomPicks;
    }
   
}
