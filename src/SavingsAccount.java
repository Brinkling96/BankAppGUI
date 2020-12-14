public class SavingsAccount extends Account{

    public SavingsAccount(Integer balance, User user, int numAccounts) {

        super(balance, user, numAccounts);
    }

    public SavingsAccount(String accountID, String balance) {
        super(accountID, balance);
    }

}
