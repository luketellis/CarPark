package carpark.gui;

import carpark.code.Application;
import carpark.code.Car;
import carpark.code.CarPark;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ParkCarGUI extends FrameSkeleton implements ActionListener {
    private ButtonGroup staffVisitorGroup;
    private JRadioButton staffTypeRadioBtn;
    private JRadioButton visitorTypeRadioBtn;

    private JComboBox parkingSlotBox;
    private JButton parkBtn;
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
        parkCarFrame = super.makeFrame("Park Car", 500, 300);
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
                    JOptionPane.showMessageDialog(parkCarFrame, "Please select a parking slot type radio button", "Validation Error!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                String potentialCarRegistration = carRegistrationFld.getText();
                if (!Application.isValidCarRegistration(potentialCarRegistration)) {
                    JOptionPane.showMessageDialog(parkCarFrame, "Car Registration is not of the required format", "Error!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String carOwnerName = carOwnerNameFld.getText();
                if (carOwnerName.length() < 1) {
                    JOptionPane.showMessageDialog(parkCarFrame, "Car Owner Name Needs To Be Entered", "Error!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (parkingSlotBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(parkCarFrame, "Please select a parking slot id", "Error!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String selectedParkingSlot = parkingSlotBox.getSelectedItem().toString();
                if (carPark.isCarWithRegistrationAlreadyParked(potentialCarRegistration)) {
                    JOptionPane.showMessageDialog(parkCarFrame, "A Car with registration '" + potentialCarRegistration + "' is already parked in the Car Park", "Error!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                carPark.parkCar(selectedParkingSlot, new Car(potentialCarRegistration, carOwnerName, isStaff));
                String message = "Parking Slot '" + selectedParkingSlot + "' is now occupied by Car with registration '" + potentialCarRegistration + "'";
                JOptionPane.showMessageDialog(parkCarFrame, message);
                parkingSlotBox.removeItem(selectedParkingSlot);
            }
        });

        //Setting buttons dimensions and position
        parkBtn.setBounds(250, 200, 80, 30);
        cancelBtn.setBounds(380, 200, 80, 30);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(true);
                parkCarFrame.dispose();
            }
        });

        String[] parkingSlotIds = carPark.retrieveParkingSlotIdsByType("staff");
        parkingSlotBox = new JComboBox(parkingSlotIds);
        parkingSlotBox.setBounds(380, 80, 80, 30);

        staffVisitorGroup = new ButtonGroup();

        JLabel parkingSlotTypeLabel = new JLabel("Staff/Visitor");

        staffTypeRadioBtn = new JRadioButton("Staff", true);
        staffTypeRadioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultComboBoxModel model = new DefaultComboBoxModel(carPark.retrieveParkingSlotIdsByType("staff"));
                parkingSlotBox.setModel(model);
            }
        });

        visitorTypeRadioBtn = new JRadioButton("Visitor");
        visitorTypeRadioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultComboBoxModel model = new DefaultComboBoxModel(carPark.retrieveParkingSlotIdsByType("visitor"));
                parkingSlotBox.setModel(model);
            }
        });

        parkingSlotTypeLabel.setBounds(50, 10, 120, 50);

        staffTypeRadioBtn.setBounds(250, 10, 120, 50);
        visitorTypeRadioBtn.setBounds(380, 10, 80, 50);

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
        parkCarFrame.add(parkBtn);
        parkCarFrame.add(cancelBtn);
        parkCarFrame.add(staffTypeRadioBtn);
        parkCarFrame.add(visitorTypeRadioBtn);
        parkCarFrame.add(parkingSlotTypeLabel);

        //Adding radio buttons to group
        staffVisitorGroup.add(staffTypeRadioBtn);
        staffVisitorGroup.add(visitorTypeRadioBtn);

        parkCarFrame.setVisible(true);
    }
}
