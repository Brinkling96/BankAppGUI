import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataKeeper {
    public static String USER_PATH = "./data/users/";
    public static String BANK_PATH = "./data/daily_reports/";

    public static void updateUser(User user, String action) {
        String uid = user.getUserID();
        String directoryName = USER_PATH.concat(uid);
        String fileName = "user_details.txt";

        // create a directory for every user if not exist
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            File userDetailFile = new File(USER_PATH + "user_details.txt");
            if (!userDetailFile.exists()) {
                userDetailFile.createNewFile();
            }
            Path filePath = Paths.get(USER_PATH + fileName);
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(filePath, StandardCharsets.UTF_8));
            switch (action) {
                case "add":
                    lines.add(user.toString());
                    break;
                case "remove":
                    int index = 0;
                    for (int i = 0; i < lines.size(); i++) {
                        if (lines.get(i).split(",")[0].equals(user.getUserID())) {
                            index = i;
                            break;
                        }
                    }
                    lines.remove(index);
                    break;
            }
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("File not found");
        }
    }

    public static void updateAccount(Account account, String type) {
        String uid = account.getAccountID().substring(0, 4);
        String aid = account.getAccountID().substring(4, 10);
        String directoryName = USER_PATH.concat(uid);

        try {
            String fileName = "account_details.txt";
            File file = new File(directoryName + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            Path filePath = Paths.get(directoryName + "/" + fileName);
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(filePath, StandardCharsets.UTF_8));
            if (type.equals("add")) {
                lines.add(account.toString().replace("\n", ""));
                File directory = new File(directoryName + "/" + aid);
                directory.mkdir();
            } else if (type.equals("remove")) {
                int index = 0;
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).split(",")[0].equals(account.getAccountID())) {
                        index = i;
                        break;
                    }
                    lines.remove(index);
                }
            } else if (type.equals("deposit") || type.equals("withdraw")) {
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).split(",")[0].equals(account.getAccountID())) {
                        lines.set(i, account.toString().replace("\n", ""));
                        break;
                    }
                }
            }
            
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Unable to update account");
        }
    }

    public static void updateDailyReports(Transaction transaction) {
        String transactionTime = transaction.getTime();
        String month = transactionTime.substring(0, 2);
        String date = transactionTime.substring(3, 5);
        String year = transactionTime.substring(6, 10);

        String directoryName = BANK_PATH + year + "/" + month;
        String fileName = date;
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directoryName + "/" + fileName);

        try (FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            
            out.print(transaction.toString());
        } catch (IOException e) {
            System.err.println("Transaction could not be recorded");
        }
    }

    public static void newTransaction(Transaction transaction) {
        System.out.println(transaction);
        String uid = transaction.getID().substring(0, 4);
        String aid = transaction.getID().substring(4, 10);
        String directoryName = USER_PATH + uid + "/" + aid + "/";
        File accDir = new File(directoryName);
        if (!accDir.exists()) {
            accDir.mkdirs();
        }
        String fileName = "transactions.txt";
        File file = new File(directoryName + "/" + fileName);
        try (FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);) {
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
                    if (userInfo.length == 4) {
                        String userID = userInfo[0];
                        String username = userInfo[1];
                        String password = userInfo[2];
                        String type = userInfo[3];
                        if (type.equals("customer")) {
                            users.add(new CustomerUser(username, password, userID));
                        } else {
                            users.add(new Banker(username, password, userID));
                        }
                    }
                }
                bank = new Bank(users);
                for (User user : users) {
                    if (user instanceof Banker) {
                        ((Banker) user).setBank(bank);
                    }
                }
            } catch (IOException e) {
                System.err.println("Bank file not found");
            }
        }
        return bank;
    }

    public static ArrayList<Account> getAccountsFromUser(User user) {
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
                System.err.println("Account file not found");
            }
        }
        return accounts;
    }

    /**
     * Date in the format MM-dd-yyyy
     * @param date
     * @return
     */
    public static ArrayList<Transaction> getDailyTransactions(String date) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        String[] d = date.split("-");
        String month = d[0];
        String day = d[1];
        String year = d[2];
        File file = new File(BANK_PATH + year + "/" + month + "/" + day);
        if (file.exists()) {
            transactions = DataKeeper.readTransactions(file);
        }
        return transactions;
    }

    public static ArrayList<Transaction> getTransactionsFromAccount(Account account) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        String uid = account.getAccountID().substring(0,4);
        String aid = account.getAccountID().substring(4,10);
        File file = new File(USER_PATH + uid + "/" + aid + "/" + "transactions.txt");
        if (file.exists()) {
            transactions = DataKeeper.readTransactions(file);
        }
        return transactions;
    }

    public static ArrayList<Transaction> readTransactions(File file) {
        ArrayList<Transaction> transactions = new ArrayList<>();
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
            System.err.println("Could not read transactions");
        }
        return transactions;
    }
}
