/**
 *
 *  @author Bolshedvorskyi Denys S19374
 *
 */
package zad3;

import java.beans.*;

public class AccountLimitator implements VetoableChangeListener {
    private int min;

    AccountLimitator(int min) {
        this.min = min;
    }

    @Override
    public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException {
        int val = (Integer) e.getNewValue();
        if (val < min)
            throw new PropertyVetoException(e.getPropertyName() + "Unacceptable value change: " + (double) val, e);
    }
}
