import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DataKeeper {
    public static String USER_PATH = "./data/users/";
    public static String BANK_PATH = "./data/daily_reports/";
    public static void newUser(User user) {
        String uid = user.getUserID();
        String directoryName = USER_PATH.concat(uid);
        String fileName = "user_detail.txt";
        File directory = new File(directoryName);
        directory.mkdirs();
        File file = new File(directoryName + "/" + fileName);
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(user.toString());
            bw.close();
        }
        catch (IOException e){
            System.err.println("Directory could not be created");
        }
    }

    public static void newAccount(Account account) {
        System.out.println(account.getAccountID());
        String uid = account.getAccountID().substring(0,4);
        String aid = account.getAccountID().substring(4,10);
        String directoryName = USER_PATH.concat(uid);
        String fileName = "account_details.txt";
        File directory = new File(directoryName + "/" + aid);
        directory.mkdir();
        File file = new File(directoryName + "/" + fileName);
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(account.toString());
            bw.close();
        }
        catch (IOException e){
            System.err.println("Directory could not be created");
        }
    }

    public static void newTransaction(Transaction transaction) {
        String uid = transaction.getID().substring(0,4);
        String aid = transaction.getID().substring(4,10);
        String directoryName = USER_PATH + uid + "/" + aid + "/";
        String fileName = "transactions.txt";
        File file = new File(directoryName + "/" + fileName);
        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(transaction.toString());
        } catch (IOException e) {

        }
    }
}
