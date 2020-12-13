public class Transaction {
    private final Account account;
    private final String transactionType;
    private final int amount;
    private final String currency;
    public Transaction(Account account, String transactionType,
                       int amount, String curr) {
        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
        this.currency = curr;
    }

    public boolean process() {
        boolean success = false;

        // TODO

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

    public String toString() {
        return null;
    }
}
