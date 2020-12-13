public class SavingsAccount extends Account{

    public SavingsAccount(Integer balance, User user) {
        super(balance, user);
        this.accountID = getAccountID() + "sv";
    }

}
