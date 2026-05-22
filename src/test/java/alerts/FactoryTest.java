package alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.involuntaryminimalism.alerts.Alert;
import com.involuntaryminimalism.alerts.types.*;
import com.involuntaryminimalism.alerts.types.BloodOxygenAlertFactory;
import com.involuntaryminimalism.alerts.types.BloodOxygenDropAlertFactory;
import com.involuntaryminimalism.alerts.types.BloodPressureCriticalAlertFactory;
import com.involuntaryminimalism.alerts.types.BloodPressureTrendAlertFactory;
import com.involuntaryminimalism.alerts.types.ECGAlertFactory;
import com.involuntaryminimalism.alerts.types.HypotensiveHypoxemiaAlertFactory;
import com.involuntaryminimalism.alerts.types.TriggeredAlertFactory;
import org.junit.jupiter.api.Test;

public class FactoryTest {

  final String PATIENTID = "0";
  final String CONDITION = "1";
  final long TIMESTAMP = 2;

  @Test
  public void testBloodOxygenAlert(){
    Alert result = new BloodOxygenAlertFactory()
        .createAlert(PATIENTID,CONDITION,TIMESTAMP);
        assertEquals(result.getPatientId(),"0");
        assertEquals(result.getCondition(),"1");
        assertEquals(result.getTimestamp(),2);
  }
  
  @Test
  public void testBloodOxygenDrop(){
    Alert result = new BloodOxygenDropAlertFactory()
        .createAlert(PATIENTID,CONDITION,TIMESTAMP);
        assertEquals(result.getPatientId(),"0");
        assertEquals(result.getCondition(),"1");
        assertEquals(result.getTimestamp(),2);
  }

  @Test
  public void testBloodPressureCritical(){
    Alert result = new BloodPressureCriticalAlertFactory()
        .createAlert(PATIENTID,CONDITION,TIMESTAMP);
        assertEquals(result.getPatientId(),"0");
        assertEquals(result.getCondition(),"1");
        assertEquals(result.getTimestamp(),2);
  }

  @Test
  public void testPressureTrend(){
    Alert result = new BloodPressureTrendAlertFactory()
        .createAlert(PATIENTID,CONDITION,TIMESTAMP);
        assertEquals(result.getPatientId(),"0");
        assertEquals(result.getCondition(),"1");
        assertEquals(result.getTimestamp(),2);
  }

  @Test
  public void testECGAlert(){
    Alert result = new ECGAlertFactory()
        .createAlert(PATIENTID,CONDITION,TIMESTAMP);
        assertEquals(result.getPatientId(),"0");
        assertEquals(result.getCondition(),"1");
        assertEquals(result.getTimestamp(),2);
  }

  @Test
  public void testHypotensiveHypocemia(){
    Alert result = new HypotensiveHypoxemiaAlertFactory()
        .createAlert(PATIENTID,CONDITION,TIMESTAMP);
        assertEquals(result.getPatientId(),"0");
        assertEquals(result.getCondition(),"1");
        assertEquals(result.getTimestamp(),2);
  }

  @Test
  public void testTriggeredAlert(){
    Alert result = new TriggeredAlertFactory()
        .createAlert(PATIENTID,CONDITION,TIMESTAMP);
        assertEquals(result.getPatientId(),"0");
        assertEquals(result.getCondition(),"1");
        assertEquals(result.getTimestamp(),2);
  }
}