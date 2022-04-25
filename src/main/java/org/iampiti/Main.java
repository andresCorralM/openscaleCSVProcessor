package org.iampiti;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.apache.commons.csv.CSVRecord;
import org.iampiti.parser.Parser;

public class Main {

    static final Logger LOG = Logger.getLogger("Main");

    /**
     * args[1]: File to process args[2]: Output file. If missing use the same
     * name as input (overwrite)
     */
    public void run(String[] args) throws IOException {
        final File inputFile, outputFile;

        inputFile = new File(args[1]);

        if (args.length < 2) {
            outputFile = inputFile;
        } else {
            outputFile = new File(args[2]);
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
        final List<CSVRecord> records;
        
        records=parse(inputCSVFile);

        write(outputCSVFile);
    }

    /**
     * Writes the file back to disk
     */
    private void write(File outputCSVFile) {

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
