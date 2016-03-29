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
 * Wykonuje algorytm browna-lomickiego
 *
 * @author Michal Oles
 */
public class BrownLomicki {

    private final int numberOfMachine; //ilosc maszyn
    private final int numberOfProduct;
    private final List<Product> productList;

    private double[] precedingCostTime; //poprzedzajacy
    private final List<Product> optimalOrder;

    /**
     *
     * @param productList lista produktow
     */
    public BrownLomicki(List<Product> productList) {
        this.productList = productList;
        numberOfMachine = productList.get(0).timeInMachines.length;
        numberOfProduct = productList.size();
        precedingCostTime = new double[numberOfMachine];
        for (int i = 0; i < numberOfMachine; i++) {
            precedingCostTime[i] = 0;
        }
        optimalOrder = new ArrayList<>();
    }

    public double calculateCost() {
        List<Double> W;
        List<double[]> beginG = null;
        int index = 0;
        // 1 obrot na kazdy produkt
        for (int j = 0; j < numberOfProduct; j++) {
            W = new ArrayList<>();
            //wyznaczanie nastepnego najlepszego produktu do wykonania
            for (int i = 0; i < productList.size(); i++) {

                beginG = calculateBeginG();
                W.add(calculateProfitabelityOfProduct(i, beginG.get(i)));
            }
            index = W.indexOf(Collections.min(W));
            optimalOrder.add(addPeriodWorks(productList.get(index), beginG.get(index)));
            precedingCostTime = beginG.get(index);
            productList.remove(index);
        }
        //System.out.println(optimalOrder);
        return beginG.get(index)[numberOfMachine - 1];
    }

    private Product addPeriodWorks(Product w, double[] konce) {
        List<Period> periods = new ArrayList();
        Period period;
        for (int i = 0; i < numberOfMachine; i++) {
            period = new Period(konce[i] - w.timeInMachines[i], konce[i]);
            periods.add(period);
        }
        w.setPeriodWorks(periods);
        return w;
    }

    private double calculateProfitabelityOfProduct(int numerWyrobu, double[] gPocz) {
        double result = 0;

        List<Double> listaG = new ArrayList<>();
        double g;
        //i kolumna
        for (int i = 0; i < numberOfMachine; i++) {
            g = gPocz[i];
            //reszta z wiersza
            for (int j = 0; j < productList.size(); j++) {
                if (numerWyrobu != j) {
                    g += productList.get(j).getTimeInMachines()[i];
                }
            }
            g += calculateRemainingCosts(numerWyrobu, i);
            listaG.add(g);
        }
        result = Collections.max(listaG);
        return result;
    }

    private double calculateRemainingCosts(int numerWyrobu, int numerMaszyny) {
        double wynik = 0, nextElement = 0;
        List<Double> lista = new ArrayList<>();
        double[] machines;
        for (int i = 0; i < productList.size(); i++) {
            nextElement = 0;
            if (numerWyrobu != i) {
                machines = productList.get(i).getTimeInMachines();
                for (int j = numerMaszyny + 1; j < machines.length; j++) {
                    nextElement += machines[j];
                }
                lista.add(nextElement);
            }
        }
        if (!lista.isEmpty()) {
            wynik = Collections.min(lista);
        }
        return wynik;
    }

    private List<double[]> calculateBeginG() {
        List<double[]> lista = new ArrayList<>();
        double[] maszyny;
        double[] g;
        for (int i = 0; i < productList.size(); i++) {
            maszyny = productList.get(i).getTimeInMachines();
            g = new double[numberOfMachine];
            g[0] = precedingCostTime[0] + maszyny[0];
            for (int j = 1; j < numberOfMachine; j++) {
                if (g[j - 1] > precedingCostTime[j]) {
                    g[j] = g[j - 1] + maszyny[j];
                } else {
                    g[j] = precedingCostTime[j] + maszyny[j];
                }
            }
            lista.add(g);
        }
        return lista;
    }

    public int getNumberOfMachine() {
        return numberOfMachine;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }
}
