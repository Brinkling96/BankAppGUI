import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WithdrawMenu extends InputIntMenu {

    private Account account;
    private Bank bank;

    public WithdrawMenu(Window window, Account account, Bank bank) {
        super(window, "Withdraw","Withdraw Amount", "Currency");

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
            if (currencyField.getText().isEmpty()) {
                warning = "Currency must be entered";
            } else {
                boolean valid = false;
                for (String c : Bank.VALID_CURRENCIES) {
                    if (currencyField.getText().equals(c)) {
                        valid = true;
                        break;
                    }
                }
                if (valid) {
                    try{
                        //todo adding the fee
                        if (account instanceof LoanAccount) {
                            warning = "Cannot withdraw from loan account";
                        } else {
                            int amount = Integer.parseInt(intField.getText());
                            if(amount> 0 && account.getBalance()> amount/bank.getConversionRate(currencyField.getText())) {
                                int result = JOptionPane.showConfirmDialog(this,
                                        "Withdraw $" + Integer.toString(amount) + currencyField.getText() + "?\nThere is a fee of $" + this.bank.getTransactionFee());
                                if (result == JOptionPane.OK_OPTION) {
                                    account.withdraw(amount, currencyField.getText());
                                    this.dispose();
                                }
                            } else {
                                warning = "Withdrawal amount is not valid, either less than 1 or greater than balance";
                            }
                        }
                    }catch (NumberFormatException err){
                        warning = "Withdraw a number";
                    }
                } else {
                    warning = "Currency must be 'usd', 'euro' or 'yen'";
                }
            }
        }
        if(warning != null){
            JOptionPane.showMessageDialog(this,warning,"Input Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
