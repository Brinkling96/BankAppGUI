import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoneyAccountTypeSelectionMenu extends JDialog {

    protected static final String[] accountTypes = new String[]{"Checking","Savings"};

    protected JLabel label = new JLabel("Select AccountType to create: ");

    protected JComboBox dropDownBox = new JComboBox(new DefaultComboBoxModel(accountTypes));

    protected JButton submitButton = new JButton("Submit");

    protected JButton cancelButton = new JButton("Cancel");


    protected CustomerUser user;

    protected Frame host;

    protected Account account;

    public MoneyAccountTypeSelectionMenu(Frame owner, CustomerUser user) {
        super(owner, true);
        this.setLayout(new GridLayout(2,2));

        this.user = user;
        this.host = owner;

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
        if(this.dropDownBox.getSelectedItem().toString() == this.accountTypes[0]){
            this.setVisible(false);
            AddAccountDialog d = new AddCheckingAccountDialog(this.host,this.user);
            if(d.getAccount() != null){
                this.account = d.getAccount();
            }
        }
        else if(this.dropDownBox.getSelectedItem().toString()== this.accountTypes[1]){
            this.setVisible(false);
            AddAccountDialog d = new AddSavingsAccountDialog(this.host,this.user);
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
