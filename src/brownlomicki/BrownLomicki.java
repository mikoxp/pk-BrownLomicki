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
    private List<Product> startList;
    private int[] precedingCostTime; //poprzedzajacy
    private final List<Product> optimalOrder;
    private int totalCost;

    /**
     *
     * @param productList lista produktow
     */
    public BrownLomicki(List<Product> productList) {
        if (productList==null ||productList.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.productList = productList;
        numberOfMachine = productList.get(0).getTimeInMachines().length;
        numberOfProduct = productList.size();
        precedingCostTime = new int[numberOfMachine];
        for (int i = 0; i < numberOfMachine; i++) {
            precedingCostTime[i] = 0;
        }

        optimalOrder = new ArrayList<>();
        startList=new ArrayList();
        for(Product p:productList){
            startList.add(p);
        }
        totalCost=0;
    }

    /**
     *
     * @return koszt czasowy wykonania wszystkich wyrobów
     */
    public int calculateCost() {
        List<Integer> W;
        List<int[]> beginG = null;
        int result;
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
        result = beginG.get(index)[numberOfMachine - 1];
        totalCost=result;
        return result;
    }

    /**
     *
     * @param product produkt
     * @param endOperation koniec operacji
     * @return produkt z okresami pracy
     */
    private Product addPeriodWorks(Product product, int[] endOperation) {
        List<Period> periods = new ArrayList();
        Period period;
        for (int i = 0; i < numberOfMachine; i++) {
            period = new Period(endOperation[i] - product.getTimeInMachines()[i], endOperation[i]);
            periods.add(period);
        }
        product.setPeriodWorks(periods);
        return product;
    }

    /**
     *
     * @param numberOfProduct numer produktu
     * @param gStart poczatkowe wartosci g
     * @return wspóczynnik produktu
     */
    private int calculateProfitabelityOfProduct(int numberOfProduct, int[] gStart) {
        int result;
        List<Integer> listOfG = new ArrayList<>();
        int g;
        //i kolumna
        for (int i = 0; i < numberOfMachine; i++) {
            g = gStart[i];
            //reszta z wiersza
            for (int j = 0; j < productList.size(); j++) {
                if (numberOfProduct != j) {
                    g += productList.get(j).getTimeInMachines()[i];
                }
            }
            g += calculateRemainingCosts(numberOfProduct, i);
            listOfG.add(g);
        }
        result = Collections.max(listOfG);
        return result;
    }

    /**
     *
     * @param numberOfProduct numer produktu
     * @param numberOfMachine numer maszyny
     * @return minimalny pozostaly koszty
     */
    private int calculateRemainingCosts(int numberOfProduct, int numberOfMachine) {
        int result = 0;
        int nextElement;
        List<Integer> listOfRemaingCost = new ArrayList<>();
        int[] machines;
        for (int i = 0; i < productList.size(); i++) {
            nextElement = 0;
            if (numberOfProduct != i) {
                machines = productList.get(i).getTimeInMachines();
                for (int j = numberOfMachine + 1; j < machines.length; j++) {
                    nextElement += machines[j];
                }
                listOfRemaingCost.add(nextElement);
            }
        }
        //wybieramy minimalny
        if (!listOfRemaingCost.isEmpty()) {
            result = Collections.min(listOfRemaingCost);
        }
        return result;
    }

    /**
     * Liczy poczatkowe koszty czasowe(czasy poprzednich operacji i bierzacej)
     *
     * @return liste poczatkow
     */
    private List<int[]> calculateBeginG() {
        List<int[]> listOfG = new ArrayList<>();
        int[] machines;
        int[] g;
        for (int i = 0; i < productList.size(); i++) {
            machines = productList.get(i).getTimeInMachines();
            g = new int[numberOfMachine];
            g[0] = precedingCostTime[0] + machines[0];
            for (int j = 1; j < numberOfMachine; j++) {
                if (g[j - 1] > precedingCostTime[j]) {
                    g[j] = g[j - 1] + machines[j];
                } else {
                    g[j] = precedingCostTime[j] + machines[j];
                }
            }
            listOfG.add(g);
        }
        return listOfG;
    }

    public int getNumberOfMachine() {
        return numberOfMachine;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }

    public List<Product> getOptimalOrder() {
        return optimalOrder;
    }

    public List<Product> getStartList() {
        return startList;
    }

    public int getTotalCost() {
        return totalCost;
    }

}
