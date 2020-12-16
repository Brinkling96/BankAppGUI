/******************************************************************************
 * Class: AddLoanAccount.java
 * Author: Sarah Shahinpour, Sean Brady, Shuaike Zhou, 
 *******************************************************************************/


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
 * Extends AddAccountDialog.java. Allows user to take out loan by 
 * creating a loan account
 */
public class AddLoanAccountDialog extends AddAccountDialog{

    protected JPanel collateralPanel = new JPanel();
    protected FieldInputTextCombo cName = new FieldInputTextCombo("Collatoral Name:");
    protected FieldInputTextCombo cDescription = new FieldInputTextCombo("Description:");
    protected FieldInputTextCombo cValue = new FieldInputTextCombo("Value:");

    public AddLoanAccountDialog(Window owner, CustomerUser user, Bank bank) {
        super(owner, user, "Input Loan amount :", bank);

        collateralPanel.setLayout(new GridLayout(1,3));

        this.collateralPanel.add(cName);
        this.collateralPanel.add(cDescription);
        this.collateralPanel.add(cValue);
        this.host.add(collateralPanel,0);
        this.pack();
        this.setVisible(true);
    }

    @Override
    protected void okButtonAction(ActionEvent e) {
        String returnString = null;
        String balanceString = "";
        Integer balance = 0;
        Integer cVal =0;

        if(cName.textField.getText().isEmpty()) {
            returnString += "Input a Collatoral name!\n";
        }
        if(cDescription.textField.getText().isEmpty()) {
            cDescription.textField.setText("N/a");
        }

        if (balanceField.getText().isEmpty()) {
            returnString += "Input a Loan Amount!\n";
        } else {
            try {
                balance = Integer.parseInt(balanceField.getText());
                if(balance < this.bank.getCreationFee() || balance <100 ) {
                    returnString = "Loan must be greater than the creation fee of $" + this.bank.getCreationFee() +"\n";
                }
            } catch (NumberFormatException err) {
                returnString += "Loan Amount must be a number!\n";
            }
        }

        if(cValue.textField.getText().isEmpty()) {
            returnString += "Collatoral must have value\n";
        }
        else {
            try {
                cVal = Integer.parseInt(cValue.textField.getText());
                if (cVal < balance * LoanAccount.COLLATERAL_ORIGINAL_VALUE) {
                    returnString += "Need a higher Collateral Value for this Loan!\n";
                }
            } catch (NumberFormatException err) {
                returnString += "Collateral Value must be a number!\n";
            }
        }

        if (returnString != null) {
            JOptionPane.showMessageDialog(this, returnString, "Input Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            String name = cName.textField.getText();
            String description = cDescription.textField.getText();
            Collateral c = new Collateral(name, description, cVal);
            int result = JOptionPane.showConfirmDialog(this,"Creation fee of $" + bank.getCreationFee(),
                    "Fee",JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.OK_OPTION) {
                account = new LoanAccount(balance, user, bank, c);
                user.addAccount(account, this.bank);
            }
            this.dispose();
        }
    }
}
