import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddLoanAccountDialog extends AddAccountDialog{

    protected JPanel collatoralPanel= new JPanel();

    protected FieldInputTextCombo cName = new FieldInputTextCombo("Collatoral Name:");
    protected FieldInputTextCombo cDescription = new FieldInputTextCombo("Description:");
    protected FieldInputTextCombo cValue = new FieldInputTextCombo("Value:");



    public AddLoanAccountDialog(Window owner, CustomerUser user, Bank bank) {
        super(owner, user, "Input Loan amount :", bank);


        collatoralPanel.setLayout(new GridLayout(1,3));

        this.collatoralPanel.add(cName);
        this.collatoralPanel.add(cDescription);
        this.collatoralPanel.add(cValue);

        this.host.add(collatoralPanel,0);
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
                if (cVal < balance * LoanAccount.COLLATERAL_ORIGINIAL_VALUE) {
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
            account = new LoanAccount(balance,user,bank,c);
            user.addAccount(account, this.bank);
            this.dispose();
        }
    }
}