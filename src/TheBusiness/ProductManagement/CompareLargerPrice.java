/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness.ProductManagement;

import java.util.Comparator;

/**
 *
 * @author csong
 */

public class CompareLargerPrice implements Comparator<ProductSummary> {
        public int compare(ProductSummary p1, ProductSummary p2) {
//            if (p1.getCeillingPrice() > p2.getCeillingPrice()) {
//                return -1; // the 1st is larger so it goes first
//            }
//            
//            if (p1.getCeillingPrice() < p2.getCeillingPrice()) {
//                return 1; // the 2nd is larger so it goes first
//            }
//            return 0;
            // return negative when p1 > p2, return positive when p2> p1, return 0 when equal and order doesn't matter
            return p2.getCeillingPrice() - p1.getCeillingPrice();
        }
}