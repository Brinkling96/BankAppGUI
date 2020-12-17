/******************************************************************************
 * Class: DataKeeper.java
 * Author: Sarah Shahinpour, Sean Brady, Shuaike Zhou, 
 *******************************************************************************/

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

//Accesses files to keep track of all the users and transactions so that the data is persistent
public class DataKeeper {
    public static String USER_PATH = "./data/users/";
    public static String BANK_PATH = "./data/daily_reports/";

    public static ArrayList<String> readAllLines(String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Path filePath = Paths.get(path);
            return new ArrayList<>(Files.readAllLines(filePath, StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println("File could not be created");
        }
        return null;
    }

    public static void writeAllLines(String filepath, ArrayList<String> lines) {
        Path path = Paths.get(filepath);
        try {
            Files.write(path, lines, StandardCharsets.UTF_8);

        } catch (IOException e) {
            System.err.println("Could not write to file");
        }
    }

    public static void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (! Files.isSymbolicLink(f.toPath())) {
                    deleteDir(f);
                }
            }
        }
        file.delete();
    }

    public static void writeNewLineToFile(String directoryName, String fileName, String str) {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directoryName + "/" + fileName);
        try (FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            out.print(str);
        } catch (IOException e) {
            System.err.println("Could not write to file");
        }
    }

    public static void updateUser(User user, String action) {
        String uid = user.getUserID();
        String directoryName = USER_PATH.concat(uid);
        String fileName = "user_details.txt";

        // create a directory for every user if not exist
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String path = USER_PATH + fileName;
        ArrayList<String> lines = readAllLines(path);
        switch (action) {
            case "add":
                lines.add(user.toString().replace("\n", ""));
                break;
            case "remove":
                String removeLine = "";
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).split(",")[0].equals(user.getUserID())) {
                        removeLine = lines.get(i);
                        break;
                    }
                }
                lines.remove(removeLine);
                deleteDir(new File(directoryName));
                break;
            case "update":
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).split(",")[0].equals(user.getUserID())) {
                        lines.set(i, user.toString().replace("\n", ""));
                        break;
                    }
                }
                break;
        }
        writeAllLines(path, lines);
    }

    public static void updateAccount(Account account, String type) {
        String uid = account.getAccountID().substring(0, 4);
        String aid = account.getAccountID().substring(4, 10);
        String directoryName = USER_PATH.concat(uid);
        String fileName = "account_details.txt";
        String path = directoryName + "/" + fileName;
        ArrayList<String> lines = readAllLines(path);
        if (type.equals("account creation fee")) {
            lines.add(account.toString().replace("\n", ""));
            File directory = new File(directoryName + "/" + aid);
            directory.mkdir();
        } else if (type.equals("account removal fee")) {
            String removeLine = "";
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).split(",")[0].equals(account.getAccountID())) {
                    removeLine = lines.get(i);
                    break;
                }
            }
            lines.remove(removeLine);
            deleteDir(new File(directoryName + "/" + aid));
        } else {
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).split(",")[0].equals(account.getAccountID())) {
                    lines.set(i, account.toString().replace("\n", ""));
                    break;
                }
            }
        }
        writeAllLines(path, lines);
    }

    public static void updateStocks(Stock stock, String action) {
        String path = "./data/bank_stocks.txt";
        ArrayList<String> lines = readAllLines(path);
        switch (action) {
            case "add":
                lines.add(stock.toString().replace("\n", ""));
                break;
            case "remove":
                String removeLine = "";
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).split(",")[0].equals(stock.getName())) {
                        removeLine = lines.get(i);
                        break;
                    }
                }
                lines.remove(removeLine);
                break;
            case "update":
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).split(",")[0].equals(stock.getName())) {
                        lines.set(i, stock.toString().replace("\n", ""));
                        break;
                    }
                }
                break;
        }
        writeAllLines(path, lines);
    }

    public static void buySellStock(SecurityAccount account, Stock stock) {
        String uid = account.getAccountID().substring(0, 4);
        String aid = account.getAccountID().substring(4, 10);
        String path = USER_PATH + uid + "/" + aid + "/" + "stocks.txt";
        ArrayList<String> lines = readAllLines(path);
        boolean found = false;
        for (int i = 0; i < lines.size(); i++) {
            String[] stockInfo = lines.get(i).split(",");
            String name = stockInfo[0];
            if (name.equals(stock.getName())) {
                lines.set(i, stock.toString().replace("\n", ""));
                found = true;
            }
        }
        if (!found) {
            lines.add(stock.toString().replace("\n", ""));
        }
        writeAllLines(path, lines);
    }

    public static void updateDailyReports(Transaction transaction) {
        String transactionTime = transaction.getTime();
        String month = transactionTime.substring(0, 2);
        String date = transactionTime.substring(3, 5);
        String year = transactionTime.substring(6, 10);
        writeNewLineToFile(BANK_PATH + year + "/" + month, date, transaction.toString());
    }

    public static void newTransaction(Transaction transaction) {
        String uid = transaction.getID().substring(0, 4);
        String aid = transaction.getID().substring(4, 10);
        writeNewLineToFile(USER_PATH + uid + "/" + aid + "/", "transactions.txt", transaction.toString());
    }

    public static Bank initBank() {
        String profit = "";
        ArrayList<String> lines = readAllLines(USER_PATH + "user_details.txt");
        Bank bank = null;
        ArrayList<User> users = new ArrayList<User>();
        for (String line : lines) {
            String[] userInfo = line.split(",");
            
            if (userInfo.length > 5) {
                String userID = userInfo[0];
                String username = userInfo[1];
                String password = userInfo[2];
                String type = userInfo[3];
                String lastLogin = userInfo[4];
                if (type.equals("customer")) {
                    users.add(new CustomerUser(username, password, userID, lastLogin));
                } else {
                    profit = userInfo[5];
                    users.add(new Banker(username, password, userID, lastLogin));
                }
            }
        }
        bank = new Bank(users);
        for (User user : users) {
            if (user instanceof Banker) {
                ((Banker) user).setBank(bank);
                bank.setBanker((Banker) user);
                bank.setProfit(Integer.parseInt(profit));
            }
        }
        return bank;
    }

    public static ArrayList<Account> getAccountsFromUser(User user) {
        String uid = user.getUserID();
        ArrayList<String> lines = readAllLines(USER_PATH + uid + "/" + "account_details.txt");
        ArrayList<Account> accounts = new ArrayList<>();
        for (String line : lines) {
            String[] accountInfo = line.split(",");
            String aid = accountInfo[0];
            String type = aid.substring(aid.length()-2, aid.length());

            if (type.equals("ck")) {
                String balance = accountInfo[1];
                accounts.add(new CheckingAccount(aid, balance));
            } else if (type.equals("sv")) {
                String balance = accountInfo[1];
                accounts.add(new SavingsAccount(aid, balance));
            } else if (type.equals("ln")) {
                String originalValue = accountInfo[1];
                String principal = accountInfo[2];
                Collateral c = new Collateral(accountInfo[3], accountInfo[4], Integer.parseInt(accountInfo[5]));
                accounts.add(new LoanAccount(aid, originalValue, principal, c));
            } else if (type.equals("sc")) {
                String balance = accountInfo[1];
                ArrayList<Stock> stocks = getStocks(USER_PATH + uid + "/" + aid.replace(uid, "") + "/" + "stocks.txt");
                accounts.add(new SecurityAccount(aid,balance,stocks));
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
        String month = date.substring(0,2);
        String day = date.substring(2,4);
        String year = date.substring(4, 8);
        String path = BANK_PATH + year + "/" + month + "/" + day;
        return  DataKeeper.readTransactions(path);
    }

    public static ArrayList<Transaction> getTransactionsFromAccount(Account account) {
        String uid = account.getAccountID().substring(0,4);
        String aid = account.getAccountID().substring(4,10);
        String path = USER_PATH + uid + "/" + aid + "/" + "transactions.txt"; 
        return DataKeeper.readTransactions(path);
    }

    public static ArrayList<Transaction> readTransactions(String path) {
        ArrayList<String> lines = readAllLines(path);
        ArrayList<Transaction> transactions = new ArrayList<>();
        for (String line : lines) {
            String[] transaction = line.split(",");
            String tid = transaction[0];
            String type = transaction[1];
            String amount = transaction[2];
            String currency = transaction[3];
            String time = transaction[4];
            transactions.add(new Transaction(tid, type, currency, amount, time));
        }
        return transactions;
    }

    public static ArrayList<Stock> loadStocks() {
        String path = "./data/bank_stocks.txt";
        return getStocks(path);
    }

    public static ArrayList<Stock> getStocks(String path) {
        ArrayList<String> lines = readAllLines(path);
        ArrayList<Stock> stocks = new ArrayList<Stock>();
        for (String line : lines) {
            String[] stockString = line.split(",");
            if (stockString.length == 4) {
                String name = stockString[0];
                String value = stockString[1];
                String shares = stockString[2];
                String type = stockString[3];
                if (type.equals("nyse")) {
                    stocks.add(new NYSE_Stock(name, value, shares));
                }
            }
        }
        return stocks;
    }
}
