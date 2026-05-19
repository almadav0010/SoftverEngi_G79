package com.involuntaryminimalism.data_management;

import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.data_management.inputs.DataListener;
import java.io.IOException;
import java.util.List;

/** Puts input data into data storage. */
public class DataSourceAdapter {
  private final DataListener dataListener;

  public DataSourceAdapter(DataListener dataListener) {
    this.dataListener = dataListener;
  }

  /**
   * Reads from specified data listener and adapts it to be placed in the data storage.
   *
   */
  public void putDataToStorage() {
    try {
      List<String[]> semiRawData = dataListener.readData();

      // contract specified in DataListener:
      //  a list string arrays encoding a record in this specific order:
      //  [patient id], [timestamp], [label], [data]
      // ---------------------------------------------------------------------
      // 1. properly parse parts of the entry
      // 2. store parsed data in DataStorage
      for (String[] entry : semiRawData) {
        int patientID;
        long timestamp;
        Label label; // aka the record type
        double data; // aka the measurement value

        patientID = Integer.parseInt(entry[0]);
        timestamp = Long.parseLong(entry[1]);
        label = Label.valueOf(entry[2]);
        if (label == Label.Alert) {
          // alerts logs are not records therefore skipped
          continue;
        }
        data = RawParser.numeric(entry[3]); // handles special formats like percentages
        // gives parsed data to the storage in a way it understands (doing the adapter thing)
        DataStorage.getInstance().addPatientData(patientID, data, label, timestamp);
      }
    } catch (IOException e) {
      System.out.println("Error with reading data from data listener. I will not put anything into data storage!");
    }
  }
}
