public class CheckingAccount extends Account {



    public CheckingAccount(Integer balance, User user, Bank bank) {
        super(balance, user, bank);
    }

    public CheckingAccount(String accountID, String balance) {
        super(accountID, balance);
    }

}
