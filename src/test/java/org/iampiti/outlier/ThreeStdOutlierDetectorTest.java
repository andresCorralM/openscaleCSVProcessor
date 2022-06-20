package org.iampiti.outlier;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Andres
 */
public class ThreeStdOutlierDetectorTest {

    @Test
    public void getOutliersTest() {
        double[] data = {1, 2, 3, 5, 6, 4, 3, 8, 10, 11, 7, 5, 34};
        double[] expectedOutliers = {34};

        OutlierDetector detector = new ThreeStdOutlierDetector();
        detector.setData(data);

        double[] outliers = detector.getOutliers();
        Assert.assertArrayEquals("Outliers calculation failed", expectedOutliers, outliers, 0.1);
    }

}
