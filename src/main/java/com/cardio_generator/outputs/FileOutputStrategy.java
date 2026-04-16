package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/** Provides dependency firewall for writing to local files in the filesystem. **/
public class FileOutputStrategy implements OutputStrategy {

    private String baseDirectory; // made camelCase everywhere since it is a variable

    public final ConcurrentHashMap<String, String> FILE_MAP = new ConcurrentHashMap<>(); 
            // made all capital since it is final

    /**
     * Upon initialization a base directory is chosen which will contain all output files.
     * @param baseDirectory a valid path to a directory in the local file system. If there are
     *                      nonexistent parent directories they are created dynamically.
     * @implNote if the baseDirectory is invalid FileOutputStrategy won't fail until output(...) is
     *           called!
     */
    public FileOutputStrategy(String baseDirectory) {

        this.baseDirectory = baseDirectory;
    }

    /**
     * Appends 1 line of formatted data of a patient to appropriate file. If it does not exist, the
     * appropriate file is created, and data is appended to it.
     * @param patientId assumed to be valid patient ID
     * @param timestamp timestamp of the data recorded
     * @param label used for creating the file name [label].txt and for explicitly marking data entry as well
     * @param data must not contain "\n" as it breaks the file formatting
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the filePath variable // made into camelCase since it is a local variable
        String filePath = FILE_MAP.computeIfAbsent(label, k -> Paths.get(baseDirectory,
                label + ".txt").toString());// broke line

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
            Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, 
                    StandardOpenOption.APPEND))) {
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n",
                    patientId, timestamp, label, data); // broke line 
        } catch (Exception e) {
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}