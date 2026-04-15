package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Generates some type of data for a patient, the type is determined by the implementation
 */
public interface PatientDataGenerator {
    /**
     * create data in some way, and output it
     * @param patientId who do we generate the data for
     * @param outputStrategy how and where do we output
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
