import java.util.ArrayList;

public class CustomerUser extends User{

    public static final int numMembersToDisplay= 3;


    private ArrayList<Account> accounts;

    public CustomerUser(String username, char[] password) {
        super(username,password);
        this.accounts = new ArrayList<>();
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account){
       this.accounts.add(account);
    }



    public Account getAccount(int AccountID){
        for(Account acct: accounts){
            if(acct.getAccountID() == AccountID){
                return acct;
            }
        }
        return null;
    }

}
