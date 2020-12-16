/******************************************************************************
 * Class: Account.java
 * Author: Sarah Shahinpour, Sean Brady, Shuaike Zhou, 
 *******************************************************************************/

/**
 * The Account class contains all functions and attributes necessary in any account
 */
public abstract class Account implements AccountActions {
    public static final int numMemsToDisplay = 2;

    protected String accountID;
    protected Integer balance;
    private int accountNumber;
    protected Bank bank;
    protected User user;

    /**
     * This constructor is used when a new account is created by the user
     */
    public Account(Integer balance, User user, Bank bank) {
        this.balance = balance;
        this.accountNumber = ((CustomerUser)user).getAccountNumber();
        this.bank = bank;
        this.user = user;
        this.setAccountID(user);
    }

    /**
     * This constructor is used when an account is loaded from the files
     */
    public Account(String accountID, String balance) {
        this.accountID = accountID;
        this.balance = Integer.parseInt(balance);
    }

    /**
     * The accountID is set by a new account number, merged by the user
     * number to ensure no duplicates
     * @param user
     */
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

    /**
     * Getters and setters
     */

    public String getAccountID() {
        return accountID;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
    
    public Bank getBank() {
        return this.bank;
    }

    /**
     * Deposits a certain amount of money in a certain currency, converted into usd
     */
    @Override
    public boolean deposit(int amount, String currency) {
        this.setBalance(this.getBalance() + (int) (amount/bank.getConversionRate(currency)));
        bank.createTransaction(this, "deposit", amount, currency);
        return true;
    }

    /**
     * Withdraw a certain amount of money in a certain currency. 
     * Withdrawal fee is automatically charged from the same account.
     */
    @Override
    public boolean withdraw(int amount, String currency) {
        // Withdraw correct amount depending on currency and creates the transaction if valid
    	int balanceAfterWithdraw = 0;
    	balanceAfterWithdraw = this.getBalance() - (int) (amount/bank.getConversionRate(currency)) - bank.getTransactionFee();
    	if(balanceAfterWithdraw < 0) {
        	System.out.println("Current balance is too low to withdraw that amount.");
            return false;
    	} else {
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
