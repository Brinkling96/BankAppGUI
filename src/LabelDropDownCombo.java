import javax.swing.*;
import java.awt.*;

public class LabelDropDownCombo extends JPanel {
    protected JLabel label;
    protected JComboBox comboBox;

    public LabelDropDownCombo(String label, String[] comboBoxOptions) {
        super();
        super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.label = new JLabel(label);
        this.comboBox = new JComboBox(comboBoxOptions);
        add(this.label);
        add(Box.createHorizontalGlue());
        add(comboBox);
        add(Box.createHorizontalGlue());
    }
}
