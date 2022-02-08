/**
 *
 *  @author Bolshedvorskyi Denys S19374
 *
 */
package zad3;

import java.beans.*;

public class AccountChange implements VetoableChangeListener {
    @Override
    public void vetoableChange(PropertyChangeEvent e) {
        Integer oldVal = (Integer) e.getOldValue(), newVal = (Integer) e.getNewValue();
        if (newVal < 0)
            System.out.println(e.getPropertyName() + "Value changed from " + (double) oldVal + " to " + (double) newVal + ", balance < 0!");
        else
            System.out.println(e.getPropertyName() + "Value changed from " + (double) oldVal + " to " + (double) newVal);
    }
}
