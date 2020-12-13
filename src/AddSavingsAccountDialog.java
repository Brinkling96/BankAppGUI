import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddSavingsAccountDialog extends AddAccountDialog {

    public AddSavingsAccountDialog(Frame owner, CustomerUser user) {
        super(owner, user);
    }

    @Override
    protected void okButtonAction(ActionEvent e) {
        String returnString  = null;
        String balanceString="";
        Integer balance = 0;

        if(balanceField.getText().isEmpty()) {
            returnString = "Input a Balance!";
        }
        else {
            try {
                balance = Integer.parseInt(balanceField.getText());
            }catch (NumberFormatException err){
                returnString = "Balance must be a number!";
            }
        }

        if(returnString != null){
            JOptionPane.showMessageDialog(this, returnString,"Input Warning",JOptionPane.WARNING_MESSAGE);
        }
        else{
            if(balance >100){
                this.account = new SavingsAccount(balance);
                user.addAccount(account);
                this.dispose();
            }
            else{
                returnString ="Balance must be over 100";
                JOptionPane.showMessageDialog(this,returnString,"Input Warning",JOptionPane.WARNING_MESSAGE);
            }
        }

    }

}
