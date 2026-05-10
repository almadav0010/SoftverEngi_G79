package com.data_management.inputs;

import com.data_management.DataStorage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DataListener {
  /**
   * Reads data from a specified source and formats into a JSON like
   * semi raw structure that can be further processed by an adapter.
   *
   * @return a list string arrays encoding a record in this specific
   *         order: [patient id], [timestamp], [label], [data]`
   * @throws IOException if there is an error reading the data`
   */
  List<String[]> readData() throws IOException;
}
