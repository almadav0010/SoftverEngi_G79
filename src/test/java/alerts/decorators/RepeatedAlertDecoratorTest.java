package alerts.decorators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.involuntaryminimalism.alerts.Alert;
import com.involuntaryminimalism.alerts.decorators.RepeatedAlertDecorator;
import com.involuntaryminimalism.alerts.types.TriggeredAlertFactory;
import org.junit.jupiter.api.Test;

public class RepeatedAlertDecoratorTest {
  @Test
  void testConditionChange() {
    Alert alertOG =
        new TriggeredAlertFactory()
            .createAlert(
                "047",
                "Agent 47 did not complete the mission without getting noticed. He must try again later",
                127);
    Alert alertPriority =
        new RepeatedAlertDecorator(
            alertOG,
            () -> {
              return true;
            });

    assertEquals("Repeat me please! " + alertOG.getCondition(), alertPriority.getCondition());
  }

  @Test
  void testFalseConditionChange() {
    Alert alertOG =
        new TriggeredAlertFactory()
            .createAlert(
                "047",
                "Agent 47 did not complete the mission without getting noticed. He must try again later",
                127);
    Alert alertPriority =
        new RepeatedAlertDecorator(
            alertOG,
            () -> {
              return false;
            });

    assertEquals(alertOG.getCondition(), alertPriority.getCondition());
  }

  @Test
  void testEmptyConditionChange() {
    Alert alertOG =
        new TriggeredAlertFactory()
            .createAlert(
                "047",
                "Agent 47 did not complete the mission without getting noticed. He must try again later",
                127);
    Alert alertPriority = new RepeatedAlertDecorator(alertOG, null);

    assertEquals(alertOG.getCondition(), alertPriority.getCondition());
  }

  @Test
  void testErronousConditionChange() {
    Alert alertOG =
        new TriggeredAlertFactory()
            .createAlert(
                "047",
                "Agent 47 did not complete the mission without getting noticed. He must try again later",
                127);
    Alert alertPriority =
        new RepeatedAlertDecorator(
            alertOG,
            () -> {
              return null;
            });

    assertEquals(alertOG.getCondition(), alertPriority.getCondition());
  }
}
