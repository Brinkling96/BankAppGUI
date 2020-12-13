import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class BankTimeSetter extends JDialog {

    private LabelDropDownCombo month, day, year, hour, minute,second;
    private JPanel comboBoxes;

    private JButton submitButton, cancelButton;
    private JPanel buttonPanel;

    public BankTimeSetter(Frame owner, boolean modal) {
        super(owner, modal);
        setTitle("Set time");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(2, 1));
        this.setMinimumSize(new Dimension(900,200));
        initComponents();
        this.setLocationRelativeTo(owner);
        setVisible(true);
    }

    private void initComponents() {
        month = new LabelDropDownCombo("Month: ", new String[]{"Jan","Feb","March","April","May","June","July","August","September","October","November","December"});
        day = new LabelDropDownCombo("Day: ", comboBoxIntBuilder(1,31,1));
        year = new LabelDropDownCombo("Year: ",comboBoxIntBuilder(2010,2030,1));
        hour = new LabelDropDownCombo("Hour: ", comboBoxIntBuilder(0,23,1));
        minute = new LabelDropDownCombo("Minute :", comboBoxIntBuilder(0,59,5));
        second = new LabelDropDownCombo("Seconds: ", comboBoxIntBuilder(0,59,5));
        comboBoxes = new JPanel();
        comboBoxes.setLayout(new BoxLayout(comboBoxes, BoxLayout.X_AXIS));

        comboBoxes.add(month);
        comboBoxes.add(Box.createHorizontalGlue());
        comboBoxes.add(day);
        comboBoxes.add(Box.createHorizontalGlue());
        comboBoxes.add(year);
        comboBoxes.add(Box.createHorizontalGlue());
        comboBoxes.add(hour);
        comboBoxes.add(Box.createHorizontalGlue());
        comboBoxes.add(minute);
        comboBoxes.add(Box.createHorizontalGlue());
        comboBoxes.add(second);
        add(comboBoxes);

        buttonPanel= new JPanel();

        submitButton= new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButtonAction(e);
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonAction(e);
            }
        });
        buttonPanel.add(submitButton);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(cancelButton);
        add(buttonPanel);




    }

    private void cancelButtonAction(ActionEvent e) {
        this.dispose();
    }

    private void submitButtonAction(ActionEvent e) {
        //todo
    }

    private String[] comboBoxIntBuilder(Integer start, Integer end, int increments){
        String[] returnlist= new String[((end-start)/increments)+1];

        for(int i=0; start<=end; i++){
            returnlist[i] =start.toString();
            start += increments;
        }
        return returnlist;
    }
}
