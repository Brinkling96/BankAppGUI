/******************************************************************************
 * Class: Bank.java
 * Author: Sarah Shahinpour, Sean Brady, Shuaike Zhou, 
 *******************************************************************************/

import java.util.ArrayList;

/**
 * Holds all the information and functionality that is important to a bank
 */ 
public class Bank {
    // The three currencies used by this bank
    public static String[] VALID_CURRENCIES = {"usd", "euro", "yen"};
    private int creationFee, closureFee, transactionFee;
    //Equivalent to 1 USD
    private double yenConversionRate, euroConversionRate, interestRate;
    private int highValueBenchmark;
    private int userNumber;
    private ArrayList<Transaction> dailyReport;
    private int profit;
    protected ArrayList<User> users;
    private Banker banker;

    protected ArrayList<Stock> stocks = new ArrayList<>();

    public Bank(ArrayList<User> users) {
        this.users = users;
        this.transactionFee = 3;
    	this.yenConversionRate = 103.96;
    	this.euroConversionRate = .82;
    	this.highValueBenchmark = 5000;
    	this.interestRate = .20;
    	this.closureFee = 5;
    	this.creationFee = 5;
        this.dailyReport = new ArrayList<Transaction>();
        this.userNumber = 0;
        this.profit = 0;
    }

    // Major methods

    public void addUser(CustomerUser user){
        this.userNumber++;
        this.users.add(user);
    }

    public void removeUser(User user){
        this.users.remove(user);
    }

    //The transaction serves as a receipt. Will have already been processed before this method is called.
    public void createTransaction(Account account, String type, int amount, String currency) {
        switch (type){
            case "withdraw fee":
            case "interest payment":
            case "account creation fee":
            case "account removal fee":
                this.setProfit(this.getProfit() - (int)(amount/getConversionRate(currency)));
        }

        Transaction t = new Transaction(account, type, amount, currency);
        
        DataKeeper.newTransaction(t);
        DataKeeper.updateAccount(account, type);
        DataKeeper.updateUser(banker, "update");
        DataKeeper.updateDailyReports(t);
    }


    // Getters and setters

    // Get user by username/password
    public User getUser(String username, char[] password){
        for(User user : users){
            if(user.getUsername().equals(username)){
                if(user.isPasswordCorrect(password)){
                    System.out.println("user found");
                    return user;
                }
            }
        }
        return null;
    }

    // Get user by account ID
    public User getUser(String accountID) {
        for (User user : users) {
            String uid = accountID.substring(0,4);
            if (uid.equals(user.getUserID())) {
                return user;
            }
        }
        return null;
    }
    
    public ArrayList<Transaction> getDailyReport() {
        return dailyReport;
    }

    public void setDailyReport(ArrayList<Transaction> dailyReport) {
        this.dailyReport = dailyReport;
    }

    public int getTransactionFee() {
    	return this.transactionFee;
    }
    public void setTransactionFee(int transactionFee) {
        this.transactionFee = transactionFee;
    }

    public void setYenConversionRate(double yenConversionRate) {
        this.yenConversionRate = yenConversionRate;
    }

    public void setEuroConversionRate(double euroConversionRate) {
        this.euroConversionRate = euroConversionRate;
    }

    public double getConversionRate(String currency) {
        double rate = 0;
        switch(currency) {
            case "usd" -> rate = 1;
            case "euro" -> rate = euroConversionRate;
            case "yen" -> rate = yenConversionRate;
        }
        return rate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getHighValueBenchmark() {
        return highValueBenchmark;
    }

    public void setHighValueBenchmark(int highValueBenchmark) {
        this.highValueBenchmark = highValueBenchmark;
    }

    public int getCreationFee() {
        return creationFee;
    }

    public void setCreationFee(int creationFee) {
        this.creationFee = creationFee;
    }

    public int getClosureFee() {
        return closureFee;
    }

    public void setClosureFee(int closureFee) {
        this.closureFee = closureFee;
    }

    public int getUserNumber() { 
        return this.userNumber + 1; 
    }

    public void setUserNumber() {
        for (User user : users) {
            int num = Integer.parseInt(user.getUserID().substring(2,4));
            if (this.userNumber <= num) {
                this.userNumber = num;
            }
        }
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void setBanker(Banker banker) {
        this.banker = banker;
    }

    public User getBanker() {
        return this.banker;
    }

    /**
     * Stock methods
     */

    public void loadStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public void addStock(Stock newStock){
        boolean exist = false;
        for (Stock stock : stocks) {
            if (stock.getName().equals(newStock.getName())) {
                stock.setShares(stock.getShares() + newStock.getShares());
                exist = true;
            }
        }
        if (!exist) {
            stocks.add(newStock);
        }
    }

    public void removeStock(Stock stock){
        stocks.remove(stock);
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public Stock findStock(String stockName){
        for(Stock s : stocks){
            if(s.getName().equals(stockName)){
                return s;
            }
        }
        return null;
    }
}
