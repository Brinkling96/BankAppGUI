import javax.swing.*;

public class testingUserDash {
    public static void main(String[]  args) {
        JFrame frame = new JFrame("UserDashboard Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CustomerUser s = new CustomerUser("sdbrady","password".toCharArray());
        s.addAccount(new CheckingAccount(1000));

        frame.add(new UserDashboard(frame,s));
        frame.pack();
        frame.setVisible(true);
    }
}
