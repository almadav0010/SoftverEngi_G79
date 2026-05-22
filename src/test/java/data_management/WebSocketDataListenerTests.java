package data_management;

import static org.junit.jupiter.api.Assertions.*;

import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.cardio_generator.outputs.WebSocketOutputStrategy;
import com.involuntaryminimalism.data_management.DataSourceAdapter;
import com.involuntaryminimalism.data_management.DataStorage;
import com.involuntaryminimalism.data_management.inputs.WebsocketDataListener;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import org.junit.jupiter.api.Test;

public class WebSocketDataListenerTests {
  @Test
  public void dataFormattingTest() throws InterruptedException, IOException {
    int port = 8888;
    // send ill formatted data
    var stdout = new WebSocketOutputStrategy(port);
    var websocketclient = new WebsocketDataListener(URI.create("ws://localhost:" + port));
    websocketclient.connectBlocking();
    stdout.output(2222, 100, "WRONG LABEL", "data string");
    // ill formatted data rejected -> readDataQueue is empty
    List<String[]> readData = websocketclient.readData();
    assertTrue(readData.isEmpty());
    websocketclient.close();
  }

  @Test
  public void lostConnectionTest() throws InterruptedException, IOException {
    int port = 8889;
    var stdout = new WebSocketOutputStrategy(port);
    var websocketclient = new WebsocketDataListener(URI.create("ws://localhost:" + port));
    websocketclient.connectBlocking();
    stdout.output(3333, 100, Label.SystolicPressure.name(), "30");
    websocketclient.close();
    // even after connection is lost previously recorded entries are kept
    System.out.println("Lost connection tester" + websocketclient.readData().toString());
  }

  @Test
  public void dataIntegrationTest() throws InterruptedException {
    int port = 8890;
    int magicPatientID = 32323232;
    long magicTimeStamp = 1111111;

    var stdout = new WebSocketOutputStrategy(port);
    var websocketclient = new WebsocketDataListener(URI.create("ws://localhost:" + port));
    websocketclient.connectBlocking();
    stdout.output(magicPatientID, magicPatientID, Label.DiastolicPressure.name(), "90");

    var adapter = new DataSourceAdapter(websocketclient);
    adapter.putDataToStorage();
    var records =
        DataStorage.getInstance().getRecords(magicPatientID, Long.MIN_VALUE, Long.MAX_VALUE);
    // match test record with what is stored in the data storage
    assertEquals(records.get(0).getRecordType(), Label.DiastolicPressure);
    websocketclient.close();
  }
}
