/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brownlomicki;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class DataExample {
    public static List<Product> productExample() {
        List<Product> l = new ArrayList<>();
        int[] m1 = {10, 12, 14, 6, 8};
        int[] m2 = {5, 7, 12, 10, 4};
        int[] m3 = {6, 10, 10, 8, 12};
        int[] m4 = {5, 7, 12, 4, 3};
        int[] m5 = {14, 9, 8, 14, 10};

        l.add(new Product(0, m1));
        l.add(new Product(1, m2));
        l.add(new Product(2, m3));
        l.add(new Product(3, m4));
        l.add(new Product(4, m5));

        return l;
    }
}
