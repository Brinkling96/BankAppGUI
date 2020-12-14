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
    public boolean deposit(int amount, String currency, Bank bank) {
        // TODO: Deposit correct amount depending on currency
    	// Create a Transaction
    	if(currency.equals("usd")) {
    		this.setBalance(this.getBalance() + amount);
    	}else if(currency.equals("yen")) {
    		this.setBalance((int)(this.getBalance() + amount*bank.getYenConversionRate()));
    	}else if(currency.equals("euro")) {
    		this.setBalance((int)(this.getBalance() + amount*bank.getEuroConversionRate()));
    	}else {
        	System.out.println("Currency not supported.");
            return false;
    	}
        
    	bank.createTransaction(this, "deposit", amount, currency);
		return true;

    }

    @Override
    public boolean withdraw(int amount, String currency, Bank bank) {
        // TODO: Withdraw correct amount depending on currency
    	// Charge fee
    	// Create a Transaction
    	int balanceAfterWithdraw = 0;
    	if(currency.equals("usd")) {
    		balanceAfterWithdraw = this.getBalance() - amount - bank.getTransactionFee();
    	}else if(currency.equals("yen")) {
    		balanceAfterWithdraw = (int)(this.getBalance() - amount*bank.getYenConversionRate() - bank.getTransactionFee());
    	}else if(currency.equals("euro")) {
    		balanceAfterWithdraw = (int)(this.getBalance() - amount*bank.getEuroConversionRate() - bank.getTransactionFee());
    	}else {
        	System.out.println("Currency not supported.");
            return false;
    	}
    	
    	if(balanceAfterWithdraw < 0) {
        	System.out.println("Current balance is too low to withdraw that amount.");
            return false;
    	}else {
    		this.setBalance(balanceAfterWithdraw);
    	}
    	
    	bank.createTransaction(this, "withdraw", amount, currency);
    	return true;

    }
}
