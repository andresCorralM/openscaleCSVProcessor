package org.iampiti.processor;

import java.util.List;
import org.iampiti.csv.record.ModifiableCSVRecord;

/**
 *
 * @author Andres
 */
public interface CSVProcessor {
    List<ModifiableCSVRecord> process(List<ModifiableCSVRecord> records);
}
