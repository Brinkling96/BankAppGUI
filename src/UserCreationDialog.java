import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCreationDialog extends JDialog {

    protected Bank bank;

    protected FieldInputTextCombo usernamePanel;

    protected JPanel passwordPanel;
    protected JLabel passwordLabel;
    protected JPasswordField passwordField;



    protected JPanel buttonPanel = new JPanel();
    protected JButton submitButton = new JButton("Submit");

    protected JButton cancelButton = new JButton("Cancel");



    public UserCreationDialog(Frame owner, boolean modal, Bank bank) {
        super(owner, modal);
        this.bank = bank;

        this.setLayout(new GridLayout(3,1));

        this.usernamePanel = new FieldInputTextCombo("Enter new Username: ");
        passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField();
        this.passwordPanel = new JPanel(new GridLayout(1,2));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);


        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButtonAction(e);
            }
        });

        this.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonAction();
            }
        });
        this.buttonPanel.add(submitButton);
        this.buttonPanel.add(cancelButton);


        this.add(usernamePanel);
        this.add(passwordPanel);
        this.add(buttonPanel);

        pack();
        setVisible(true);

    }

    private void cancelButtonAction() {
        this.dispose();
    }

    protected void submitButtonAction(ActionEvent e) {
        StringBuilder warningsBuilder = new StringBuilder();
        String username = null;
        char[] password = new char[0];
        if(this.usernamePanel.textField.getText().isEmpty()){
            warningsBuilder.append("Username must be entered!\n");
        }
        else {
           username=  this.usernamePanel.textField.getText();
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
