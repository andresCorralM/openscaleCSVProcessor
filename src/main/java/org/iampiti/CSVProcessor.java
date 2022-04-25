
package org.iampiti;

import java.util.List;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Andres
 */
public class CSVProcessor {
    
    public List<CSVRecord> process(List<CSVRecord> records){
        removeOutliersForColumns(records, "");
        return null;
    }
    
    /**
     * Removes outliers by using the value from the previous record
     */
    private List<CSVRecord> removeOutliersForColumns(List<CSVRecord> records, String... columns){
        return null;
    }
}
