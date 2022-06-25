
package org.iampiti.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.commons.csv.CSVRecord;
import org.iampiti.parser.Parser;
import org.junit.Assert;
import org.junit.Test;
/**
 *
 * @author Andres
 */
public class CSVRecordUtilsTest {
    @Test
    public void getDoubleValuesForColumnTest() throws IOException{
        InputStream testFileIS;
        List<CSVRecord> testFileRecords;
        
        testFileIS=getClass().getResourceAsStream("/test.csv");
        
        try(InputStreamReader isr=new InputStreamReader(testFileIS);){
           Parser parser;
           parser=Parser.getParser();
           testFileRecords=parser.parse(isr);
        }
        
        double[] parsedWeightColumnValues;
        parsedWeightColumnValues=CSVRecordUtils.getDoubleValuesForColumn(testFileRecords, "weight");
        
        double[] expectedValues={74.5, 73.6, 72.9, 73.3, 74.0};
        
        Assert.assertArrayEquals(expectedValues, parsedWeightColumnValues, 0.1);
    }
}
