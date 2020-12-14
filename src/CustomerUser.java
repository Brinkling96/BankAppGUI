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


    // Need to charge fees to add and remove accounts
    public void addAccount(Account account){
       this.accounts.add(account);
    }

    public void removeAccount(Account account){
        this.accounts.remove(account);
    }



    public Account getAccount(String AccountID){
        for(Account acct: accounts){
            if(acct.getAccountID().equals(AccountID)){
                return acct;
            }
        }
        return null;
    }

}
