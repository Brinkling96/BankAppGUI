/******************************************************************************
 * Class: AddCheckingAccount.java
 * Author: Sarah Shahinpour, Sean Brady, Shuaike Zhou, 
 *******************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Dialog that allows the user to create a checking account
 */
public class AddCheckingAccountDialog extends AddAccountDialog {

    public AddCheckingAccountDialog(Window owner, CustomerUser user, Bank bank) {
        super(owner, user, "Input Balance: ", bank);
        this.pack();
        this.setVisible(true);
    }

    protected void okButtonAction(ActionEvent e) {
        String returnString = null;
        String balanceString = "";
        Integer balance = 0;

        if (balanceField.getText().isEmpty()) {
            returnString = "Input a Balance!";
        } else {
            // Check balance
            try {
                balance = Integer.parseInt(balanceField.getText());
                if(balance < this.bank.getCreationFee()) {
                    returnString = "Balance must be greater than the creation fee of $" + this.bank.getCreationFee();
                }
            } catch (NumberFormatException err) {
                returnString = "Balance must be a number!";
            }
        }

        if (returnString != null) {
            JOptionPane.showMessageDialog(this, returnString, "Input Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            if (balance > 100) {
                int result = JOptionPane.showConfirmDialog(this,"Creation fee of $" + bank.getCreationFee(),
                        "Fee",JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.OK_OPTION) {
                    user.addAccount(account, this.bank);
                    this.account = new CheckingAccount(balance, user, bank);
                }
                this.dispose();
            } else {
                returnString = "Balance must be over 100";
                JOptionPane.showMessageDialog(this, returnString, "Input Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}