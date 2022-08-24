package org.iampiti.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;
import org.iampiti.csv.record.ModifiableCSVRecord;
import org.iampiti.csv.util.CSVRecordUtils;

/**
 *
 * @author Andres
 */
public class CSVWriter {

    public static final char DEFAULT_RECORD_SEPARATOR = '\n';
    public static final char DEFAULT_DELIMITER_STRING = ',';

    private final boolean useHeader;
    private final char recordSeparator;
    private final char delimiterString;

    public CSVWriter(boolean useHeader, char recordSeparator) {
        this.useHeader = useHeader;
        this.recordSeparator = recordSeparator;
        this.delimiterString = DEFAULT_DELIMITER_STRING;
    }

    public CSVWriter() {
        this(true, DEFAULT_RECORD_SEPARATOR);
    }

    public void write(List<ModifiableCSVRecord> records, File file) throws FileNotFoundException, IOException {
        String[] headerColumnNames = {};

        if (!records.isEmpty()) {
            headerColumnNames = CSVRecordUtils.getColumnNames(records.get(0)).toArray(new String[0]);
        }

        try ( BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8")));) {
            if (useHeader) {
                writeHeader(headerColumnNames, bw);
            }

            for (ModifiableCSVRecord record : records) {
                writeRecord(record, headerColumnNames, bw);
            }
        }
    }

    private void writeHeader(String[] columnNames, BufferedWriter writer) throws IOException {
        StringBuilder sb = new StringBuilder("");

        for (String column : columnNames) {
            sb.append("").append(column).append("").append(delimiterString);
        }

        writer.write(sb.toString());
    }

    private void writeRecord(ModifiableCSVRecord record, String[] columnNames, BufferedWriter writer) throws IOException {
        StringBuilder sb = new StringBuilder("");

        for (String column : columnNames) {
            sb.append(record.get(column)).append(delimiterString);
        }
        
        writer.write(sb.toString());
        writer.write(recordSeparator);
    }
}
