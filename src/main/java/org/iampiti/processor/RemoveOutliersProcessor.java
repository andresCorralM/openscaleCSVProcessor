package org.iampiti.processor;

import java.util.List;
import org.apache.commons.csv.CSVRecord;
import org.iampiti.outlier.OutlierDetector;
import org.iampiti.csv.record.ModifiableCSVRecord;
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
    public List<ModifiableCSVRecord> process(List<ModifiableCSVRecord> records) {
        removeOutliersForColumns(records);
        return null;
    }

    /**
     * Removes outliers by using the value from the previous record.<br>
     * If the record is the first use the second as the "correcting" value
     */
    private List<CSVRecord> removeOutliersForColumns(List<ModifiableCSVRecord> records, String... columns) {
        for (String column : columns) {
            double[] columnValues;
            
            columnValues=CSVRecordUtils.getDoubleValuesForColumn(records, column);
            outlierDetector.setData(columnValues);
            
            for (int i = 0; i < records.size(); i++) {
                ModifiableCSVRecord processing, reference;
                double processingValue;

                processing = records.get(i);

                if (i != 0) {
                    reference = records.get(i - 1);
                } else {
                    reference = records.get(i + 1);
                }
                
                processingValue=Double.parseDouble(processing.get(column));
                
                if(outlierDetector.isOutlier(processingValue)){
                    
                }
            }
        }
        return null;
    }

}
