/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brownlomicki;

/**
 *
 * @author miko
 */
public class Wyrob {
    int numer;
    double[] czasyNaMaszynach;

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public double[] getCzasyNaMaszynach() {
        return czasyNaMaszynach;
    }

    public void setCzasyNaMaszynach(double[] czasyNaMaszynach) {
        this.czasyNaMaszynach = czasyNaMaszynach;
    }

    public Wyrob(int numer, double[] czasyNaMaszynach) {
        this.numer = numer;
        this.czasyNaMaszynach = czasyNaMaszynach;
    }
    
}
