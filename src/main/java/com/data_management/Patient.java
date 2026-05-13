package com.data_management;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Represents a patient and manages their medical records. This class stores patient-specific data,
 * allowing for the addition and retrieval of medical records based on specified criteria.
 */
public class Patient {
  private final int patientId;
  private final List<PatientRecord> patientRecords;

  /**
   * Constructs a new Patient with a specified ID. Initializes an empty list of patient records.
   *
   * @param patientId the unique identifier for the patient
   */
  public Patient(int patientId) {
    this.patientId = patientId;
    this.patientRecords = new ArrayList<>();
  }

  /** Getter for patientId */
  public int getPatientId() {return patientId;}

  /**
   * Adds a new record to this patient's list of medical records. The record is created with the
   * specified measurement value, record type, and timestamp.
   *
   * @param measurementValue the measurement value to store in the record
   * @param recordType the type of record, e.g., "HeartRate", "BloodPressure"
   * @param timestamp the time at which the measurement was taken, in milliseconds since UNIX epoch
   */
  public void addRecord(double measurementValue, String recordType, long timestamp) {
    PatientRecord record =
        new PatientRecord(this.patientId, measurementValue, recordType, timestamp);

    // insert to the appropriate index so chronological order is kept.
    // HEURISTICS: assumes new record is fresher than any recorded so start the search from behind.
    int insertIndex = this.patientRecords.size();
    while (record.getTimestamp() < this.patientRecords.get(insertIndex-1).getTimestamp()) {
      insertIndex--;
    }
    this.patientRecords.add(insertIndex, record);
  }

  /**
   * Retrieves a list of PatientRecord objects for this patient that fall within a specified time
   * range. The method filters records based on the start and end times provided.
   *
   * @param startTime the start of the time range, in milliseconds since UNIX epoch
   * @param endTime the end of the time range, in milliseconds since UNIX epoch
   * @return a list of PatientRecord objects that fall within the specified time range
   */
  public List<PatientRecord> getRecords(long startTime, long endTime) {
    List<PatientRecord> out = new ArrayList<>();

    for (var patientRecord : patientRecords) {
      double timestamp = patientRecord.getTimestamp();
      if (timestamp >= startTime && timestamp <= endTime)
        // no need for copying as all fields of PatientRecord is not modifiable from the outside
        out.add(patientRecord);
    }

    return out;
  }

  /**
   * Retrives the last N records of a patient.
   * @param lastN amount in chronological order
   * @return the last N amount of patient records. Oldest record is first. If
   *         there are less than N patient records then less than N are returned
   *
   * @implNote this method assumes that patientRecords are sorted timewise. which is guaranteed
   *           by the implementation of {@link #addRecord(double, String, long)}
   */
  public List<PatientRecord> getRecords(int lastN) {
    var out = new ArrayList<PatientRecord>();
    int size = this.patientRecords.size();
    for (int i = Math.max(0, size - lastN); i < size; i++) {
      out.add(this.patientRecords.get(i));
    }
    return out;
  }

  /**
   * Retrivies all records withing the last X miliseconds
   * @param lastMiliseconds time window in miliseconds
   * @return all records that were timestamped within the last X miliseconds
   */
  public List<PatientRecord> getRecords(long lastMiliseconds) {
    long now = java.time.Instant.now().toEpochMilli();
    return getRecords(now - lastMiliseconds, now);

  }
}
