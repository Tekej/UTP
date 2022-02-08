/**
 *
 *  @author Bolshedvorskyi Denys S19374
 *
 */
package zad3;

import java.beans.*;


class AccountTest {
    public static void main(String[] args) {
        Account acc1 = null, acc2 = null;

        try {
            acc1 = new Account(100);
            acc2 = new Account();

            AccountLimitator acclim = new AccountLimitator(-200);

            acc1.addVetoableChangeListener(acclim);
            acc2.addVetoableChangeListener(acclim);
            AccountChange accountChange = new AccountChange();

            acc1.addVetoableChangeListener(accountChange);
            acc2.addVetoableChangeListener(accountChange);

            System.out.println(acc1);
            System.out.println(acc2);
            System.out.println();

            acc2.deposit(1000);
            System.out.println(acc1);
            System.out.println(acc2);
            System.out.println();

            acc1.withdraw(250);
            System.out.println(acc1);
            System.out.println(acc2);
            System.out.println();

            acc2.transfer(acc1, 1200);

            System.out.println(acc1);
            System.out.println(acc2);
            System.out.println();

            acc2.transfer(acc1, 1);
            System.out.println(acc1);
            System.out.println(acc2);

        } catch (PropertyVetoException e) {
            System.out.println(e.getMessage());
            System.out.println(acc1);
            System.out.println(acc2);
        }
    }
}
