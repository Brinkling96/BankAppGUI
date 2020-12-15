import java.util.ArrayList;

public class Bank {
    private int creationFee;
    private int closureFee;
    private int transactionFee;
    //Equivalent to 1 USD
    //todo ask user which type of currency to enter?
    private double yenConversionRate, euroConversionRate;
    private double interestRate;
    private int highValueBenchmark;
    private final String name;
    private ArrayList<Transaction> transactions;
    protected ArrayList<User> users;

    public Bank(ArrayList<User> users) {
        this.users = users;
        this.transactionFee = 3;
    	this.yenConversionRate = 103.96;
    	this.euroConversionRate = .82;
    	this.highValueBenchmark = 5000;
    	this.closureFee = 5;
    	this.creationFee = 5;
        this.name = "";
        this.transactions = new ArrayList<Transaction>();
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
        this.users.add(user);
    }

    public void removeUser(User user){
        this.users.remove(user);
    }

    public int getNumUsers() { return this.users.size(); }


    //The transaction serves as a receipt. Will have already been processed before this method is called.
    public void createTransaction(Account account, String type, int amount, String currency) {
        Transaction t = new Transaction(account, type, amount, currency);
        transactions.add(t);
        DataKeeper.newTransaction(t);
        DataKeeper.updateAccount(account, type);
        DataKeeper.updateDailyReports(t);
    }

    // Getters and setters
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public int getTransactionFee() {
    	return this.transactionFee;
    }
    public void setTransactionFee(int transactionFee) {
        this.transactionFee = transactionFee;
    }

    public double getYenConversionRate() {
    	return this.yenConversionRate;
    }
    public void setYenConversionRate(double yenConversionRate) {
        this.yenConversionRate = yenConversionRate;
    }

    public double getEuroConversionRate() {
    	return this.euroConversionRate;
    }
    public void setEuroConversionRate(double euroConversionRate) {
        this.euroConversionRate = euroConversionRate;
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
}
