/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.CustomerManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author kal bugrara
 */
public class CustomersReport {
    ArrayList<CustomerSummary> customersummarylist;
    
    public CustomersReport(){
        customersummarylist = new ArrayList<CustomerSummary>();
        
    }
    
    public void addCustomerSummary(CustomerSummary cs){
        customersummarylist.add(cs);
    }
    
    // sort customer summarys by the total order value
    public ArrayList<CustomerSummary> sortCustomerByHighestTotalValue() {
        ArrayList<CustomerSummary> sortedCustomerSummaryList = new ArrayList<> (customersummarylist);
        
        Comparator sortByLargerOrderValue = new CompareLargerTotalValueOfCustomerSummary();
        Collections.sort(sortedCustomerSummaryList, sortByLargerOrderValue);
        return sortedCustomerSummaryList;
    }

    public ArrayList<CustomerSummary> getCustomersummarylist() {
        return customersummarylist;
    }
    
    
}
