package data_management;

import static org.junit.jupiter.api.Assertions.*;

import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.data_management.DataStorage;
import com.involuntaryminimalism.data_management.PatientRecord;
import java.util.List;
import org.junit.jupiter.api.Test;

class DataStorageTest {

  @Test
  void testAddAndGetRecords() {
    DataStorage storage =
        DataStorage
            .getInstance(); // removed reader from the overload, the constructor doesnt need
                            // anything
    storage.addPatientData(1, 100.0, Label.WhiteBloodCells, 1714376789050L);
    storage.addPatientData(1, 200.0, Label.WhiteBloodCells, 1714376789051L);

    List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);
    assertEquals(2, records.size()); // Check if two records are retrieved
    assertEquals(100.0, records.get(0).getMeasurementValue()); // Validate first record
  }

  @Test
  void testSingleton() {
    var instance01 = DataStorage.getInstance();
    var instance02 = DataStorage.getInstance();
    boolean isSameObject = (instance01 == instance02);
    assertTrue(isSameObject);
  }
}
