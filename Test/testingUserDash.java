import javax.swing.*;

public class testingUserDash {
    public static void main(String[]  args) {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BankUser s = new BankUser("sdbrady","password".toCharArray());
        s.addAccount(new CheckingAccount(1000));

        frame.add(new UserDashboard(frame,s));
        frame.pack();
        frame.setVisible(true);
    }
}
