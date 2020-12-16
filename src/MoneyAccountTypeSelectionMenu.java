import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Dialog that allows the user to select which type of account they would like to create
public class MoneyAccountTypeSelectionMenu extends JDialog {

    protected static final String[] accountTypes = new String[]{"Checking","Savings","Loan","Security"};
    private Window window;
    private Bank bank;
    protected JLabel label = new JLabel("Select AccountType to create: ");
    protected JComboBox dropDownBox = new JComboBox(new DefaultComboBoxModel(accountTypes));
    protected JButton submitButton = new JButton("Submit");
    protected JButton cancelButton = new JButton("Cancel");
    protected CustomerUser user;
    protected Account account;

    public MoneyAccountTypeSelectionMenu(Window owner, CustomerUser user, Bank bank) {
        super(owner, Dialog.DEFAULT_MODALITY_TYPE);
        this.setLayout(new GridLayout(2,2));
        this.window = owner;
        this.bank = bank;

        this.user = user;

        this.add(label);
        this.add(dropDownBox);

        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitActionButton();
            }
        });

        this.add(submitButton);

        this.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelActionButton();
            }
        });

        this.add(cancelButton);

        this.pack();
        this.setVisible(true);
    }

    private void submitActionButton() {
        if(this.dropDownBox.getSelectedItem().toString() == MoneyAccountTypeSelectionMenu.accountTypes[0]){
            this.setVisible(false);
            AddAccountDialog d = new AddCheckingAccountDialog(this.window,this.user, this.bank);
            if(d.getAccount() != null){
                this.account = d.getAccount();
            }
        }
        else if(this.dropDownBox.getSelectedItem().toString()== MoneyAccountTypeSelectionMenu.accountTypes[1]){
            this.setVisible(false);
            AddAccountDialog d = new AddSavingsAccountDialog(this.window,this.user,this.bank);
            if(d.getAccount() != null){
                this.account = d.getAccount();
            }
        }

        else if(this.dropDownBox.getSelectedItem().toString()== MoneyAccountTypeSelectionMenu.accountTypes[2]){
            this.setVisible(false);
            AddAccountDialog d = new AddLoanAccountDialog(this.window,this.user,this.bank);
            if(d.getAccount() != null){
                this.account = d.getAccount();
            }
        }

        else if(this.dropDownBox.getSelectedItem().toString()== MoneyAccountTypeSelectionMenu.accountTypes[3]){
            this.setVisible(false);
            AddSecurityAccount d = new AddSecurityAccount(window,user,bank);
            if(d.getAccount() != null){
                this.account = d.getAccount();
            }
        }
        this.dispose();
    }

    private void cancelActionButton(){
        this.dispose();
    }
}
