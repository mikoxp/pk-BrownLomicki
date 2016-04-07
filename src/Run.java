/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import gui.MainGui;

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
