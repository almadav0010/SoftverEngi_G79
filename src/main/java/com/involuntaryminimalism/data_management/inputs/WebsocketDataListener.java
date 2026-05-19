package com.involuntaryminimalism.data_management.inputs;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 * While the project manual asked for a WebSocketClient named class in the linked examples
 * we have found that it is conventional to embed the client into the class that relies on
 * the websocket itself. Hence, we are keeping the filename WebsocketDataListener that is
 * actually the WebSocketClient.
 * (e.g. https://github.com/TooTallNate/Java-WebSocket/blob/master/src/main/example/ChatClient.java#L124)
 */
public class WebsocketDataListener extends WebSocketClient implements DataListener {

  /* stores incoming messages in the universally accepted format, until
  they are requested via the readData() method call, upon which the dataQueue is
  emptied. Only validated String[] are added to the queue.
  */
  private final LinkedList<String[]> dataQueue = new LinkedList<>();

  /**
   * Reads a chunk of data (accumulated since previous call of this method, and outputs them in the
   * commonly agreed format understood by {@link com.involuntaryminimalism.data_management.DataSourceAdapter} class.
   *
   * @return A list of patient records represented as String arrays
   * @throws IOException when error in websocket connection
   */
  @Override
  public List<String[]> readData() throws IOException {
    var out = new LinkedList<String[]>();
    while (!dataQueue.isEmpty()) {
      out.add(dataQueue.remove(0));
    }
    return out;
  }

  /**
   * Simple WebSocket Listener that supplies data coming from socket connections to a bridge that
   * can integrate that data to the data storage of this system.
   *
   * @param serverURI The constructor expects a valid ws:// or wss:// URI to connect to
   */
  public WebsocketDataListener(URI serverURI) throws InterruptedException {
    super(serverURI);
  }

  @Override
  public void onOpen(ServerHandshake serverHandshake) {
    var status = serverHandshake.getHttpStatusMessage();
    System.out.println("Connection opened with status: " + status);
  }

  @Override
  public void onMessage(String message) {
    // String message = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);
    String[] parts = message.strip().split(",");

    // preprocess parts
    stripParts(parts);
    if (isCorrupt(parts)) {
      return;
    }

    // convert line to standard form, for adapters
    var formattedMessage = new String[] {
        parts[0], // patient ID
        parts[1], // timestamp
        parts[2], // label
        parts[3], // data
    };
    dataQueue.add(formattedMessage);
  }

  @Override
  public void onClose(int i, String s, boolean b) {
    System.out.println("WebsocketDataListener closed with exit code " + i + " additional info: " + s);
  }

  @Override
  public void onError(Exception e) {
    e.printStackTrace();
  }

  // ///////////////// //
  // auxiliary methods //
  // ///////////////// //
  private boolean isCorrupt(String[] parts) {
    // pieces of information in message
    return parts.length != 4;
  }
  private void stripParts(String[] parts) {
    for (int i = 0; i < parts.length; i++) {
      parts[i] = parts[i].strip();
    }
  }
}
