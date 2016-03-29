/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brownlomicki;

import java.util.List;

/**
 *
 * @author miko
 */
public class Run {
    
    public void wypiszTab(double[] tab){
        System.out.println("________________");
        for(int i=0;i<tab.length;i++)
            System.out.println(tab[i]);
        System.out.println("________________");
    }
    public static void main(String[] args) {
        Run run=new Run();
        List<Wyrob> lista=Dane.daneWyrobow();
        BrownLomicki bl=new BrownLomicki(lista);
        bl.oblicz();
        
    }
}
