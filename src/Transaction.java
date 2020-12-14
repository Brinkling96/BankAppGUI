import java.time.LocalDateTime;

public class Transaction {
    public static final int numMemsToDisplay = 6;

    private final Account account;
    private final LocalDateTime time;
    private final String transactionType, currency, ID;
    private final int amount;
    public Transaction(Account account, String type,
                       int amount, String curr) {
        this.time = Clock.getClock().getTime();
        this.account = account;
        this.transactionType = type;
        this.amount = amount;
        this.currency = curr;
        this.ID = account.getAccountID().toString()
                + Clock.getClock().getTimeAsLong(this.time);
        DataKeeper.newTransaction(this);
    }

    // Leaving for now in case needed in the future
    // Right now all transactions are already validated and processed before they are created
    public boolean process(Bank bank) {
        boolean success = false;
        switch(transactionType) {
            case "deposit":
                success = account.deposit(amount, currency, bank);
                break;
            case "withdraw":
                success = account.withdraw(amount, currency, bank);
                break;
        }
        return success;
    }

    /**
     * Getter methods
     * @return
     */
    public String getCurrency() {
        return this.currency;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getID() {
        return ID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public String getTime() {
        return Clock.getClock().getTimeAsString(time);
    }

    public String toString() {

        String out = "";
        out += this.getID() + ",";
        out += this.getTransactionType() + ",";
        out += this.getAmount() + ",";
        out += this.getCurrency() + ",";
        out += this.getTime() + ",\n";
        return out;
    }
}
