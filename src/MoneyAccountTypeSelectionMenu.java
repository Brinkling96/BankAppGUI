import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoneyAccountTypeSelectionMenu extends JDialog {

    protected static final String[] accountTypes = new String[]{"Checking","Savings"};
    private Window window;

    protected JLabel label = new JLabel("Select AccountType to create: ");

    protected JComboBox dropDownBox = new JComboBox(new DefaultComboBoxModel(accountTypes));

    protected JButton submitButton = new JButton("Submit");

    protected JButton cancelButton = new JButton("Cancel");


    protected CustomerUser user;

    protected Account account;

    public MoneyAccountTypeSelectionMenu(Window owner, CustomerUser user) {
        super(owner, Dialog.DEFAULT_MODALITY_TYPE);
        this.setLayout(new GridLayout(2,2));
        this.window = owner;

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
        if(this.dropDownBox.getSelectedItem().toString() == this.accountTypes[0]){
            this.setVisible(false);
            AddAccountDialog d = new AddCheckingAccountDialog(this.window,this.user);
            if(d.getAccount() != null){
                this.account = d.getAccount();
            }
        }
        else if(this.dropDownBox.getSelectedItem().toString()== this.accountTypes[1]){
            this.setVisible(false);
            AddAccountDialog d = new AddSavingsAccountDialog(this.window,this.user);
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
