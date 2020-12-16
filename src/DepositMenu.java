import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DepositMenu extends InputIntMenu {

    private Account account;


    public DepositMenu(Window owner, Account account) {
        super(owner, "Deposit Menu", "Deposit Amount");

        this.account = account;


        pack();
        setVisible(true);

    }


    @Override
    protected void submitButtonAction(ActionEvent e) {
        String warning = null;

        if(intField.getText().isEmpty()){
            warning = "Deposit must be entered";
        }
        else{
            try{
                int amount = Integer.parseInt(intField.getText());
                if(amount> 0) {
                    if (account instanceof LoanAccount) {
                        if (amount <= ((LoanAccount) account).getPrincipal()) {
                            int result = JOptionPane.showConfirmDialog(this, "Pay $" + Integer.toString(amount) + " ?");
                            if (result == JOptionPane.OK_OPTION) {
                                ((LoanAccount) account).deposit(amount, "usd");
                                this.dispose();
                            }
                        } else {
                            warning = "Deposit amount is not valid";
                        }
                    } else {
                        int result = JOptionPane.showConfirmDialog(this,
                                "Deposit $" + Integer.toString(amount) + " ?");
                        if (result == JOptionPane.OK_OPTION) {
                            account.deposit(amount, "usd");
                            this.dispose();
                        }
                    }
                }
                else{
                    warning = "Deposit amount is not valid";
                }

            }catch (NumberFormatException err){
                warning = "Deposit a number";
            }
        }
        if(warning != null){
            JOptionPane.showMessageDialog(this,warning,"Input Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

}
