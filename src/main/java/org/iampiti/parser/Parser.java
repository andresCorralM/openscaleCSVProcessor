
package org.iampiti.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Andres
 */
public class Parser {
    
    private final CSVFormat csvFormat;
    
    private Parser(CSVFormat csvFormat){
        this.csvFormat=csvFormat;
    }
    
    public static final Parser getParser(){
        final CSVFormat csvFormat;
        
        csvFormat=CSVFormat.Builder.create().setRecordSeparator('\n').setHeader().build();
        
        return new Parser(csvFormat);
    }
    
    public List<CSVRecord> parse(File file) throws IOException {
        List<CSVRecord> records;
        
        try(FileReader fr = new FileReader(file);
	         BufferedReader br = new BufferedReader(fr)){
            final CSVParser parser=csvFormat.parse(br);
            records=parser.getRecords();
        }
        
        return records;
    }
}
