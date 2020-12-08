import java.util.ArrayList;

public class BankUser {

    public static final int numMembersToDisplay= 2;

    public static int numUsers = 0;

    private String username;
    private char[] password;

    private Integer userID;

    private ArrayList<CheckingAccount> accounts;

    public BankUser(String username, char[] password) {
        this.username = username;
        this.password = password;
        this.userID = numUsers++;
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

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
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

    public boolean isPasswordCorrect(char[] inputpassword){
        if(inputpassword == null){
            return false;
        }
        else if(inputpassword.length != password.length){
            return false;
        }

        for(int i=0; i<inputpassword.length; i++){
            if(password[i] != inputpassword[i]){
                return false;
            }
        }
        return true;
    }

}
