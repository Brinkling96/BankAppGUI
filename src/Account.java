public abstract class Account implements AccountActions {

    public static Integer numAccounts = 0;

    public static final int numMemsToDisplay = 2;

    protected Integer accountID;
    protected Integer balance;

    public Account(Integer balance) {
        this.accountID = numAccounts;
        numAccounts++;
        this.balance = balance;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }


    @Override
    public void deposit(int amount, String currency) {

    }

    @Override
    public void withdraw(int amount, String currency) {

    }
}