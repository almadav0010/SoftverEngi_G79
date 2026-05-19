package com.involuntaryminimalism.data_management.inputs;

import java.io.IOException;
import java.util.List;

/** Handles reading data from a TCP connection generated as output by this software. */
public class TCPDataListener implements DataListener {

  @Override
  public List<String[]> readData() throws IOException {
    throw new UnsupportedOperationException("Not supported:(");
  }
}
