package carpark.gui;

import carpark.code.CarPark;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CreateParkingSlotGUI implements ActionListener {
    private JLabel parkingSlotTypeLabel;
    ButtonGroup parkingSlotTypeGroup;
    JRadioButton staffTypeRadioBtn;
    JRadioButton visitorTypeRadioBtn;

    CarPark carPark;

    private JButton createBtn, cancelBtn;
    private JFrame createParkingSlotFrame;
    private JFrame mainMenuFrame;

    public CreateParkingSlotGUI(CarPark carPark, JFrame mainMenuFrame) {
        this.carPark = carPark;
        this.mainMenuFrame = mainMenuFrame;

        buildFrame();
        makeMenuBar(createParkingSlotFrame);
    }

    void buildFrame()     {
        createParkingSlotFrame = new JFrame("Add New Parking Slot");
        createParkingSlotFrame.setSize(500,300);
        createParkingSlotFrame.setLayout(null);

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
                    carPark.addParkingSlotsByNumberAndType(1, "staff");

                    JOptionPane.showMessageDialog(createParkingSlotFrame, "A new Parking Slot of type 'Staff' has been added");
                }

                else if (visitorTypeRadioBtn.isSelected()) {
                    carPark.addParkingSlotsByNumberAndType(1, "visitor");
                    JOptionPane.showMessageDialog(createParkingSlotFrame, "A new Parking Slot of type 'Visitor' has been added");
                }

                else {
                    JOptionPane.showMessageDialog(createParkingSlotFrame, "Please select a radio button");
                }

                /*frame.dispose();
                new CarParkMenuGUI();*/
            }
        });

        // Initialization of object of "JButton" class.
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frame, Application.isStringNumber(visitorsParksFld.getText()));
                mainMenuFrame.setVisible(true);
                createParkingSlotFrame.dispose();
            }
        });

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
        parkingSlotTypeLabel.setBounds(30, 30, 120, 50);

        // Setting Bounds of "jRadioButton2".
        staffTypeRadioBtn.setBounds(250, 30, 120, 50);

        // Setting Bounds of "jRadioButton4".
        visitorTypeRadioBtn.setBounds(380, 30, 80, 50);

        // Setting Bounds of "jButton".
        createBtn.setBounds(250, 90, 80, 30);

        cancelBtn.setBounds(380, 90, 80, 30);

        // Adding "jButton" on JFrame.
        createParkingSlotFrame.add(createBtn);

        // Adding "jButton" on JFrame.
        createParkingSlotFrame.add(cancelBtn);

        // Adding "jRadioButton2" on JFrame.
        createParkingSlotFrame.add(staffTypeRadioBtn);

        // Adding "jRadioButton4" on JFrame.
        createParkingSlotFrame.add(visitorTypeRadioBtn);

        // Adding JLabel "parkingSlotTypeLabel" on JFrame.
        createParkingSlotFrame.add(parkingSlotTypeLabel);


        // Adding "jRadioButton1" and "jRadioButton3" in a Button Group "G2".
        parkingSlotTypeGroup.add(staffTypeRadioBtn);
        parkingSlotTypeGroup.add(visitorTypeRadioBtn);

        createParkingSlotFrame.setVisible(true);
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
}
