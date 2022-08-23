
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
    
    /*private List<CSVRecord> parseFile(String path) throws IOException{
        InputStream testFileIS;
        List<CSVRecord> testFileRecords;
        
        testFileIS=getClass().getResourceAsStream(path);
        
        try(InputStreamReader isr=new InputStreamReader(testFileIS);){
           Parser parser;
           parser=Parser.getParser();
           testFileRecords=parser.parse(isr);
        }
        
        return testFileRecords;
    }*/
    
    @Test
    public void getDoubleValuesForColumnTest() throws IOException{
        List<CSVRecord> testFileRecords;
        
        testFileRecords=CSVUtils.parseFile("test.csv");
        
        double[] parsedWeightColumnValues;
        parsedWeightColumnValues=CSVRecordUtils.getDoubleValuesForColumn(testFileRecords, "weight");
        
        double[] expectedValues={74.5, 73.6, 72.9, 73.3, 74.0, 63.5};
        
        Assert.assertArrayEquals(expectedValues, parsedWeightColumnValues, 0.1);
    }
    
    @Test
    public void getColumnNamesTest() throws IOException{
        List<CSVRecord> testFileRecords;
        
        testFileRecords=CSVUtils.parseFile("test.csv");
        
        List<String> columnNames;
        
        columnNames=CSVRecordUtils.getColumnNames(testFileRecords.get(0));
        String[] expectedValues={"biceps","bone","caliper1","caliper2","caliper3","calories","chest","comment","dateTime","fat","hip","lbm","muscle","neck","thigh","visceralFat","waist","water","weight"};
        
        Assert.assertArrayEquals("Both sets of column names should match", expectedValues, columnNames.toArray(new String[0]));
    }
}
