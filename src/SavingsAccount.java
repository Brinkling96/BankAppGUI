import java.time.LocalDateTime;

public class SavingsAccount extends Account {

    public SavingsAccount(Integer balance, User user, int numAccounts, Bank bank) {

        super(balance, user, numAccounts, bank);
    }

    public SavingsAccount(String accountID, String balance) {
        super(accountID, balance);
    }

    public void accumulateInterest(int days, Bank bank){
        if(this.getBalance() >= bank.getHighValueBenchmark()){
            int interestGained = (int)(this.getBalance()*Math.pow((1+(bank.getInterestRate())), days));
            this.setBalance(this.getBalance() + interestGained);
            bank.createTransaction(this, "interest payment", interestGained, "usd");
        }
    }
}
