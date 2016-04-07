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

    int number;
    
    double[] timeInMachines;
    List<Period> periodWorks;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double[] getTimeInMachines() {
        return timeInMachines;
    }

    public void setTimeInMachines(double[] timeInMachines) {
        this.timeInMachines = timeInMachines;
    }

    public List<Period> getPeriodWorks() {
        return periodWorks;
    }

    public void setPeriodWorks(List<Period> periodWorks) {
        this.periodWorks = periodWorks;
    }

    public Product(int number, double[] timeInMachines) {
        this.number = number;
        this.timeInMachines = timeInMachines;
    }

   

   


    
}
