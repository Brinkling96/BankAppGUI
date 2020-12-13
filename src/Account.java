public abstract class Account implements AccountActions {

    public static Integer numAccounts = 0;

    public static final int numMemsToDisplay = 2;

    protected String accountID;
    protected Integer balance;

    public Account(Integer balance, User user) {
        this.accountID = String.format("%04d",numAccounts);
        this.accountID = user.getUserID() + this.accountID;
        numAccounts++;
        this.balance = balance;
    }

    public String getAccountID() {
        return accountID;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }


    @Override
    public boolean deposit(int amount, String currency) {
        // TODO: Deposit correct amount depending on currency
        return false;
    }

    @Override
    public boolean withdraw(int amount, String currency) {
        // TODO: Withdraw correct amount depending on currency
        return false;
    }
}
