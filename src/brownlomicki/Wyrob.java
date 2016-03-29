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
public class Wyrob {

    int numer;
    
    double[] czasyNaMaszynach;
    List<Okres> czasyPracy;

    public List<Okres> getCzasyPracy() {
        return czasyPracy;
    }

    public void setCzasyPracy(List<Okres> czasyPracy) {
        this.czasyPracy = czasyPracy;
    }

    public Wyrob(int numer, double[] czasyNaMaszynach) {
        this.numer = numer;
        this.czasyNaMaszynach = czasyNaMaszynach;
    }

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


    
}
