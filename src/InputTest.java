public class InputTest {
    public static void main (String[] args) {
        Bank bank = DataKeeper.initBank();
        System.out.println(bank.getUserNumber());
        CustomerUser user = (CustomerUser) bank.getUser("0051");
        user.loadAccounts(DataKeeper.getAccountsFromUser(user));
        CheckingAccount account = (CheckingAccount) user.getAccount("00510000ck");
        for (Transaction t : DataKeeper.getTransactionsFromAccount(account)) {
            System.out.print(t);
        }
    }
}
