/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brownlomicki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author miko
 */
public class BrownLomicki {

    private final int liczbaMaszyn;
    private final int liczbaWyrobow;
    private final List<Wyrob> wyroby;

    private double[] pop;
    private final List<Wyrob> kolejnosc;

    public BrownLomicki(List<Wyrob> wyroby) {
        this.wyroby = wyroby;
        liczbaMaszyn = wyroby.get(0).czasyNaMaszynach.length;
        liczbaWyrobow = wyroby.size();
        pop = new double[liczbaMaszyn];
        for (int i = 0; i < liczbaMaszyn; i++) {
            pop[i] = 0;
        }
        kolejnosc = new ArrayList<>();
    }

    public int getLiczbaMaszyn() {
        return liczbaMaszyn;
    }

    public int getLiczbaWyrobow() {
        return liczbaWyrobow;
    }

    public void oblicz() {
        List<Double> W;
        List<double[]> gPocz = null;
        int index = -1;
// i kolumna
        for (int j = 0; j < liczbaWyrobow; j++) {
            W = new ArrayList<>();
            for (int i = 0; i < wyroby.size(); i++) {

                gPocz = obliczPoczG();
                W.add(obliczWyrob(i, gPocz.get(i)));
            }
            index=W.indexOf(Collections.min(W));
            kolejnosc.add(dodajOkresyPracy(wyroby.get(index), gPocz.get(index)));
            pop=gPocz.get(index);
            wyroby.remove(index);
        }
       System.out.println(kolejnosc);
    }
     private Wyrob dodajOkresyPracy(Wyrob w,double[] konce){
         List<Okres> okresy=new ArrayList();
         Okres o;
         for(int i=0;i<liczbaMaszyn;i++){
             o=new Okres(konce[i]-w.czasyNaMaszynach[i], konce[i]);
             okresy.add(o);
         }
         w.setCzasyPracy(okresy);
         return w;
     }
    private double obliczWyrob(int numerWyrobu, double[] gPocz) {
        double wynik = 0;
        
        List<Double> listaG = new ArrayList<>();
        double g;
        //i kolumna
        for (int i = 0; i < liczbaMaszyn; i++) {
            g = gPocz[i];
            //reszta z wiersza
            for (int j = 0; j < wyroby.size(); j++) {
                if (numerWyrobu != j) {
                    g += wyroby.get(j).getCzasyNaMaszynach()[i];
                }
            }
            g += obliczMinReszty(numerWyrobu, i);
            listaG.add(g);
        }
        wynik = Collections.max(listaG);
        return wynik;
    }

    private double obliczMinReszty(int numerWyrobu, int numerMaszyny) {
        double wynik = 0, kolejny = 0;
        List<Double> lista = new ArrayList<>();
        double[] maszyny;
        for (int i = 0; i < wyroby.size(); i++) {
            kolejny = 0;
            if (numerWyrobu != i) {
                maszyny = wyroby.get(i).getCzasyNaMaszynach();
                for (int j = numerMaszyny + 1; j < maszyny.length; j++) {
                    kolejny += maszyny[j];
                }
                lista.add(kolejny);
            }
        }
        if(!lista.isEmpty()){
            wynik = Collections.min(lista);
        }
        return wynik;
    }

    private List<double[]> obliczPoczG() {
        List<double[]> lista = new ArrayList<>();
        double[] maszyny;
        double[] g;
        for (int i = 0; i < wyroby.size(); i++) {
            maszyny = wyroby.get(i).getCzasyNaMaszynach();
            g = new double[liczbaMaszyn];
            g[0] = pop[0] + maszyny[0];
            for (int j = 1; j < liczbaMaszyn; j++) {
                if (g[j - 1] > pop[j]) {
                    g[j] = g[j - 1] + maszyny[j];
                } else {
                    g[j] = pop[j] + maszyny[j];
                }
            }
            lista.add(g);
        }
        return lista;
    }

}
