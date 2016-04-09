/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import brownlomicki.BrownLomicki;
import brownlomicki.Product;
import gui.MainGui;
import java.util.List;

/**
 *
 * @author miko
 */
public class Run {
    
    public static void main(String[] args) {
       
//        List<Product> lista=Dane.daneWyrobow();
//        BrownLomicki bl=new BrownLomicki(lista);
//        double koszt=bl.calculateCost();
//        System.out.println("K "+koszt);
        MainGui m=new MainGui();
        m.setVisible(true);
    }
}
