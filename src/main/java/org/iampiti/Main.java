package org.iampiti;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVRecord;
import org.iampiti.csv.CSVWriter;
import org.iampiti.outlier.ThreeStdOutlierDetector;
import org.iampiti.csv.record.ModifiableCSVRecord;
import org.iampiti.csv.parser.Parser;
import org.iampiti.csv.processor.CSVProcessor;
import org.iampiti.csv.processor.RemoveOutliersProcessor;
import org.iampiti.openscale.model.Column;

public class Main {

    private static final Logger LOG = Logger.getLogger("Main");
    
    private static final List<CSVProcessor> processorsToRun;
    
    static{
        //Process every column except "dateTime"
        final Column[] columnsToProcess={Column.BICEPS, Column.BONE, Column.CALIPER1, Column.CALIPER2, Column.CALIPER3, Column.CALORIES, Column.CHEST
        , Column.COMMENT, Column.FAT, Column.HIP, Column.LBM, Column.MUSCLE, Column.NECK, Column.THIGH, Column.VISCERALFAT
        , Column.WAIST, Column.WEIGHT};
        final String[] columnsToProcessAsString=Arrays.stream(columnsToProcess).map(c -> c.toString()).toArray(String[]::new) ;
        
        processorsToRun=new ArrayList<>();
        
        processorsToRun.add(new RemoveOutliersProcessor(new ThreeStdOutlierDetector(), columnsToProcessAsString));
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
        
        LOG.log(Level.INFO, "Input file: {0}. Output file: {1}", new String[]{inputFile.getName(), outputFile.getName()});
        //
        process(inputFile, outputFile);
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
        List<ModifiableCSVRecord> modifiableRecords;
        modifiableRecords=records.stream().map(r -> new ModifiableCSVRecord(r)).collect(Collectors.toList());
        
        //Run processors
        for (CSVProcessor  processor : processorsToRun){
            modifiableRecords=processor.process(modifiableRecords);
        }
        try{
            write(outputCSVFile, modifiableRecords);
        }catch(IOException e){
            LOG.log(Level.SEVERE, "Error writing modified CSV file", e);
        }
        
        //Merge?
    }

    /**
     * Writes the file back to disk
     */
    private void write(File outputCSVFile, final List<ModifiableCSVRecord> recordsToSave) throws IOException {
        CSVWriter csvWriter;
        
        LOG.log(Level.INFO, "Writing to output file {0}", outputCSVFile.getAbsolutePath());
        
        csvWriter=new CSVWriter();
        csvWriter.write(recordsToSave, outputCSVFile);
    }
    
    /**
     * TODO: We have to decide what to do with the input data:
     * <ul>
     * <li> Always remove outliers and merge input files </li>
     * <li> Let the user choose what to do with command line parameters </li>
     * <ul>
     */
    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.run(args);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error!", e);
        }
    }
}
