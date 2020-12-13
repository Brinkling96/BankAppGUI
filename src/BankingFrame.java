import javax.swing.*;

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