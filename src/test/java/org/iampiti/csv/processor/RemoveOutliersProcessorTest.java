package org.iampiti.csv.processor;

import org.iampiti.csv.processor.RemoveOutliersProcessor;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVRecord;
import org.iampiti.csv.record.ModifiableCSVRecord;
import org.iampiti.outlier.ThreeStdOutlierDetector;
import org.iampiti.csv.util.CSVRecordUtils;
import org.iampiti.csv.util.CSVUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Andres
 */
public class RemoveOutliersProcessorTest {

    @Test
    public void processTest() throws IOException {
        final String columnToProcess = "weight";
        List<CSVRecord> testFileRecords;
        List<ModifiableCSVRecord> testFileModifiableRecords;
        RemoveOutliersProcessor rop;

        testFileRecords = CSVUtils.parseFile("testRemoveOutliers.csv");
        rop = new RemoveOutliersProcessor(new ThreeStdOutlierDetector(), columnToProcess);

        testFileModifiableRecords = testFileRecords.stream().map(r -> new ModifiableCSVRecord(r)).collect(Collectors.toList());
        testFileModifiableRecords = rop.process(testFileModifiableRecords);

        String lastRecordValue;
        ModifiableCSVRecord lastRecord = testFileModifiableRecords.get(testFileModifiableRecords.size() - 1);
        lastRecordValue=lastRecord.get(columnToProcess);
        
        String lastRecordExpected="76.8";

        Assert.assertEquals(lastRecordExpected, lastRecordValue);
    }
}
