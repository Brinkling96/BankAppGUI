public abstract class Account implements AccountActions {

    /*todo calculate interest. maybe add variable to bank class for last updated (need to make sure to write to a doc)
        and for how many days have past compound interest for that many days and update accounts.
        we can say it compounds at 9am or something. relevant to savings accounts over highValueBenchmark, loans and possibly the stock market.
    */
    public static final int numMemsToDisplay = 2;

    protected String accountID;
    protected Integer balance;
    private int accountNumber;
    private Bank bank;
    protected User user;

    public Account(Integer balance, User user, Bank bank) {
        this.balance = balance;
        this.accountNumber = ((CustomerUser)user).getAccountNumber();
        this.bank = bank;
        this.user = user;
        this.setAccountID(user);
    }

    public Account(String accountID, String balance) {
        this.accountID = accountID;
        this.balance = Integer.parseInt(balance);
    }

    private void setAccountID(User user) {
        this.accountID = String.format("%04d",accountNumber);
        this.accountID = user.getUserID() + this.accountID;
        accountNumber++;
        if (this instanceof CheckingAccount)
            this.accountID += "ck";
        else if (this instanceof SavingsAccount)
            this.accountID += "sv";
        else if (this instanceof SecurityAccount)
            this.accountID += "sc";
        else if (this instanceof LoanAccount)
            this.accountID += "ln";
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

    public Bank getBank() {
        return this.bank;
    }

    @Override
    public boolean deposit(int amount, String currency) {
        // Deposit correct amount depending on currency
        // Creates the transaction if valid
        switch (currency) {
            case "usd" -> this.setBalance(this.getBalance() + amount);
            case "yen" -> this.setBalance((int) (this.getBalance() + amount / bank.getYenConversionRate()));
            case "euro" -> this.setBalance((int) (this.getBalance() + amount / bank.getEuroConversionRate()));
            default -> {
                System.out.println("Currency not supported.");
                return false;
            }
        }

        bank.createTransaction(this, "deposit", amount, currency);

        return true;

    }

    // Fee is automatically charged from the same account.
    @Override
    public boolean withdraw(int amount, String currency) {
        // Withdraw correct amount depending on currency
        // Creates the transaction if valid
    	int balanceAfterWithdraw = 0;
        switch (currency) {
            case "usd" -> balanceAfterWithdraw = this.getBalance() - amount - bank.getTransactionFee();
            case "yen" -> balanceAfterWithdraw =
                    (int) (this.getBalance() - amount / bank.getYenConversionRate() - bank.getTransactionFee());
            case "euro" -> balanceAfterWithdraw =
                    (int) (this.getBalance() - amount / bank.getEuroConversionRate() - bank.getTransactionFee());
            default -> {
                System.out.println("Currency not supported.");
                return false;
            }
        }
    	
    	if(balanceAfterWithdraw < 0) {
        	System.out.println("Current balance is too low to withdraw that amount.");
            return false;
    	}else {
    		this.setBalance(balanceAfterWithdraw);
    	}

    	bank.createTransaction(this, "withdraw", -amount, currency);
    	bank.createTransaction(this, "withdraw fee", -bank.getTransactionFee(), currency);
    	return true;

    }

    public String toString() {
        String out = "";
        out += getAccountID() + ",";
        out += getBalance() + ",";
        return out;
    }
}
