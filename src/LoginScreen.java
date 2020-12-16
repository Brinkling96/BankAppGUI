import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JPanel {

    protected Bank bank;

    protected Window window;

    protected FieldInputTextCombo usernamePanel;

    protected JPanel passwordPanel;
    protected JLabel passwordLabel;
    protected JPasswordField passwordField;

    protected JPanel buttonPanel;
    protected JButton submitButton;
    protected JButton newUserButton;
    protected JButton cancelButton;

    public LoginScreen(Window window, Bank bank) {
        super();
        this.bank = bank;
        this.window = window;
        this.setLayout(new GridLayout(3,1));
        Dimension xy = new Dimension(800,100);
        this.window.setMinimumSize(xy);
        this.window.setSize(xy);
        this.usernamePanel = new FieldInputTextCombo("Username: ");

        this.passwordPanel = new JPanel(new GridLayout(1,2));
        this.passwordLabel = new JLabel("Password: ");
        this.passwordField = new JPasswordField();

        this.passwordPanel.add(passwordLabel);
        this.passwordPanel.add(passwordField);

        this.buttonPanel = new JPanel(new GridLayout(1,3));
        this.submitButton = new JButton("Submit");
        this.newUserButton = new JButton("New User? Create an Account here!");
        this.cancelButton = new JButton("Cancel");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButtonAction();
            }
        });

        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newUserButtonAction();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonAction();
            }
        });
        this.buttonPanel.add(submitButton);
        this.buttonPanel.add(newUserButton);
        this.buttonPanel.add(cancelButton);


        this.add(usernamePanel);
        this.add(passwordPanel);
        this.add(buttonPanel);


    }

    private void cancelButtonAction() {
        window.dispose();
    }

    private void newUserButtonAction() {
        UserCreationDialog d = new UserCreationDialog(window,bank);
    }

    private void submitButtonAction() {
        User user = bank.getUser( usernamePanel.getTextInputted() ,passwordField.getPassword());
        

        if(user != null) {
            this.window.setVisible(false);
            this.window.remove(this);
            if(user instanceof CustomerUser) {
                ((CustomerUser) user).loadAccounts(DataKeeper.getAccountsFromUser(user));
                //update the savings accounts and loans with interest here
                CustomerUser customerUser = (CustomerUser) user;
                long days = Clock.getClock().daysElapsed(customerUser.getCurrentLogin(), customerUser.getLastLogin());
                if(customerUser.getAccounts() != null){
                    for(Account account: customerUser.getAccounts()) {
                        if (account instanceof SavingsAccount && account.getBalance() >= bank.getHighValueBenchmark()) {
                            ((SavingsAccount) account).accumulateInterest((int)days, bank);
                        }
                    }
                }

                // if(customerUser.getLoans() != null) {
                //     for(Loan loan: customerUser.getLoans()){
                //         //todo loan doesn't have an account associated with it, so no transaction for now
                //         loan.chargeInterest((int)days);
                //         //createTransaction(account, "interest payment", interestGained, "usd")
                //     }
                // }
                this.window.add(new UserDashboard(window,(CustomerUser) user,bank));
            }
            else if(user instanceof Banker){
                this.window.add(new BankerDashboard(window,(Banker) user,bank));
            }
            this.window.pack();
            this.window.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(window,"User not found","Login Error",JOptionPane.ERROR_MESSAGE);
        }
    }

}
