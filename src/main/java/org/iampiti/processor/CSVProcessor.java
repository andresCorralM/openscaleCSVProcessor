package org.iampiti.processor;

import java.util.List;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Andres
 */
public interface CSVProcessor {
    List<CSVRecord> process(List<CSVRecord> records);
}
