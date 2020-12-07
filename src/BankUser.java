import java.util.ArrayList;

public class BankUser {

    public static final int numMembersToDisplay= 2;

    private String username;

    private Integer userID;

    private ArrayList<CheckingAccount> accounts;

    public BankUser(String username, Integer userID) {
        this.username = username;
        this.userID = userID;
        this.accounts = new ArrayList<>();
    }

    public ArrayList<CheckingAccount> getAccounts() {
        return accounts;
    }

    public void addCheckingAccount(CheckingAccount checkingAccount){
       this.accounts.add(checkingAccount);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public CheckingAccount getAccount(int AccountID){
        for(CheckingAccount acct: accounts){
            if(acct.getAccountID() == AccountID){
                return acct;
            }
        }
        return null;
    }

}
