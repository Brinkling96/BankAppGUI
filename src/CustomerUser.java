import java.util.ArrayList;

public class CustomerUser extends User{

    public static final int numMembersToDisplay= 3;
    private int accountNumber;
    private ArrayList<Account> accounts;
    private ArrayList<Loan> loans;

    /**
     * This constructor creates a new customer user
     * @param username
     * @param password
     * @param numUsers
     */
    public CustomerUser(String username, char[] password, int userNumber) {
        super(username,password, userNumber);
        this.accounts = new ArrayList<>();
    }

    /**
     * This constructor creates a returning customer from the files
     * @param username
     * @param password
     * @param uid
     * @param status
     */
    public CustomerUser(String username, String password, String uid, String status) {
        super(username, password, uid, status);
        this.accounts = new ArrayList<>();
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public int getNumAccounts() { return this.accountNumber; }

    public void loadAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    // todo charge fees to add and remove accounts, I think I did it correctly, but we can never be too sure
    public void addAccount(Account account, Bank bank){
        this.accountNumber++;
        this.accounts.add(account);
        account.setBalance(account.getBalance() - bank.getCreationFee());
        bank.createTransaction(account, "add", -bank.getCreationFee(), "usd");
    }

    public void removeAccount(Account account, Bank bank){
        this.accounts.remove(account);
        account.setBalance(account.getBalance() - bank.getClosureFee());
        bank.createTransaction(account, "remove", -bank.getClosureFee(), "usd");
    }

    // todo allow users to take out loans
    public void takeOutLoan(int originalValue, double interest, Collateral collateral){
        this.getLoans().add(new Loan(originalValue, interest, collateral));
    }

    public Account getAccount(String AccountID){
        for(Account acct: accounts){
            if(acct.getAccountID().equals(AccountID)){
                return acct;
            }
        }
        return null;
    }

    // Getters and setters
    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }

}
