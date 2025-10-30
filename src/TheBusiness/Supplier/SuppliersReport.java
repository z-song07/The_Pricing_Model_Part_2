/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness.Supplier;

import java.util.ArrayList;

/**
 *
 * @author csong
 */
public class SuppliersReport {
    ArrayList<SupplierSummary> suppliersummarylist;
    public SuppliersReport() {
        suppliersummarylist = new ArrayList<SupplierSummary>();
    }
    
    public void addSupplierSummary(SupplierSummary ss) {
        suppliersummarylist.add(ss);
    }

    public ArrayList<SupplierSummary> getSuppliersummarylist() {
        return suppliersummarylist;
    }
    
}
