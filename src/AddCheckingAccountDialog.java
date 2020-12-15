import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddCheckingAccountDialog extends AddAccountDialog {


    public AddCheckingAccountDialog(Window owner, CustomerUser user, Bank bank) {
        super(owner, user, "Input Balance: ", bank);
    }

    protected void okButtonAction(ActionEvent e) {
        String returnString = null;
        String balanceString = "";
        Integer balance = 0;

        if (balanceField.getText().isEmpty()) {
            returnString = "Input a Balance!";
        } else {
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
                this.account = new CheckingAccount(balance, user, user.getNumAccounts(), bank);
                user.addAccount(account, this.bank);
                this.dispose();
            } else {
                returnString = "Balance must be over 100";
                JOptionPane.showMessageDialog(this, returnString, "Input Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}