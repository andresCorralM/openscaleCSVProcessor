package org.iampiti.outlier;

import java.util.List;


/**
 * Implementations specialise in detecting anomalous data (outliers) for a certain category of data (e.g. weight)
 * Since a measurement can contain certain elements that are valid and some invalid the aim is not to
 * eliminate the whole measurement whenever we detect a column that isn't valid but to correct the invalid value somehow
 * (e.g.) keep the value from the previous measurement
 * @author Andres
 */
public interface OutlierDetector {
    /**
     * Sets the dataset to be used by this instance
     */
    void setData(double[] data);
    /**
     * Returns a read-only view of the data set in the instance
     */
    List<Double> getDataAsList();
    /**
     * Return whether this particular datum is an outlier
     */
    boolean isOutlier(double datum);
    double[] getOutliers();
}
