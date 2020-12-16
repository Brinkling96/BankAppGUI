import java.util.ArrayList;

public class CustomerUser extends User{

    public static final int numMembersToDisplay= 3;
    private int accountNumber;
    private ArrayList<Account> accounts;

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
    public CustomerUser(String username, String password, String uid, String lastLogin) {
        super(username, password, uid, lastLogin);
        this.accounts = new ArrayList<>();
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public int getAccountNumber() { return this.accountNumber; }

    public void loadAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
        int num = 0;
        for (Account a : accounts) {
            String id = a.getAccountID().replaceAll("[^\\d.]", "");
            if (Integer.parseInt(id) >= num) {
                num = Integer.parseInt(id);
            }
        }
        this.accountNumber = num + 1;
    }

    // todo charge fees to add and remove accounts, I think I did it correctly, but we can never be too sure
    public void addAccount(Account account, Bank bank){
        System.out.println(bank);
        this.accountNumber++;
        this.accounts.add(account);
        account.setBalance(account.getBalance() - bank.getCreationFee());
        bank.createTransaction(account, "account creation fee", -bank.getCreationFee(), "usd");
    }

    public void removeAccount(Account account, Bank bank){
        this.accounts.remove(account);
        account.setBalance(account.getBalance() - bank.getClosureFee());
        bank.createTransaction(account, "account removal fee", -bank.getClosureFee(), "usd");
    }

    // // todo allow users to take out loans
    // public void takeOutLoan(int originalValue, double interest, Collateral collateral, Bank bank){
    //     this.accounts.add(new LoanAccount(originalValue, interest, collateral, this, bank));
    // }

    public Account getAccount(String AccountID){
        for(Account acct: accounts){
            if(acct.getAccountID().equals(AccountID)){
                return acct;
            }
        }
        return null;
    }

    // Getters and setters
    // public ArrayList<Loan> getLoans() {
    //     return null;
    // }

    // public void setLoans(ArrayList<Loan> loans) {
    //     this.loans = loans;
    // }

}
