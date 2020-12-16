/******************************************************************************
 * Class: BankInitializer.java
 * Author: Sarah Shahinpour, Sean Brady, Shuaike Zhou, 
 *******************************************************************************/

import javax.swing.*;
import java.util.ArrayList;

//Initializes all things necessary in the bank and displays a login screen
public class BankInitializer {
    public BankInitializer() {
    }

    public void initialize() {
        JFrame frame = BankingFrame.getInstance();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new LoginScreen(frame,createBank()));
        frame.pack();
        frame.setVisible(true);
    }

    // Initializes the bank from file if users already exist
    private Bank createBank() {
        Bank bank = DataKeeper.initBank();
        if (bank == null) {
            ArrayList<User> users = new ArrayList<User>();
            bank = new Bank(users);
        }
        bank.setUserNumber();
        return bank;
    }

}
