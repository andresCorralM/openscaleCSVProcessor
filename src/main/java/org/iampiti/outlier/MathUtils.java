package org.iampiti.outlier;

import java.util.Arrays;
import java.util.OptionalDouble;

/**
 * used by ThreeStdOutlierDetector
 *
 * @author Andres
 */
public class MathUtils {

    public static double mean(double[] data) {
        OptionalDouble averageResult;
        final double result;

        averageResult = Arrays.stream(data).average();

        result = averageResult.getAsDouble();

        return result;
    }

    public static double standardDeviation(double[] data) {
        final double mean = mean(data);
        double[] dataPointsDeviations;
        final double variance;
        final double stdDev;

        dataPointsDeviations = Arrays.stream(data).map(e -> Math.pow((e - mean), 2)).toArray();
        variance = mean(dataPointsDeviations);
        stdDev = Math.sqrt(variance);

        return stdDev;
    }
}
