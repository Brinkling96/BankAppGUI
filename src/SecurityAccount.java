import java.util.ArrayList;

//Type of account that handles stocks
public class SecurityAccount extends Account{
    private ArrayList<Stock> stocks;
    public static final int STARTING_BALANCE = 1000;

    public SecurityAccount(Integer balance, User user, Bank bank) {
        super(balance, user, bank);
        this.setStocks(new ArrayList<>());
    }

    public SecurityAccount(String accountID, String balance, ArrayList<Stock> stocks) {
        super(accountID, balance);
        this.setStocks(stocks);
    }

    public void buyStock(Stock stock){
        boolean exist = false;
        for (Stock myStock : stocks) {
            if (myStock.getName().equals(stock.getName())) {
                myStock.setShares(stock.getShares() + myStock.getShares());
                exist = true;
            }
        }
        if (!exist) {
            this.stocks.add(stock);
        }
        DataKeeper.buySellStock((SecurityAccount) this, stock);
        this.setBalance(this.getBalance() - (stock.getValue()*stock.getShares()));
        bank.createTransaction(this, "stock purchase", -(stock.getValue()*stock.getShares()), "usd");
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
}
