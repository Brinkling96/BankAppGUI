import java.time.LocalDateTime;

public class SavingsAccount extends Account {

    public SavingsAccount(Integer balance, User user, int numAccounts, Bank bank) {

        super(balance, user, numAccounts, bank);
    }

    public SavingsAccount(String accountID, String balance) {
        super(accountID, balance);
    }

    public void accumulateInterest(){
        LocalDateTime now = this.user.getCurrentLogin();
        LocalDateTime lastLoginDate = this.user.getLastLogin();
        long days = Clock.getClock().daysElapsed(now, lastLoginDate);
        if(this.getBalance() >= this.getBank().getHighValueBenchmark()){
            int interestGained = (int)(this.getBalance()*Math.pow((1+(this.getBank().getInterestRate())), days));
            this.setBalance(this.getBalance() + interestGained);
            this.getBank().createTransaction(this, "interest payment", interestGained, "usd");
        }
    }
}
