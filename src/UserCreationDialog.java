import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserCreationDialog extends InputIntMenu {

    protected Bank bank;

    protected JPanel passwordPanel;
    protected JLabel passwordLabel;
    protected JPasswordField passwordField;

    public UserCreationDialog(Frame owner, boolean modal, Bank bank) {
        super(owner, modal, "New User Account", "Username: ");
        this.bank = bank;

        passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField();
        this.passwordPanel = new JPanel(new GridLayout(1,2));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        this.add(passwordPanel);

        pack();
        setVisible(true);

    }

    @Override
    protected void submitButtonAction(ActionEvent e) {
        StringBuilder warningsBuilder = new StringBuilder();
        String username = null;
        char[] password = new char[0];
        if(this.intField.getText().isEmpty()){
            warningsBuilder.append("Username must be entered!\n");
        }
        else {
           username=  this.intField.getText();
        }
        if(this.passwordField.getPassword().length ==0){
            warningsBuilder.append("Password must be entered\n");
        }
        else{
            password= this.passwordField.getPassword();
        }
        String warnings = warningsBuilder.toString();
        if(warnings.isEmpty()){
            bank.addUser(new BankUser(username,password));
            JOptionPane.showMessageDialog(this,"New User Created!","Success",JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(this,"Errors: " + warnings,"ERROR!",JOptionPane.ERROR_MESSAGE);
        }
    }
}
