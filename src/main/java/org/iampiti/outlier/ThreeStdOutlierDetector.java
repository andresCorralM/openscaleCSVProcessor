/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.iampiti.outlier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This outlier detector considers outliers observations that are outside of 3
 * stdev from the mean
 * https://towardsdatascience.com/5-ways-to-detect-outliers-that-every-data-scientist-should-know-python-code-70a54335a623
 *
 * @author Andres
 */
public class ThreeStdOutlierDetector implements OutlierDetector {

    private double mean;
    private double stdDev;
    private double[] data;
    private double lowerLimit, upperLimit;
    private double[] outliers;
    
    @Override
    public double[] getOutliers() {
        return outliers;
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
        
        this.outliers=recalculateOutliers(data);
    }

    @Override
    public boolean isOutlier(double datum) {
        return (datum < lowerLimit || datum > upperLimit);
    }

    @Override
    public List<Double> getDataAsList() {
        List<Double> dataAsList;
                
        dataAsList=Arrays.stream(data).boxed().collect(Collectors.toList());
        return Collections.unmodifiableList(dataAsList);
    }
    
    private double[] recalculateOutliers(double[] _data){
        double[] outliersArr;
        List<Double> outliersList=new ArrayList<>();
        
        for(double datum: _data){
            if(isOutlier(datum)){
                outliersList.add(datum);
            }
        }
        
        outliersArr = Arrays.stream(outliersList.toArray()).mapToDouble(d->((Double)d).doubleValue()).toArray();
        
        return outliersArr;
    }

}
