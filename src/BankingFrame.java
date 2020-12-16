/******************************************************************************
 * Class: BankingFrame.java
 * Author: Sarah Shahinpour, Sean Brady, Shuaike Zhou, 
 *******************************************************************************/

import javax.swing.*;

//Helps display the bank to the user
public class BankingFrame extends JFrame{

    private static BankingFrame host;

    private BankingFrame() {
       super("Banking app gui");
       super.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static JFrame getInstance(){
        if (host == null){
            host = new BankingFrame();
        }
        return host;
    }
}
