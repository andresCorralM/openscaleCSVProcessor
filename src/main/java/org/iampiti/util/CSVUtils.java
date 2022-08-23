package org.iampiti.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.commons.csv.CSVRecord;
import org.iampiti.parser.Parser;

/**
 *
 * @author Andres
 */
public class CSVUtils {
     public static List<CSVRecord> parseFile(String path) throws IOException{
        InputStream testFileIS;
        List<CSVRecord> testFileRecords;
        
        //testFileIS=getClass().getResourceAsStream(path);
        
        testFileIS=ClassLoader.getSystemResourceAsStream(path);
        
        try(InputStreamReader isr=new InputStreamReader(testFileIS);){
           Parser parser;
           parser=Parser.getParser();
           testFileRecords=parser.parse(isr);
        }
        
        return testFileRecords;
    }
}
