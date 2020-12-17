import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//Dialog that allows a customer to withdraw money from an account
public class StockSaleMenu extends InputIntMenu {

    private Account account;
    private Bank bank;
    private Stock stock;

    public StockSaleMenu(Window window, Account account, Bank bank, Stock stock) {
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
                if (shares > 0 && shares <= stock.getShares()) {
                    int result = JOptionPane.showConfirmDialog(this,
                            "Sell " + Integer.toString(shares) + " shares of " 
                            + stock.getName() 
                            + "\nThere is a fee of $" 
                            + this.bank.getTransactionFee());
                            if (result == JOptionPane.OK_OPTION) {
                                Stock soldStock = new NYSE_Stock(stock.getName(), stock.getValue(), shares);
                                ((SecurityAccount) account).sellStock(soldStock);
                                bank.addStock(soldStock);
                                DataKeeper.updateStocks(stock, "update");
                                this.dispose();
                            }
                } else {
                    warning = "Shares exeeds total shares in account";
                }
            } catch (NumberFormatException err) {
                warning = "Enter a number";
            }
        }
        if(warning != null){
            JOptionPane.showMessageDialog(this,warning,"Input Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
