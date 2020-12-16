import java.util.ArrayList;

//Type of account that handles stocks
public class SecurityAccount extends Account{
    private ArrayList<Stock> stocks;
    public SecurityAccount(Integer balance, User user, Bank bank) {
        super(balance, user, bank);
        this.setStocks(new ArrayList<>());
    }

    public void buyStock(Stock stock){
        int balanceAfterPurchase = this.getBalance() - (int) stock.getValue()*stock.getShares();
        if(balanceAfterPurchase < 0) {
            System.out.println("Current balance is too low to purchase that amount.");
        }else {
            this.setBalance(balanceAfterPurchase);
            this.getStocks().add(stock);
            this.getBank().createTransaction(this, "stock purchase",
                    -(stock.getValue()*stock.getShares()), "usd");
        }
    }

    public void sellStock(Stock stock) {
        this.setBalance(this.getBalance() + (stock.getValue()*stock.getShares()));
        this.getStocks().add(stock);
        this.getBank().createTransaction(this, "stock sold", (stock.getValue()*stock.getShares()), "usd");

    }

    //Getters and setters
    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public String toString() {
        String out = "";
        out += String.valueOf(balance) + ",";
        for (Stock stock : stocks) {
            out += stock.toString();
        }
        out += "\n";
        return out;
    }
}
