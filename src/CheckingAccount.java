public class CheckingAccount extends Account {



    public CheckingAccount(Integer balance, User user, int numAccounts) {
        super(balance, user, numAccounts);
    }

    public CheckingAccount(String accountID, String balance) {
        super(accountID, balance);
    }

}
