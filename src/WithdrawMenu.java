import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WithdrawMenu extends InputIntMenu {

    private Account account;
    private Bank bank;

    public WithdrawMenu(Window window, Account account, Bank bank) {
        super(window, "Withdraw","Withdraw Amount");

        this.account = account;
        this.bank = bank;

        pack();
        setVisible(true);
    }

    @Override
    protected void submitButtonAction(ActionEvent e) {
        String warning = null;

        if(intField.getText().isEmpty()){
            warning = "Withdraw must be entered";
        }
        else{
            try{
                //todo adding the fee
                int amount = Integer.parseInt(intField.getText());
                if(amount> 0 && account.getBalance()> amount) {
                    int result = JOptionPane.showConfirmDialog(this,
                            "Withdraw $" + Integer.toString(amount) + "?\nThere is a fee of $" + this.bank.getTransactionFee());
                    if (result == JOptionPane.OK_OPTION) {
                        account.withdraw(amount, "usd");
                        this.dispose();
                    }
                }
                else{
                    warning = "Deposit amount is not valid, either less than 1 or greater than balance";
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
