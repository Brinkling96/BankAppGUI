public class SavingsAccount extends Account{

    public SavingsAccount(Integer balance, User user) {
        super(balance, user);
    }

    public void accumulateInterest(Bank bank){
        if(this.getBalance() >= bank.getHighValueBenchmark()){
            this.setBalance((int)(this.getBalance()*(1 + bank.getInterestRate())));
        }
    }
}
