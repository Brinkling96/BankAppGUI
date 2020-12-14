import java.util.ArrayList;

public class Bank {
    private int creationFee, closureFee, transactionFee;
    private double interestRate;
    private final String name;
    private ArrayList<Transaction> transactions;
    protected ArrayList<User> users;

    public Bank(ArrayList<User> users) {
        this.users = users;
        this.creationFee = 0;
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
    public User getUser(String accountID){

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

    public void createTransaction(Account account, String type, int amount, String currency) {
        this.transactions.add(new Transaction(account, type, amount, currency));
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
