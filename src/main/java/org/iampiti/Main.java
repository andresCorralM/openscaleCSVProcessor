package org.iampiti;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.apache.commons.csv.CSVRecord;
import org.iampiti.outlier.ThreeStdOutlierDetector;
import org.iampiti.parser.Parser;
import org.iampiti.processor.CSVProcessor;
import org.iampiti.processor.RemoveOutliersProcessor;

public class Main {

    static final Logger LOG = Logger.getLogger("Main");
    
    private static final List<CSVProcessor> processorsToRun;
    
    static{
        processorsToRun=new ArrayList<>();
        processorsToRun.add(new RemoveOutliersProcessor(new ThreeStdOutlierDetector()));
    }

    /**
     * args[1]: File to process args[2]: Output file. If missing use the same
     * name as input (overwrite)
     */
    public void run(String[] args) throws IOException {
        final File inputFile, outputFile;

        inputFile = new File(args[0]);

        if (args.length < 2) {
            outputFile = inputFile;
        } else {
            outputFile = new File(args[1]);
        }

        //
        process(inputFile, outputFile);
        //write(outputFile);
    }

    private List<CSVRecord> parse(File inputCSVFile) throws IOException {
        final Parser parser = Parser.getParser();
        LOG.log(Level.FINE, "Parsing file", inputCSVFile.getName());
        return parser.parse(inputCSVFile);
    }

    /**
     * Processes the csv file as desired
     */
    private void process(File inputCSVFile, File outputCSVFile) throws IOException {
        List<CSVRecord> records;
        
        records=parse(inputCSVFile);
        
        //Run processors
        for (CSVProcessor  processor : processorsToRun){
            records=processor.process(records);
        }

        write(outputCSVFile, records);
    }

    /**
     * Writes the file back to disk
     */
    private void write(File outputCSVFile, final List<CSVRecord> recordsToSave) {

    }

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.run(args);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error!", e);
        }
    }
}
