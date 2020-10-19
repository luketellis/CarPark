package carpark.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddParkingSlotGUI implements ActionListener {
    private JLabel parkingSlotTypeLabel;
    ButtonGroup parkingSlotTypeGroup;
    JRadioButton staffTypeRadioBtn;
    JRadioButton visitorTypeRadioBtn;

    private JButton createBtn, exitBtn;
    private JFrame frame;

    public AddParkingSlotGUI() {
        buildFrame2();
        //makeMenuBar(frame);
    }

    void buildFrame2()     {
        frame= new JFrame("Add New Parking Slot");
        frame.setSize(500,300);
        frame.setLayout(null);

        // Initialization of object of "JRadioButton" class.
        staffTypeRadioBtn = new JRadioButton();

        // Initialization of object of "JRadioButton" class.
        visitorTypeRadioBtn = new JRadioButton();

        // Initialization of object of "JButton" class.
        createBtn = new JButton("Create");

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedText = "Please select radio button";
                if (staffTypeRadioBtn.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "A new Parking Slot of type 'Staff' has been added");
                }

                else if (visitorTypeRadioBtn.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "A new Parking Slot of type 'Visitor' has been added");
                }

                else {
                    JOptionPane.showMessageDialog(frame, "Please select a radio button");
                }

                /*frame.dispose();
                new CarParkMenuGUI();*/
            }
        });

        // Initialization of object of "JButton" class.
        exitBtn = new JButton("Exit");

        // Initialization of object of "ButtonGroup" class.
        parkingSlotTypeGroup = new ButtonGroup();

        // Initialization of object of " JLabel" class.
        parkingSlotTypeLabel = new JLabel("Parking Slot Type");

        // setText(...) function is used to set text of radio button.
        // Setting text of "jRadioButton2".
        staffTypeRadioBtn.setText("Staff");

        // Setting text of "jRadioButton4".
        visitorTypeRadioBtn.setText("Visitor");

        // Setting Bounds of JLabel "L2".
        parkingSlotTypeLabel.setBounds(50, 50, 120, 50);

        // Setting Bounds of "jRadioButton2".
        staffTypeRadioBtn.setBounds(250, 30, 120, 50);

        // Setting Bounds of "jRadioButton4".
        visitorTypeRadioBtn.setBounds(380, 30, 80, 50);

        // Setting Bounds of "jButton".
        createBtn.setBounds(250, 90, 80, 30);

        exitBtn.setBounds(380, 90, 80, 30);

        // Adding "jButton" on JFrame.
        frame.add(createBtn);

        // Adding "jButton" on JFrame.
        frame.add(exitBtn);

        // Adding "jRadioButton2" on JFrame.
        frame.add(staffTypeRadioBtn);

        // Adding "jRadioButton4" on JFrame.
        frame.add(visitorTypeRadioBtn);

        // Adding JLabel "parkingSlotTypeLabel" on JFrame.
        frame.add(parkingSlotTypeLabel);


        // Adding "jRadioButton1" and "jRadioButton3" in a Button Group "G2".
        parkingSlotTypeGroup.add(staffTypeRadioBtn);
        parkingSlotTypeGroup.add(visitorTypeRadioBtn);

        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Quit")) {

        }
    }

    void makeMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem quitItem = new JMenuItem("Quit");
        fileMenu.add(quitItem);
        quitItem.addActionListener(this);
    }

    public static void main(String[] args) {
        new AddParkingSlotGUI();
    }
}
