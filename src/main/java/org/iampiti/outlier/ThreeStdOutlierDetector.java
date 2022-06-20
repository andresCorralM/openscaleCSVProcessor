/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.iampiti.outlier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This outlier detector considers outliers observations that are outside of 3
 * stdev from the mean
 * https://towardsdatascience.com/5-ways-to-detect-outliers-that-every-data-scientist-should-know-python-code-70a54335a623
 *
 * @author Andres
 */
public class ThreeStdOutlierDetector implements OutlierDetector {

    double mean;
    double stdDev;
    double[] data;
    double lowerLimit, upperLimit;

    @Override
    public double[] getOutliers() {
        double[] outliersArr;
        List<Double> outliers=new ArrayList<>();
        
        for(double datum: data){
            if(isOutlier(datum)){
                outliers.add(datum);
            }
        }
        
        outliersArr = Arrays.stream(outliers.toArray()).mapToDouble(d->((Double)d).doubleValue()).toArray();
        
        return outliersArr;
    }

    @Override
    public void setData(double[] data) {
        double cutoff;

        this.data = Arrays.copyOf(data, data.length);

        mean = MathUtils.mean(this.data);
        stdDev = MathUtils.standardDeviation(this.data);

        cutoff = stdDev * 3;
        lowerLimit = mean - cutoff;
        upperLimit = mean + cutoff;
    }

    @Override
    public boolean isOutlier(double datum) {
        return (datum < lowerLimit || datum > upperLimit);
    }

}
