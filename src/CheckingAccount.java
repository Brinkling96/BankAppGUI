public class CheckingAccount extends Account {



    public CheckingAccount(Integer balance, User user, int numAccounts, Bank bank) {
        super(balance, user, numAccounts, bank);
    }

    public CheckingAccount(String accountID, String balance) {
        super(accountID, balance);
    }

}
