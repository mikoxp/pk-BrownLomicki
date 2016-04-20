/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brownlomicki;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;


public class BrownLomickiTest {
    private BrownLomicki brownLomicki;
    public BrownLomickiTest() {
    }
    
    

    /**
     * Test of calculateCost method, of class BrownLomicki.
     */
    @Test
    public void calculateCost_correctCalc_result() {
       brownLomicki=new BrownLomicki(DataExample.productExample());
       int result=brownLomicki.calculateCost();
        assertEquals(83, result);
    }
    @Test (expected = IllegalArgumentException.class)
    public void calculateCost_ListOfProductISNULL_EXception(){
        brownLomicki=new BrownLomicki(null);
    }

     @Test (expected = IllegalArgumentException.class)
    public void calculateCost_ListOfProductISEmpty_EXception(){
        brownLomicki=new BrownLomicki(new ArrayList<Product>());
    }
    
    
}
