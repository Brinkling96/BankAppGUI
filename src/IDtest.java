import java.util.ArrayList;

public class IDtest {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<User>();
        Bank bank = new Bank(users);
        char[] password = "123456".toCharArray();
        for (int i = 0; i < 100; i++) {
            users.add(new CustomerUser("user" + i, password));
        }
        Account c = new CheckingAccount(0,users.get(51));
        bank.createTransaction(c, "deposit", 150, "");
        Transaction t = bank.getTransactions().get(0);
        System.out.println(t.getID());
        
    }
}
