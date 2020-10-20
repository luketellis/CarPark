package carpark.gui;

import carpark.code.Application;
import carpark.code.Car;
import carpark.code.CarPark;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ParkCarGUI implements ActionListener {
    private ButtonGroup staffVisitorGroup;
    private JRadioButton staffTypeRadioBtn;
    private JRadioButton visitorTypeRadioBtn;

    private JComboBox parkingSlotBox;
    private JButton parkBtn, cancelBtn;
    private JLabel carOwnerNameLabel, carRegistrationLabel;
    private JTextField carOwnerNameFld, carRegistrationFld;

    private JFrame parkCarFrame;
    private JFrame mainMenuFrame;

    private CarPark carPark;

    public ParkCarGUI(CarPark carPark, JFrame mainMenuFrame) {
        this.carPark = carPark;
        this.mainMenuFrame = mainMenuFrame;

        buildFrame();
        makeMenuBar(parkCarFrame);
    }

    void buildFrame() {
        parkCarFrame = new JFrame("Park Car");
        parkCarFrame.setSize(500, 300);
        parkCarFrame.setLayout(null);


        parkBtn = new JButton("Create");

        parkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean isStaff = true;

                if (staffTypeRadioBtn.isSelected()) {
                } else if (visitorTypeRadioBtn.isSelected()) {
                    isStaff = false;
                } else {
                    JOptionPane.showMessageDialog(parkCarFrame, "Please select a parking slot type radio button");
                    return;
                }

                String potentialCarRegistration = carRegistrationFld.getText();
                if (!Application.isValidCarRegistration(potentialCarRegistration)) {
                    JOptionPane.showMessageDialog(parkCarFrame, "Car Registration is not of the required format");
                    return;
                }

                String carOwnerName = carOwnerNameFld.getText();
                if (carOwnerName.length() < 1) {
                    JOptionPane.showMessageDialog(parkCarFrame, "Car Owner Name Needs To Be Entered");
                    return;
                }

                if (parkingSlotBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(parkCarFrame, "Please select a parking slot id");
                    return;
                }

                String selectedParkingSlot = parkingSlotBox.getSelectedItem().toString();
                if (carPark.isCarWithRegistrationAlreadyParked(selectedParkingSlot)) {
                    JOptionPane.showMessageDialog(parkCarFrame, "A Car at '" + selectedParkingSlot + "'is already parked in the Car Park");
                    return;
                }

                carPark.parkCar(selectedParkingSlot, new Car(potentialCarRegistration, carOwnerName, isStaff));
                String message = "Parking Slot '" + selectedParkingSlot + "' is now occupied by Car with registration '" + potentialCarRegistration + "'";
                JOptionPane.showMessageDialog(parkCarFrame, message);

            }
        });

        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(true);
                parkCarFrame.dispose();
            }
        });

        //Setting Bounds of "buttons".
        parkBtn.setBounds(250, 200, 80, 30);
        cancelBtn.setBounds(380, 200, 80, 30);

        String[] parkingSlotIds = carPark.retrieveParkingSlotIdsByType("staff");
        parkingSlotBox = new JComboBox(parkingSlotIds);
        parkingSlotBox.setBounds(380, 80, 80, 30);

        // Initialization of object of "ButtonGroup" class.
        staffVisitorGroup = new ButtonGroup();

        // Initialization of object of " JLabel" class.
        JLabel parkingSlotTypeLabel = new JLabel("Staff/Visitor");


        // Initialization of object of "JRadioButton" class.
        staffTypeRadioBtn = new JRadioButton("Staff", true);
        staffTypeRadioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultComboBoxModel model = new DefaultComboBoxModel(carPark.retrieveParkingSlotIdsByType("staff"));
                parkingSlotBox.setModel(model);
            }
        });

        // Initialization of object of "JRadioButton" class.
        visitorTypeRadioBtn = new JRadioButton("Visitor");
        visitorTypeRadioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultComboBoxModel model = new DefaultComboBoxModel(carPark.retrieveParkingSlotIdsByType("visitor"));
                parkingSlotBox.setModel(model);
            }
        });


        // Setting Bounds of JLabel "L2".
        parkingSlotTypeLabel.setBounds(50, 10, 120, 50);

        // Setting Bounds of "jRadioButton2".
        staffTypeRadioBtn.setBounds(250, 10, 120, 50);

        // Setting Bounds of "jRadioButton4".
        visitorTypeRadioBtn.setBounds(380, 10, 80, 50);

        //Initialize label and set bounds
        carRegistrationLabel = new JLabel("Enter Car Registration of Parked Car");
        carRegistrationLabel.setBounds(50, 40, 250, 50);

        carRegistrationFld = new JTextField();
        carRegistrationFld.setBounds(50, 80, 80, 30);

        carOwnerNameLabel = new JLabel("Enter Car Owner's Name");
        carOwnerNameLabel.setBounds(50, 100, 250, 50);

        carOwnerNameFld = new JTextField();
        carOwnerNameFld.setBounds(50, 140, 80, 30);



        parkCarFrame.add(parkingSlotBox);

        parkCarFrame.add(carRegistrationLabel);
        parkCarFrame.add(carRegistrationFld);

        parkCarFrame.add(carOwnerNameLabel);
        parkCarFrame.add(carOwnerNameFld);


        // Adding "jButton" on JFrame.
        parkCarFrame.add(parkBtn);

        // Adding "jButton" on JFrame.
        parkCarFrame.add(cancelBtn);

        // Adding "jRadioButton2" on JFrame.
        parkCarFrame.add(staffTypeRadioBtn);

        // Adding "jRadioButton4" on JFrame.
        parkCarFrame.add(visitorTypeRadioBtn);

        // Adding JLabel "parkingSlotTypeLabel" on JFrame.
        parkCarFrame.add(parkingSlotTypeLabel);


        // Adding "jRadioButton1" and "jRadioButton3" in a Button Group "G2".
        staffVisitorGroup.add(staffTypeRadioBtn);
        staffVisitorGroup.add(visitorTypeRadioBtn);

        parkCarFrame.setVisible(true);
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
