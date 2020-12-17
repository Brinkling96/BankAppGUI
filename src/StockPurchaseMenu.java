import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//Dialog that allows a customer to withdraw money from an account
public class StockPurchaseMenu extends InputIntMenu {

    private Account account;
    private Bank bank;
    private Stock stock;

    public StockPurchaseMenu(Window window, Account account, Bank bank, Stock stock) {
        super(window, "Purchase Stock", "Shares", "Ignore");

        this.account = account;
        this.bank = bank;
        this.stock = stock;

        pack();
        setVisible(true);
    }

    @Override
    protected void submitButtonAction(ActionEvent e) {
        String warning = null;

        if(intField.getText().isEmpty()){
            warning = "Shares must be entered";
        } else {
            try {
                int shares = Integer.parseInt(intField.getText());
                int amount = shares * stock.getValue();
                if (amount > 0 && amount < account.getBalance()) {
                    int result = JOptionPane.showConfirmDialog(this,
                            "Purchase " + Integer.toString(shares) + " shares of " 
                            + stock.getName() 
                            + "\nThere is a fee of $" 
                            + this.bank.getTransactionFee());
                            if (result == JOptionPane.OK_OPTION) {
                                
                                if (stock instanceof NYSE_Stock) {
                                    ((SecurityAccount) account).buyStock(
                                        new NYSE_Stock(stock.getName(), 
                                                        stock.getValue(), 
                                                        shares));
                                }
                                stock.setShares(stock.getShares() - shares);
                                DataKeeper.updateStocks(stock, "update");
                                bank.createTransaction(account, "stock purchase", amount, "usd");
                                this.dispose();
                            }
                } else {
                    warning = "Total stock value exeeds balance or smaller than 1";
                }
            } catch (NumberFormatException err) {
                warning = "Withdraw a number";
            }
        }
        if(warning != null){
            JOptionPane.showMessageDialog(this,warning,"Input Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
