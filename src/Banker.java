import java.util.ArrayList;

public class Banker extends User{
    private Bank bank;

    public Banker(String username, char[] password, int numUsers) {
        super(username, password, numUsers);
    }

    public Banker(String username, String password, String uid, String lastLogin) {
        super(username, password, uid, lastLogin);
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public ArrayList<User> viewCustomers(){
        return this.bank.users;
    }

    public User viewCustomer(String accountID){
        return this.bank.getUser(accountID);
    }

    //todo update stock prices


    //todo allow banker to configure bank
    public void configureBank(int creationFee, int closureFee, int transactionFee, double yenConversionRate, double euroConversionRate,
        double interestRate, int highValueBenchmark){
        this.bank.setCreationFee(creationFee);
        this.bank.setClosureFee(closureFee);
        this.bank.setTransactionFee(transactionFee);
        this.bank.setYenConversionRate(yenConversionRate);
        this.bank.setEuroConversionRate(euroConversionRate);
        this.bank.setInterestRate(interestRate);
        this.bank.setHighValueBenchmark(highValueBenchmark);
    }



}
