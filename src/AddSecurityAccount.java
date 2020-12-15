import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddSecurityAccount extends AddAccountDialog{


    public AddSecurityAccount(Window owner, CustomerUser user, Bank bank) {
        super(owner, user,"Transfer: ", bank);
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        this.getContentPane().add(new JLabel("NUYLL"),0);

    }

    @Override
    protected void okButtonAction(ActionEvent e) {
        //todo
    }
}
