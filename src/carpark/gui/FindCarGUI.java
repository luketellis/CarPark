package carpark.gui;

import carpark.code.Application;
import carpark.code.CarPark;
import carpark.code.ParkingSlot;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FindCarGUI extends FrameSkeleton implements ActionListener {
    private JLabel findCarLabel;

    private JComboBox carComboBox;

    private JButton findBtn;
    private JFrame findCarFrame;
    private JFrame mainMenuFrame;

    private CarPark carPark;

    public FindCarGUI(CarPark carPark, JFrame mainMenuFrame) {
        this.carPark = carPark;
        this.mainMenuFrame = mainMenuFrame;

        buildFrame();
        makeMenuBar(findCarFrame);
    }

    void buildFrame() {
        findCarFrame = super.makeFrame("Find Parked Car", 500, 300);
        findCarFrame.setLayout(null);

        //Initialize find button and set bounds
        findBtn = new JButton("Find Car");
        findBtn.setBounds(250, 90, 80, 30);

        String[] carRegistrations = carPark.retrieveCarRegistrations();
        carComboBox = new JComboBox(carRegistrations);
        carComboBox.setBounds(50, 90, 80, 30);

        findBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (carComboBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(findCarFrame, "Please select a car registration number");
                    return;
                }

                String carRegistrationNumber = carComboBox.getSelectedItem().toString();

                for (ParkingSlot parkingSlot : carPark.carParkList) {
                    //If parkingSlot has a car and the car's registration matches the requested registration
                    if (parkingSlot.getCar() != null && carRegistrationNumber.equals(parkingSlot.getCar().getRegistrationNumber())) {
                        String carRegistrationInformation = "Car with Registration Number '" + carRegistrationNumber + "' is currently parked in Parking Slot '" + parkingSlot.getId() + "'\n";
                        carRegistrationInformation += "The owner of the car is '" + parkingSlot.getCar().getOwner() + "'";

                        JOptionPane.showMessageDialog(findCarFrame, carRegistrationInformation);
                    }

                }
                return;
            }

        });

        cancelBtn.setBounds(380, 90, 80, 30);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(true);
                findCarFrame.dispose();
            }
        });

        //Initialize label and set bounds
        findCarLabel = new JLabel("Select Car Registration of Parked Car");
        findCarLabel.setBounds(50, 50, 250, 50);

        //Add UI elements to frame
        findCarFrame.add(findCarLabel);
        findCarFrame.add(findBtn);
        findCarFrame.add(cancelBtn);
        findCarFrame.add(carComboBox);

        findCarFrame.setVisible(true);
    }
}
