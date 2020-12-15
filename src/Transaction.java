import java.time.LocalDateTime;

public class Transaction {
    public static final int numMemsToDisplay = 6;

    private Account account;
    private final String transactionType, currency, ID, time;
    private final int amount;
    public Transaction(Account account, String type,
                       int amount, String curr) {
        LocalDateTime currTime = Clock.getClock().getTime();
        this.time = Clock.getClock().getTimeAsString(currTime);
        this.account = account;
        this.transactionType = type;
        this.amount = amount;
        this.currency = curr;
        this.ID = account.getAccountID().toString()
                + Clock.getClock().getTimeAsLong(currTime);
    }

    public Transaction(String id, String type, String currency, String amount, String time) {
        this.ID = id;
        this.transactionType = type;
        this.currency = currency;
        this.amount = Integer.parseInt(amount);
        this.time = time;
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
        return this.time;
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
