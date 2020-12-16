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
        // TODO: Check how many days has passed since last login date
        
        if(this.getBalance() >= this.getBank().getHighValueBenchmark()){
            this.setBalance((int)(this.getBalance()*(1 + this.getBank().getInterestRate())));
        }
    }
}
