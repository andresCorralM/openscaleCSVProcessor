/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.iampiti.outlier;


/**
 * This outlier detector considers outliers observations that are outside of 3 stdev from the mean
 * @author Andres
 */
public class ThreeStdOutlierDetector implements OutlierDetector{

    @Override
    public double[] getOutliers(double[] data){
        double[] outliers={};
        final double mean;
        final double stdDev;
        
        mean=MathUtils.mean(data);
        stdDev=MathUtils.standardDeviation(data);
        
        return outliers;
    }
    
}
