package carpark.gui;

import carpark.code.CarPark;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CreateParkingSlotGUI extends FrameSkeleton implements ActionListener {
    private JLabel parkingSlotTypeLabel;
    ButtonGroup parkingSlotTypeGroup;
    JRadioButton staffTypeRadioBtn;
    JRadioButton visitorTypeRadioBtn;

    CarPark carPark;

    private JButton createBtn;
    private JFrame createParkingSlotFrame;
    private JFrame mainMenuFrame;

    public CreateParkingSlotGUI(CarPark carPark, JFrame mainMenuFrame) {
        this.carPark = carPark;
        this.mainMenuFrame = mainMenuFrame;

        buildFrame();
        makeMenuBar(createParkingSlotFrame);
    }

    void buildFrame()     {
        createParkingSlotFrame = super.makeFrame("Car Park Management System Main Menu", 500, 300);
        createParkingSlotFrame.setLayout(null);

        //Initialization of radio buttons
        staffTypeRadioBtn = new JRadioButton("Staff", true);
        visitorTypeRadioBtn = new JRadioButton("Visitor");

        // Initialization of object of "JButton" class.
        createBtn = new JButton("Create");
        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(true);
                createParkingSlotFrame.dispose();
            }
        });


        parkingSlotTypeGroup = new ButtonGroup();

        parkingSlotTypeLabel = new JLabel("Parking Slot Type");
        parkingSlotTypeLabel.setBounds(30, 30, 120, 50);

        //Setting position of radio buttons
        staffTypeRadioBtn.setBounds(250, 30, 120, 50);
        visitorTypeRadioBtn.setBounds(380, 30, 80, 50);

        //Setting dimensions and position of buttons
        createBtn.setBounds(250, 90, 80, 30);
        cancelBtn.setBounds(380, 90, 80, 30);

        //Adding elements to frame and setting it to visible
        createParkingSlotFrame.add(createBtn);
        createParkingSlotFrame.add(cancelBtn);
        createParkingSlotFrame.add(staffTypeRadioBtn);
        createParkingSlotFrame.add(visitorTypeRadioBtn);
        createParkingSlotFrame.add(parkingSlotTypeLabel);
        parkingSlotTypeGroup.add(staffTypeRadioBtn);
        parkingSlotTypeGroup.add(visitorTypeRadioBtn);
        createParkingSlotFrame.setVisible(true);
    }
}
