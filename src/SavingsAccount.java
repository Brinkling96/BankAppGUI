public class SavingsAccount extends Account{

    public SavingsAccount(Integer balance, User user, int numAccounts) {

        super(balance, user, numAccounts);
    }

    public SavingsAccount(String accountID, String balance) {
        super(accountID, balance);
    }

    public void accumulateInterest(Bank bank){
        if(this.getBalance() >= bank.getHighValueBenchmark()){
            this.setBalance((int)(this.getBalance()*(1 + bank.getInterestRate())));
        }
    }
}
