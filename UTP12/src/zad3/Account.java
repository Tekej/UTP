/**
 *
 *  @author Bolshedvorskyi Denys S19374
 *
 */
package zad3;

import java.beans.*;

public class Account {
    private double balance;
    private int number;
    private static int count = 0;

    private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetoableChange = new VetoableChangeSupport(this);

    public synchronized void addVetoableChangeListener(VetoableChangeListener vetoableChangeListener) {
        vetoableChange.addVetoableChangeListener(vetoableChangeListener);
    }

    Account() {
        this.balance = 0;
        this.number = ++count;
    }

    Account(double balance) {
        this.balance = balance;
        this.number = ++count;
    }

    public void deposit(double bal) throws PropertyVetoException {
        setBalance(bal);
    }

    public void withdraw(double bal) throws PropertyVetoException {
        setBalance(balance - bal);
    }

    public void transfer(Account account, double bal) throws PropertyVetoException {
        setTransfer(account, bal);
    }

    private synchronized void setTransfer(Account account, double bal) throws PropertyVetoException {
        setBalance(this.balance - bal);
        account.setBalance(account.balance + bal);
    }

    private synchronized void setBalance(double bal) throws PropertyVetoException {
        int oldValue = (int) this.balance;
        vetoableChange.fireVetoableChange(number + ": ", new Integer(oldValue), new Integer((int) bal));
        this.balance = bal;
        propertyChange.firePropertyChange(number + ": ", new Integer(oldValue), new Integer((int) bal));
    }

    public String toString() {
        return "Acc " + number + ": " + balance;
    }
}
