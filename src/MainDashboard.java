import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MainDashboard extends BankDashboard {
    protected User user;

    protected JLabel usernameLabel;

    protected JButton stockMarketButton;

    public MainDashboard(Window window, User user, Bank bank) {
        super(window, bank);
        this.user = user;

        ///generalLabel
        this.usernameLabel = new JLabel(user.getUsername());
        this.generalLabelsPanel.add(usernameLabel);

        //GeneralActions
        this.stockMarketButton = new JButton("View Stock Market Section");
        this.stockMarketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockMarketAction();
            }
        });
        this.generalActionsPanel.add(stockMarketButton);
    }

    protected abstract void stockMarketAction();

}
