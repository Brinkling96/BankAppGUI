public class InputTest {
    public static void main (String[] args) {
        Bank bank = DataKeeper.initBank();
        System.out.println(bank.getNumUsers());
        CustomerUser user = (CustomerUser) bank.getUser("0051");
        user.loadAccounts(DataKeeper.loadAccounts(user));
        CheckingAccount account = (CheckingAccount) user.getAccount("00510000ck");
        for (Transaction t : DataKeeper.loadTransactions(account)) {
            System.out.print(t);
        }
    }
}
