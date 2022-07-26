
package org.iampiti.util;

import java.util.List;
import org.apache.commons.csv.CSVRecord;
import org.iampiti.csv.record.ModifiableCSVRecord;

/**
 *
 * @author Andres
 */
public class CSVRecordUtils {
    /*public static double[] getDoubleValuesForColumn(List<CSVRecord> records, String columnName){
        double[] values;
        int numRows;
        
        numRows=records.size();
        values=new double[numRows];
        
        for(int i=0;i<numRows;i++){
            values[i]=Double.parseDouble(records.get(i).get(columnName));
        }
        
        return values;
    }*/
    
    public static double[] getDoubleValuesForColumn(List<?> records, String columnName){
        double[] values;
        int numRows;
        
        numRows=records.size();
        values=new double[numRows];
        
        {
            Object csvRecordLikeElement;
            String columnValue;
            
            for(int i=0;i<numRows;i++){
                csvRecordLikeElement=records.get(i);
                columnValue=getColumnValueForCSVRecordLikeElement(csvRecordLikeElement, columnName);

                values[i]=Double.parseDouble(columnValue);
            }
        }
        
        return values;
    }
    
    
    
    /**
     * Returns the value for the given column for a CSVRecord-like element. In practice it means a CSVRecord or a ModifiableCSVRecord.<br>
     * All we need is a type that has a [String get(String)] method that returns the value of a column.<br>
     * We could easily create an interface with that method and make ModifiableCSVRecord but we are not the designers of CSVRecord and
     * it's also final so we are pretty much stuck here.<br>
     * There's probably a better solution but this is what I've come up with (so far).
     */
    private static String getColumnValueForCSVRecordLikeElement(Object csvRecordLikeElement, String column){
        String columnValue=null;
        
        if(csvRecordLikeElement instanceof CSVRecord){
            CSVRecord csvRecord=(CSVRecord)csvRecordLikeElement;
            columnValue=csvRecord.get(column);
        }else if (csvRecordLikeElement instanceof ModifiableCSVRecord){
            ModifiableCSVRecord modifiableCSVRecord=(ModifiableCSVRecord)csvRecordLikeElement;
            columnValue=modifiableCSVRecord.get(column);
        }else{
            throw new IllegalArgumentException("The object passed into this method is not of a compatible type");
        }
        
        return columnValue;
    }
}
