public class CheckingAccount extends Account {



    public CheckingAccount(Integer balance, User user) {
        super(balance, user);
        this.accountID = this.getAccountID() + "ck";
    }



}
