package com.involuntaryminimalism.cardio_generator.outputs;

/** A way to output data, can be etended to add the method of outputing */
public interface OutputStrategy {
  /**
   * Outputs the data of the patient in the implemented way
   *
   * @param patientId who do we want to output from as an integer
   * @param timestamp what is the timestamp we want to output as a long
   * @param label what name should the outputted data have as a string
   * @param data the actual content of the output as a string
   */
  void output(int patientId, long timestamp, String label, String data);
}
