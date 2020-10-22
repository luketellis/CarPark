package carpark.gui;

import carpark.code.Application;
import carpark.code.Car;
import carpark.code.CarPark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ParkCarGUI extends FrameSkeleton implements ActionListener {
    private ButtonGroup staffVisitorGroup;
    private JRadioButton staffTypeRadioBtn;
    private JRadioButton visitorTypeRadioBtn;

    private JComboBox parkingSlotBox;
    private JButton parkBtn;
    private JLabel carOwnerNameLabel, carRegistrationLabel, parkingSlotLabel, parkingSlotTypeLabel;
    private JTextField carOwnerNameFld, carRegistrationFld;

    private JFrame mainMenuFrame, parkCarFrame;
    private JPanel buttonPanel, carOwnerPanel, carRegistrationPanel, parkCarPanel, parkingSlotPanel, staffVisitorPanel;

    private CarPark carPark;

    public ParkCarGUI(CarPark carPark, JFrame mainMenuFrame) {
        this.carPark = carPark;
        this.mainMenuFrame = mainMenuFrame;

        buildFrame();
        makeMenuBar(parkCarFrame);
    }

    void buildFrame() {
        parkCarFrame = super.makeFrame("Park Car", 500, 300);

        parkBtn = new JButton("Park");
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
                    JOptionPane.showMessageDialog(parkCarFrame, "Please select a Parking Slot Id", "Error!", JOptionPane.ERROR_MESSAGE);
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
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(true);
                parkCarFrame.dispose();
            }
        });

        String[] parkingSlotIds = carPark.retrieveParkingSlotIdsByType("staff");
        parkingSlotBox = new JComboBox(parkingSlotIds);
        parkingSlotBox.setPreferredSize(new Dimension(100, 20));

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

        staffVisitorGroup = new ButtonGroup();
        staffVisitorGroup.add(staffTypeRadioBtn);
        staffVisitorGroup.add(visitorTypeRadioBtn);

        carRegistrationLabel = new JLabel("Enter Car Registration of Parked Car");

        carRegistrationFld = new JTextField();
        carRegistrationFld.setPreferredSize(new Dimension(100, 20));

        parkingSlotLabel = new JLabel("Select Parking Slot");
        parkingSlotTypeLabel = new JLabel("Staff/Visitor");

        carOwnerNameLabel = new JLabel("Enter Car Owner's Name");

        carOwnerNameFld = new JTextField();
        carOwnerNameFld.setPreferredSize(new Dimension(100, 20));

        arrangeElementsIntoPanelsAndAddToFrame();
        addPanelsToFrameAndSetVisible();
    }

    public void arrangeElementsIntoPanelsAndAddToFrame() {
        parkCarPanel = new JPanel();
        parkCarPanel.setLayout(new BoxLayout(parkCarPanel, BoxLayout.PAGE_AXIS));

        staffVisitorPanel = new JPanel();
        staffVisitorPanel.add(parkingSlotTypeLabel);
        staffVisitorPanel.add(staffTypeRadioBtn);
        staffVisitorPanel.add(visitorTypeRadioBtn);
        parkCarPanel.add(staffVisitorPanel);

        parkingSlotPanel = new JPanel();
        parkingSlotPanel.setLayout(new FlowLayout());
        parkingSlotPanel.add(parkingSlotLabel);
        parkingSlotPanel.add(parkingSlotBox);
        parkCarPanel.add(parkingSlotPanel);

        carOwnerPanel = new JPanel();
        carOwnerPanel.setLayout(new FlowLayout());
        carOwnerPanel.add(carOwnerNameLabel);
        carOwnerPanel.add(carOwnerNameFld);
        parkCarPanel.add(carOwnerPanel);

        carRegistrationPanel = new JPanel();
        carRegistrationPanel.setLayout(new FlowLayout());
        carRegistrationPanel.add(carRegistrationLabel);
        carRegistrationPanel.add(carRegistrationFld);
        parkCarPanel.add(carRegistrationPanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(parkBtn);
        buttonPanel.add(cancelBtn);
    }

    public void addPanelsToFrameAndSetVisible() {
        parkCarFrame.add(parkCarPanel, BorderLayout.PAGE_START);
        parkCarFrame.add(buttonPanel, BorderLayout.PAGE_END);
        parkCarFrame.setVisible(true);
    }
}
