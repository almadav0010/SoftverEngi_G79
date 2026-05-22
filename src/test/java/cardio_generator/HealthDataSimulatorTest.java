package cardio_generator;

import com.involuntaryminimalism.cardio_generator.HealthDataSimulator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HealthDataSimulatorTest {
  @Test
  void testSingletonBehaviour() {
    var instance01 = HealthDataSimulator.getInstance();
    var instance02 = HealthDataSimulator.getInstance();
    boolean isSameObject = (instance01 == instance02);
    assertTrue(isSameObject);
  }
}
