package com.involuntaryminimalism.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

/** Provides dependency firewall for "writing"/sending patient data to a TCP connection.* */
public class TcpOutputStrategy implements OutputStrategy {

  private ServerSocket serverSocket;
  private Socket clientSocket;
  private PrintWriter out;

  /**
   * Attempts initiating TCP port at initialization and keeps using it later. When socket creation
   * fails, exception is caught gracefully.
   *
   * @param port port number where the socket is opened
   */
  public TcpOutputStrategy(int port) {
    try {
      serverSocket = new ServerSocket(port);
      System.out.println("TCP Server started on port " + port);

      // Accept clients in a new thread to not block the main thread
      Executors.newSingleThreadExecutor()
          .submit(
              () -> {
                try {
                  clientSocket = serverSocket.accept();
                  out = new PrintWriter(clientSocket.getOutputStream(), true);
                  System.out.println("Client connected: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                  e.printStackTrace();
                }
              });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sends a data packet to the TCP port provided it is available with the following components:
   *
   * @param patientId assumed to be valid patient ID
   * @param timestamp timestamp of the data recorded
   * @param label marks data packet with this label
   * @param data must not contain "\n" as it breaks the text formatting
   */
  @Override
  public void output(int patientId, long timestamp, String label, String data) {
    if (out != null) {
      String message = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);
      out.println(message);
    }
  }
}
