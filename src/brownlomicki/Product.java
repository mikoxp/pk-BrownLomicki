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
public class Product {

    private final String idName;
    private final int number;
    private int[] timeInMachines;
    private List<Period> periodWorks;

    public Product(int number, int[] timeInMachines) {
        this.number = number;
        this.timeInMachines = timeInMachines;
        this.idName="";
    }

    public Product(String idName, int number, int[] timeInMachines) {
        this.idName = idName;
        this.number = number;
        this.timeInMachines = timeInMachines;
    }

    public int getNumber() {
        return number;
    }
    
    public int[] getTimeInMachines() {
        return timeInMachines;
    }

    public void setTimeInMachines(int[] timeInMachines) {
        this.timeInMachines = timeInMachines;
    }

    public List<Period> getPeriodWorks() {
        return periodWorks;
    }

    public void setPeriodWorks(List<Period> periodWorks) {
        this.periodWorks = periodWorks;
    }

}
