import java.util.ArrayList;

public class Bank {
    private int creationFee, closureFee, transactionFee;
    //Equivalent to 1 USD
    private double yenConversionRate, euroConversionRate;
    private double interestRate;
    private final String name;
    private ArrayList<Transaction> transactions;
    protected ArrayList<User> users;

    public Bank(ArrayList<User> users) {
        this.users = users;
        this.creationFee = 0;
        this.transactionFee = 3;
    	this.yenConversionRate = 103.96;
    	this.euroConversionRate = .82;
        this.name = "";
        this.transactions = new ArrayList<Transaction>();
    }

    public User getUser(String username, char[] password){
        for(User user : users){
            if(user.getUsername().equals(username)){
                if(user.isPasswordCorrect(password)){
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

    //The transaction serves as a receipt. Will have already been processed before this method is called.
    public void createTransaction(Account account, String type, int amount, String currency) {
        transactions.add(new Transaction(account, type, amount, currency));
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    
    
    public int getTransactionFee() {
    	return this.transactionFee;
    }
    
    public double getYenConversionRate() {
    	return this.yenConversionRate;
    }
    
    public double getEuroConversionRate() {
    	return this.euroConversionRate;
    }
}
