import java.util.ArrayList;

public class Bank {
    private int creationFee, closureFee, transactionFee;
    private double interestRate;
    private final String name;
    private ArrayList<Transaction> transactions;
    protected ArrayList<BankUser> users;
    

    public Bank(ArrayList<BankUser> users) {
        this.users = users;
        this.creationFee = 0;
        this.name = "";
        this.transactions = new ArrayList<Transaction>();
    }

    public BankUser getUser(String username, char[] password){
        for(BankUser user : users){
            if(user.getUsername().equals(username)){
                if(user.isPasswordCorrect(password)){
                    return user;
                }
            }
        }
        return null;
    }

    public void addUser(BankUser user){
        this.users.add(user);
    }

    public void createTransaction(Account source, Account target, int amount, String currency) {
        this.transactions.add(new Transaction(source, target, amount, currency))
    }


}
