
package org.iampiti.csv;

import java.util.List;
import org.iampiti.csv.record.ModifiableCSVRecord;
import org.iampiti.openscale.model.Column;

/**
 * Merges two Openscale produced CSV files by date<br>
 * The two files have to contain different fragments of a series of related
 * measurements. 
 * For example: Most scales store the series of measurements of a person. 
 * Imagine that on date A the scale has 3 measurements (1, 2, 3) of a person.
 * Later, on date B, that person has measured itself two more times with that scale
 * so it has measurements 1 through 5 (or maybe it has removed some of the oldest
 * ones). If you export CSV files with Openscale in moments A and B you will get
 * CSV files that contain some repeated measurements but some unique too.
 * This class can merge both sets of measurements so that only unique measurements
 * remain.
 * @author Andres
 */
public class OpenscaleCSVDateMerger {
    
    static final Column MERGE_COLUMN=Column.DATETIME;
    
    public List<ModifiableCSVRecord> merge (List<ModifiableCSVRecord> first, List<ModifiableCSVRecord> second){
        return null;
    }
}
