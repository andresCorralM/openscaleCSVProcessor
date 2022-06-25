
package org.iampiti.util;

import java.util.List;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Andres
 */
public class CSVRecordUtils {
    public static double[] getDoubleValuesForColumn(List<CSVRecord> records, String columnName){
        double[] values;
        int numRows;
        
        numRows=records.size();
        values=new double[numRows];
        
        for(int i=0;i<numRows;i++){
            values[i]=Double.parseDouble(records.get(i).get(columnName));
        }
        
        return values;
    }
}
