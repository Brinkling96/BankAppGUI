import java.util.ArrayList;

public class IDtest {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<User>();
        Bank bank = new Bank(users);
        char[] password = "123456".toCharArray();
        for (int i = 0; i < 100; i++) {
            users.add(new CustomerUser("user" + i, password));
        }
        Account c = new CheckingAccount(0, users.get(51));
        for (int i = 0; i < 10; i++) {
            try {
                bank.createTransaction(c, "deposit", 150, "");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (Transaction t : bank.getTransactions()) {
            System.out.println(t.getID());
        }
        System.out.println(c.getBalance());
    }
}
