
package org.iampiti.csv.record;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.csv.CSVRecord;

/**
 * Apache's CSVRecord is a read-only view of a row of a CSV file. This class allows for modification of the held values
 * We need this so that CSVProcessors can alter CSVRecords
 * @author Andres
 */
public class ModifiableCSVRecord{
    private final CSVRecord referencedRecord;
    private final Map<String, String> modifiedColumns;
    
    public ModifiableCSVRecord(CSVRecord referencedRecord){
        this.referencedRecord=referencedRecord;
        modifiedColumns=new HashMap<>();
    }
    
    public String get(String column){
        String value;
        
        if(modifiedColumns.containsKey(column)){
            value=modifiedColumns.get(column);
        }else{
            value=referencedRecord.get(column);
        }
        
        return value;
    }
    
    public void set(String column, String value){
        modifiedColumns.put(column, value);
    }
}
