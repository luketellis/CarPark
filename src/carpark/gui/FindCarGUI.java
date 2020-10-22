package carpark.gui;

import carpark.code.CarPark;
import carpark.code.ParkingSlot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FindCarGUI extends FrameSkeleton implements ActionListener {
    private JLabel findCarLabel;
    private JComboBox carRegistrationBox;
    private JButton findBtn;

    private JFrame findCarFrame, mainMenuFrame;

    private JPanel buttonsPanel, labelPanel, comboBoxPanel;

    private CarPark carPark;

    public FindCarGUI(CarPark carPark, JFrame mainMenuFrame) {
        this.carPark = carPark;
        this.mainMenuFrame = mainMenuFrame;

        buildFrame();
        makeMenuBar(findCarFrame);
    }

    void buildFrame() {
        findCarFrame = super.makeFrame("Find Parked Car", 500, 300);

        findCarLabel = new JLabel("Select Car Registration of Parked Car");
        findBtn = new JButton("Find Car");

        String[] carRegistrations = carPark.retrieveCarRegistrations();
        carRegistrationBox = new JComboBox(carRegistrations);
        carRegistrationBox.setPreferredSize(new Dimension(100, 20));

        findBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (carRegistrationBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(findCarFrame, "Please select a car registration number");
                    return;
                }

                String carRegistrationNumber = carRegistrationBox.getSelectedItem().toString();

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

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(true);
                findCarFrame.dispose();
            }
        });

        arrangeElementsIntoPanelsAndAddToFrame();
        addPanelsToFrameAndSetVisible();
    }

    public void arrangeElementsIntoPanelsAndAddToFrame()  {
        comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout());
        comboBoxPanel.add(carRegistrationBox);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(findBtn);
        buttonsPanel.add(cancelBtn);

        labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout());
        labelPanel.add(findCarLabel);

        findCarFrame.add(labelPanel, BorderLayout.NORTH);
        findCarFrame.add(comboBoxPanel,BorderLayout.CENTER);
        findCarFrame.add(buttonsPanel, BorderLayout.PAGE_END);
    }

    /**
     * Adds the Panels to the Frame and sets the visibility of the Frame to true
     */
    public void addPanelsToFrameAndSetVisible()  {
        findCarFrame.add(labelPanel, BorderLayout.NORTH);
        findCarFrame.add(comboBoxPanel,BorderLayout.CENTER);
        findCarFrame.add(buttonsPanel, BorderLayout.PAGE_END);
        findCarFrame.setVisible(true);
    }
}
