//Type of account that accumulates interest
public class SavingsAccount extends Account {

    public SavingsAccount(Integer balance, User user, Bank bank) {

        super(balance, user, bank);
    }

    public SavingsAccount(String accountID, String balance) {
        super(accountID, balance);
    }

    public void accumulateInterest(int days, Bank bank){
        System.out.println(days);
        if(this.getBalance() >= bank.getHighValueBenchmark()){
            double interestGained = (Math.pow((1+(bank.getInterestRate())), days));
            if (interestGained > 1) {
                this.setBalance(this.getBalance() + (int) Math.ceil(interestGained));
                bank.createTransaction(this, "interest payment", (int) Math.ceil(interestGained), "usd");
            }
        }
    }
}
