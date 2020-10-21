package carpark.gui;

import carpark.code.CarPark;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DeleteParkingSlotGUI extends FrameSkeleton implements ActionListener {
    private JLabel parkingSlotLabel;
    private JButton deleteBtn;
    private JComboBox parkingSlotBox;

    private JFrame deleteParkingSlotFrame;
    private JFrame mainMenuFrame;

    private CarPark carPark;

    public DeleteParkingSlotGUI(CarPark carPark, JFrame mainMenuFrame) {
        this.carPark = carPark;
        this.mainMenuFrame = mainMenuFrame;

        buildFrame();
        makeMenuBar(deleteParkingSlotFrame);
    }

    void buildFrame()   {
        deleteParkingSlotFrame = super.makeFrame("Delete Parking Slot", 500, 300);
        deleteParkingSlotFrame.setLayout(null);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(250, 90, 80, 30);

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (parkingSlotBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(deleteParkingSlotFrame, "Please select a parking slot id");
                    return;
                }

                String parkingSlotId = parkingSlotBox.getSelectedItem().toString();

                carPark.removeParkingSlotById(parkingSlotId);
                parkingSlotBox.removeItem(parkingSlotId);
                String parkingSlotRemovedMessage = "Parking Slot with id '" + parkingSlotId + "' has been removed from the system";
                JOptionPane.showMessageDialog(deleteParkingSlotFrame, parkingSlotRemovedMessage);
            }

        });

        cancelBtn.setBounds(380, 90, 80, 30);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(true);
                deleteParkingSlotFrame.dispose();
            }
        });

        String[] parkingSlotIds = carPark.retrieveEmptyParkingSlots();
        parkingSlotBox = new JComboBox(parkingSlotIds);
        parkingSlotBox.setBounds(50, 90, 80, 30);

        //Initialize label and set bounds
        parkingSlotLabel = new JLabel("Enter Parking Slot Id of Parked Car");
        parkingSlotLabel.setBounds(50, 50, 250, 50);

        //Add UI elements to frame
        deleteParkingSlotFrame.add(parkingSlotLabel);
        deleteParkingSlotFrame.add(parkingSlotBox);
        deleteParkingSlotFrame.add(deleteBtn);
        deleteParkingSlotFrame.add(cancelBtn);

        deleteParkingSlotFrame.setVisible(true);
    }
}
