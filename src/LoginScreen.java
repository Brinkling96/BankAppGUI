import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JPanel {

    protected JFrame host;
    protected Bank bank;

    protected FieldInputTextCombo usernamePanel;

    protected JPanel passwordPanel;
    protected JLabel passwordLabel;
    protected JPasswordField passwordField;

    protected JPanel buttonPanel;
    protected JButton submitButton;
    protected JButton newUserButton;
    protected JButton cancelButton;

    public LoginScreen(JFrame host, Bank bank) {
        super();
        this.host = host;
        this.bank = bank;
        this.setLayout(new GridLayout(3,1));
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
        host.dispose();
    }

    private void newUserButtonAction() {
        UserCreationDialog d = new UserCreationDialog(host,true,bank);
    }

    private void submitButtonAction() {
        User user = bank.getUser( usernamePanel.getTextInputted() ,passwordField.getPassword());

        if(user != null) {
            this.host.setVisible(false);
            this.host.remove(this);
            if(user instanceof CustomerUser) {
                this.host.add(new UserDashboard(host,(CustomerUser) user));
            }
            else if(user instanceof Banker){
                this.host.add(new BankerDashboard(host,(Banker) user,bank));
            }
            this.host.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(host,"User not found","Login Error",JOptionPane.ERROR_MESSAGE);
        }
    }

}
