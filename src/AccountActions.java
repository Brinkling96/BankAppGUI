public interface AccountActions {
    public boolean deposit(int amount, String currency, Bank bank);
    public boolean withdraw(int amount, String currency, Bank bank);
}
