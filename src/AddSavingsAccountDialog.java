import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddSavingsAccountDialog extends AddAccountDialog {

    public AddSavingsAccountDialog(Window owner, CustomerUser user, Bank bank) {
        super(owner, user, "Input Balance: ", bank);
        this.pack();
        this.setVisible(true);
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
                int result = JOptionPane.showConfirmDialog(this,"Creation fee of $" + bank.getCreationFee(),
                        "Fee",JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.OK_OPTION) {
                    this.account = new SavingsAccount(balance, user, bank);
                    user.addAccount(account, this.bank);
                }
                this.dispose();
            }
            else{
                returnString ="Balance must be over 100";
                JOptionPane.showMessageDialog(this,returnString,"Input Warning",JOptionPane.WARNING_MESSAGE);
            }
        }

    }

}
