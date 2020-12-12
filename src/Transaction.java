public class Transaction {
    private final Account source, target;
    private final int amount;
    private final String currency;
    public Transaction(Account source, Account target, int amount, String curr) {
        this.source = source;
        this.target = target;
        this.amount = amount;
        this.currency = curr;
    }

    public Account getSource() {
        return this.source;
    }

    public Account getTarget() {
        return this.target;
    }

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
