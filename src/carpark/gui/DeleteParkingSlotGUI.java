package carpark.gui;

import carpark.code.Application;
import carpark.code.CarPark;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DeleteParkingSlotGUI implements ActionListener {
    private JLabel parkingSlotLabel;
    private JButton deleteBtn, cancelBtn;
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

    void buildFrame()     {
        deleteParkingSlotFrame = new JFrame("Delete Parking Slot");
        deleteParkingSlotFrame.setSize(500,300);
        deleteParkingSlotFrame.setLayout(null);

        //Initialize find button and set bounds
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

        //Initialize cancel button and set bounds
        cancelBtn = new JButton("Cancel");
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
