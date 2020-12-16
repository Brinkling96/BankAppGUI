import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//Dialog that allows a customer to deposit money into their account
public class DepositMenu extends InputIntMenu {

    private Account account;

    public DepositMenu(Window owner, Account account) {
        super(owner, "Deposit Menu", "Deposit Amount", "Currency");

        this.account = account;


        pack();
        setVisible(true);

    }

    @Override
    protected void submitButtonAction(ActionEvent e) {
        String warning = null;

        if(intField.getText().isEmpty()){
            warning = "Deposit must be entered";
        } else {
            boolean valid = false;
            for (String str : Bank.VALID_CURRENCIES) {
                if (currencyField.getSelectedItem().equals(str)) {
                    valid = true;
                    break;
                }
            }
            if (valid) {
                try {
                    int amount = Integer.parseInt(intField.getText());
                    if (amount > 0) {
                        if (account instanceof LoanAccount) {
                            if (amount <= ((LoanAccount) account).getPrincipal()) {
                                int result = JOptionPane.showConfirmDialog(this, "Pay " + (String) currencyField.getSelectedItem() + " " + Integer.toString(amount) + " ?");
                                if (result == JOptionPane.OK_OPTION) {
                                    ((LoanAccount) account).deposit(amount,  (String) currencyField.getSelectedItem());
                                    this.dispose();
                                }
                            } else {
                                warning = "Deposit amount is not valid";
                            }
                        } else {
                            int result = JOptionPane.showConfirmDialog(this, "Deposit " + currencyField.getSelectedItem() + " " + Integer.toString(amount) + " ?");
                            if (result == JOptionPane.OK_OPTION) {
                                account.deposit(amount, (String) currencyField.getSelectedItem());
                                this.dispose();
                            }
                        }
                    } else {
                        warning = "Deposit amount is not valid";
                    }

                } catch (NumberFormatException err) {
                    warning = "Deposit a number";
                }
            } else {
                warning = "Currency must be 'usd', 'euro' or 'yen'";
            }
        }
        if(warning != null){
            JOptionPane.showMessageDialog(this,warning,"Input Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

}
