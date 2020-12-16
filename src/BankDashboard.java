import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BankDashboard extends JPanel {

        protected Window window;

        protected Bank bank;

        protected User user;


        //Dashboard Generals

        protected JPanel generalLabelsPanel;

        //DashBoardOptions
        protected JPanel generalActionsPanel;
        protected JButton logoutButton;


        public BankDashboard(Window window, User user, Bank bank) {
            super();
            this.window = window;
            this.bank = bank;
            window.setMinimumSize(new Dimension(1200, 800));
            LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
            this.setLayout(layoutManager);
            /////////////////////////////////////////////////////////////////////////////
            //INFO PANEL SETUP

            this.generalLabelsPanel = new JPanel();

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

            this.generalActionsPanel.add(logoutButton);

            add(Box.createVerticalBox());
            add(generalActionsPanel);

        }

        protected void logout() {
            user.logout();
            this.window.setVisible(false);
            this.window.remove(this);
            this.window.add(new LoginScreen(window, bank));
            this.window.setVisible(true);
        }
    }