import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddAccountDialog extends JDialog{
    protected CustomerUser user;

    protected JLabel balanceLabel;
    protected JTextField balanceField;


    protected JButton okButton;
    protected JButton cancelButton;

    public AddAccountDialog(Frame owner, CustomerUser user) {
        super(owner, true);
        this.user = user;
        setLocationRelativeTo(owner);
        initComponents();
    }

    @SuppressWarnings("unchecked")

    private void initComponents(){
        balanceLabel = new JLabel("Input Balance");
        balanceField = new JTextField();
        okButton = new JButton("OK");
        cancelButton = new JButton("CANCEL");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Add Account Menu");

        getContentPane().setLayout(new GridLayout(3,2,5,5));

        getContentPane().add(balanceLabel);
        getContentPane().add(balanceField);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonAction(e);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonAction(e);
            }
        });


        getContentPane().add(okButton);
        getContentPane().add(cancelButton);


        pack();
        setVisible(true);
    }

    private void cancelButtonAction(ActionEvent e){
        this.dispose();
    }

    protected abstract void okButtonAction(ActionEvent e);


}
