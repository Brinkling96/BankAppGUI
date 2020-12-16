import java.util.ArrayList;

//Holds all the information and functionality that is important to a bank
public class Bank {
    public static String[] VALID_CURRENCIES = {"usd", "euro", "yen"};
    private int creationFee, closureFee, transactionFee;
    //Equivalent to 1 USD
    private double yenConversionRate, euroConversionRate, interestRate;
    private int highValueBenchmark;
    private int userNumber;
    private ArrayList<Transaction> dailyReport;
    protected ArrayList<User> users;

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
    }

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

    public User getUser(String accountID) {
        for (User user : users) {
            String uid = accountID.substring(0,4);
            if (uid.equals(user.getUserID())) {
                return user;
            }
        }
        return null;
    }


    public void addUser(CustomerUser user){
        this.userNumber++;
        this.users.add(user);
    }

    public void removeUser(User user){
        this.users.remove(user);
    }

    //The transaction serves as a receipt. Will have already been processed before this method is called.
    public void createTransaction(Account account, String type, int amount, String currency) {
        Transaction t = new Transaction(account, type, amount, currency);
        DataKeeper.newTransaction(t);
        DataKeeper.updateAccount(account, type);
        DataKeeper.updateDailyReports(t);
    }

    // Getters and setters
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

    // public double getYenConversionRate() {
    // 	return this.yenConversionRate;
    // }
    public void setYenConversionRate(double yenConversionRate) {
        this.yenConversionRate = yenConversionRate;
    }

    // public double getEuroConversionRate() {
    // 	return this.euroConversionRate;
    // }
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

    public void addStock(Stock stock){
        stocks.add(stock);
    }

    public void removeStock(Stock stock){stocks.remove(stock);}

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
