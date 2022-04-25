package org.iampiti.outlier;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Andres
 */
public class MathUtilsTest {
    @Test
    public void meanTest(){
        double[] data={1,2,3,4,5,6};
        
        Assert.assertEquals(3.5, MathUtils.mean(data), 0.1);
    }
    
    @Test
    public void standardDeviationTest(){
        double[] data={1,2,3,4,5,6};
        
        Assert.assertEquals(1.7078, MathUtils.standardDeviation(data), 0.01);
    }
}
