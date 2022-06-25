package org.iampiti.processor;

import java.util.List;
import org.apache.commons.csv.CSVRecord;
import org.iampiti.outlier.OutlierDetector;
import org.iampiti.util.CSVRecordUtils;

/**
 *
 * @author Andres
 */
public class RemoveOutliersProcessor implements CSVProcessor {
    
    private final OutlierDetector outlierDetector;
    
    public RemoveOutliersProcessor(OutlierDetector outlierDetector){
        this.outlierDetector=outlierDetector;
    }

    @Override
    public List<CSVRecord> process(List<CSVRecord> records) {
        removeOutliersForColumns(records, "");
        return null;
    }

    /**
     * Removes outliers by using the value from the previous record.<br>
     * If the record is the first use the second as the "correcting" value
     */
    private List<CSVRecord> removeOutliersForColumns(List<CSVRecord> records, String... columns) {
        for (String column : columns) {
            //TODO: Get column values as a double array. We need this to initialize the outlierDetector with setData(double[])
            double[] columnValues;
            
            columnValues=CSVRecordUtils.getDoubleValuesForColumn(records, column);
            outlierDetector.setData(columnValues);
            
            for (int i = 0; i < records.size(); i++) {
                CSVRecord processing, reference;

                processing = records.get(i);

                if (i != 0) {
                    reference = records.get(i - 1);
                } else {
                    reference = records.get(i + 1);
                }
            }
        }
        return null;
    }

}
