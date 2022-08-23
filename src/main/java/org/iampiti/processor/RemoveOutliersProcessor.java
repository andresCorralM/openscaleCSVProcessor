package org.iampiti.processor;

import java.util.List;
import org.iampiti.outlier.OutlierDetector;
import org.iampiti.csv.record.ModifiableCSVRecord;
import org.iampiti.util.CSVRecordUtils;

/**
 *
 * @author Andres
 */
public class RemoveOutliersProcessor implements CSVProcessor {
    
    private final OutlierDetector outlierDetector;
    private String[] columnsToProcess=null;
    
    public RemoveOutliersProcessor(OutlierDetector outlierDetector){
        this.outlierDetector=outlierDetector;
    }
    
    public RemoveOutliersProcessor(OutlierDetector outlierDetector, String... columns){
        this(outlierDetector);
        
        if(columns.length>0){
            this.columnsToProcess=columns;
        }
    }

    @Override
    public List<ModifiableCSVRecord> process(List<ModifiableCSVRecord> records) {
        String[] definitiveColumnsToProcess={};
        
        if(!records.isEmpty()){
            if(columnsToProcess == null){
                List<String> columnNames;

                columnNames=CSVRecordUtils.getColumnNames(records.get(0));
                definitiveColumnsToProcess=columnNames.toArray(new String[0]);
            }else{
                definitiveColumnsToProcess=columnsToProcess;
            }
        }
        
        return removeOutliersForColumns(records, definitiveColumnsToProcess);
    }

    /**
     * Removes outliers by using the value from the previous record.<br>
     * If the record is the first use the second as the "correcting" value
     */
    private List<ModifiableCSVRecord> removeOutliersForColumns(List<ModifiableCSVRecord> records, String... columns) {
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
                    processing.set(column, reference.get(column));
                    records.set(i, processing);
                }
            }
        }
        return records;
    }

}
