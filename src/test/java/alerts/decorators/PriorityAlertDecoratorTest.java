package alerts.decorators;

import com.involuntaryminimalism.alerts.Alert;
import com.involuntaryminimalism.alerts.decorators.PriorityAlertDecorator;
import com.involuntaryminimalism.alerts.types.TriggeredAlertFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriorityAlertDecoratorTest {
  @Test
  public void testConditionChange() {
    Alert alertOG =
        new TriggeredAlertFactory().createAlert("047", "Agent 47 completed the mission", 17);
    Alert alertPriority = new PriorityAlertDecorator(alertOG, 2);

    assertEquals("[PRIORITY: 2]: " + alertOG.getCondition(), alertPriority.getCondition());
    }
}
