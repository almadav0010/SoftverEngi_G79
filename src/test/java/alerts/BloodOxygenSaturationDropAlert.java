package alerts;

import org.junit.jupiter.api.Test;

import com.involuntaryminimalism.TesterDoctor;
import com.involuntaryminimalism.alerts.AlertManager;
import com.involuntaryminimalism.alerts.AlertGenerator;
import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.alerts.types.BloodPressureCriticalAlert;
import com.involuntaryminimalism.data_management.DataStorage;
import com.involuntaryminimalism.data_management.Patient;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.involuntaryminimalism.alerts.types.BloodOxygenAlert;
import com.involuntaryminimalism.alerts.types.BloodOxygenDropAlert;

public class BloodOxygenSaturationDropAlert {
    @Test
    void OxygenLevelDrop(){
      var instance = DataStorage.getInstance();
      var alertManager = new AlertManager();
      var doctor = new TesterDoctor();
      AlertManager.registerListener(doctor);
      var patient = new Patient(7);
      instance.addPatientData(7, 104.5, Label.Saturation, 100);
      instance.addPatientData(7, 102.5, Label.Saturation, 500);
      instance.addPatientData(7, 98.5, Label.Saturation, 800);
      instance.addPatientData(7, 95.5, Label.Saturation, 900);
      var alertGenerator = new AlertGenerator();
      alertGenerator.evaluateData(patient);
      assertEquals(BloodOxygenDropAlert.class.getName(), doctor.lastAlert.getClass().getName());

    }
}
