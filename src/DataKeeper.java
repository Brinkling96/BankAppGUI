import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataKeeper {
    public static String USER_PATH = "./data/users/";
    public static String BANK_PATH = "./data/daily_reports/";
    public static void newUser(User user) {
        String uid = user.getUserID();
        String directoryName = USER_PATH.concat(uid);
        String fileName = "user_details.txt";

        // create a directory for every user
        File directory = new File(directoryName);
        directory.mkdirs();
        File file = new File(USER_PATH + fileName);
        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.print(user.toString());
        } catch (IOException e) {
            System.err.println("User could not be recorded");
        }
    }

    public static void newAccount(Account account) {
        String uid = account.getAccountID().substring(0,4);
        String aid = account.getAccountID().substring(4,10);
        String directoryName = USER_PATH.concat(uid);
        String fileName = "account_details.txt";
        File directory = new File(directoryName + "/" + aid);
        directory.mkdir();
        File file = new File(directoryName + "/" + fileName);
        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.print(account.toString());
        } catch (IOException e) {
            System.err.println("Transaction could not be created");
        }
    }


    public static void updateAccount(Account account) {
        String uid = account.getAccountID().substring(0,4);
        String directoryName = USER_PATH.concat(uid);
        String fileName = "account_details.txt";
        Path filePath = Paths.get(directoryName + "/" + fileName);
        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(filePath, StandardCharsets.UTF_8));
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).split(",")[0].equals(account.getAccountID())) {
                    lines.set(i, account.toString().replace("\n", ""));
                    break;
                }
            }

            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e ) {
            System.out.println("Unable to update account");
        }
    }

    public static void updateDailyReports(Transaction transaction) {
        String transactionTime = transaction.getTime();
        String month = transactionTime.substring(0,2);
        String date = transactionTime.substring(3,5);
        String year = transactionTime.substring(6,10);

        String directoryName = BANK_PATH + year + "/" + month;
        String fileName = date;
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directoryName + "/" + fileName);
        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.print(transaction.toString());
        } catch (IOException e) {
            System.err.println("Transaction could not be recorded");
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
            out.print(transaction.toString());
        } catch (IOException e) {
            System.err.println("Transaction could not be created");
        }
    }

    public static Bank initBank() {
        File file = new File(USER_PATH + "user_details.txt");
        Bank bank = null;
        if (file.exists()) {
            ArrayList<User> users = new ArrayList<User>();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] userInfo = line.split(",");
                    String userID = userInfo[0];
                    String username = userInfo[1];
                    String password = userInfo[2];
                    String status = userInfo[3];
                    users.add(new CustomerUser(username, password, userID, status));
                }
                bank = new Bank(users);
            } catch (IOException e) {
                System.err.println("File not found");
            }
        }
        return bank;
    }

    public static ArrayList<Account> loadAccounts(User user) {
        String uid = user.getUserID();
        ArrayList<Account> accounts = new ArrayList<>();
        File file = new File(USER_PATH + uid + "/" + "account_details.txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] accountInfo = line.split(",");
                    String aid = accountInfo[0];
                    String balance = accountInfo[1];
                    String type = aid.substring(aid.length()-2, aid.length());
                    if (type.equals("ck"))
                        accounts.add(new CheckingAccount(aid, balance));
                    else if (type.equals("sv"))
                        accounts.add(new SavingsAccount(aid, balance));

                }
            } catch (IOException e) {
                System.err.println("File not found");
            }
        }
        return accounts;
    }

    public static ArrayList<Transaction> loadTransactions(Account account) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        String uid = account.getAccountID().substring(0,4);
        String aid = account.getAccountID().substring(4,10);
        File file = new File(USER_PATH + uid + "/" + aid + "/" + "transactions.txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] transaction = line.split(",");
                    String tid = transaction[0];
                    String type = transaction[1];
                    String amount = transaction[2];
                    String currency = transaction[3];
                    String time = transaction[4];
                    transactions.add(new Transaction(tid, type, currency, amount, time));
                }
            } catch (IOException e) {
                System.err.println("File not found");
            }
        }
        return transactions;
    }
}
