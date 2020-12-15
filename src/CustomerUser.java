import java.util.ArrayList;

public class CustomerUser extends User{

    public static final int numMembersToDisplay= 3;


    private ArrayList<Account> accounts;
    private ArrayList<Loan> loans;

    public CustomerUser(String username, char[] password) {
        super(username,password);
        this.accounts = new ArrayList<>();
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }


    // TODO: charge fees to add and remove accounts
    public void addAccount(Account account){
       this.accounts.add(account);
    }

    public void removeAccount(Account account){
        this.accounts.remove(account);
    }

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
