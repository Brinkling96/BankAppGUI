public class SavingsAccount extends Account{

    public SavingsAccount(Integer balance, User user, int numAccounts, Bank bank) {

        super(balance, user, numAccounts, bank);
    }

    public SavingsAccount(String accountID, String balance) {
        super(accountID, balance);
    }

    public void accumulateInterest(){
        if(this.getBalance() >= this.getBank().getHighValueBenchmark()){
            this.setBalance((int)(this.getBalance()*(1 + this.getBank().getInterestRate())));
        }
    }
}
