import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Dashboard extends JPanel {

    protected Bank bank;

    protected Window window;
    protected User user;
    //protected transactions


    //Dashboard Generals
    protected JLabel usernameLabel;
    protected JPanel generalLabelsPanel;

    //DashBoardOptions
    protected JPanel generalActionsPanel;
    protected JButton logoutButton;
    protected JButton stockMarketButton;


    public Dashboard(Window window, User user, Bank bank) {
        super();
        this.user = user;
        this.bank = bank;
        this.window= window;
        window.setMinimumSize(new Dimension(1200, 800));
        LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layoutManager);
        /////////////////////////////////////////////////////////////////////////////
        //INFO PANEL SETUP

        this.usernameLabel = new JLabel(user.getUsername());
        this.generalLabelsPanel = new JPanel();

        this.generalLabelsPanel.add(usernameLabel);

        add(generalLabelsPanel);


        ///////////////////////////////////////////////////////////////////////
        //GENERAL ACTIONS BUTTONS PANEL

        this.generalActionsPanel = new JPanel();
        this.logoutButton = new JButton("Logout");

        this.logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        this.stockMarketButton = new JButton("View Stock Market Section");
        this.stockMarketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockMarketAction();
            }
        });
        this.generalActionsPanel.add(logoutButton);
        this.generalActionsPanel.add(stockMarketButton);

        add(Box.createVerticalBox());
        add(generalActionsPanel);

    }

    protected void logout() {
        this.window.setVisible(false);
        this.window.remove(this);
        this.window.add(new LoginScreen(window,bank));
        this.window.setVisible(true);
    }

    protected abstract void stockMarketAction();

    protected abstract void removeAccountButtonActionPerformed();
}