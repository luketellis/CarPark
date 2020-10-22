package carpark.gui;

import carpark.code.CarPark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DeleteParkingSlotGUI extends FrameSkeleton implements ActionListener {
    private JLabel parkingSlotLabel;
    private JButton deleteBtn;
    private JComboBox parkingSlotBox;

    private JFrame deleteParkingSlotFrame;
    private JFrame mainMenuFrame;
    private JPanel labelComboBoxPanel, buttonsPanel;

    private CarPark carPark;

    public DeleteParkingSlotGUI(CarPark carPark, JFrame mainMenuFrame) {
        this.carPark = carPark;
        this.mainMenuFrame = mainMenuFrame;

        buildFrame();
        makeMenuBar(deleteParkingSlotFrame);
    }

    void buildFrame()   {
        deleteParkingSlotFrame = super.makeFrame("Delete Parking Slot", 500, 300);

        parkingSlotLabel = new JLabel("Select Parking Slot Id To Delete");
        String[] parkingSlotIds = carPark.retrieveEmptyParkingSlots();
        parkingSlotBox = new JComboBox(parkingSlotIds);
        parkingSlotBox.setPreferredSize(new Dimension(80, 20));

        deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (parkingSlotBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(deleteParkingSlotFrame, "Please select a Parking Slot Id");
                    return;
                }

                String parkingSlotId = parkingSlotBox.getSelectedItem().toString();

                carPark.removeParkingSlotById(parkingSlotId);
                parkingSlotBox.removeItem(parkingSlotId);
                String parkingSlotRemovedMessage = "Parking Slot with id '" + parkingSlotId + "' has been removed from the system";
                JOptionPane.showMessageDialog(deleteParkingSlotFrame, parkingSlotRemovedMessage);
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(true);
                deleteParkingSlotFrame.dispose();
            }
        });
        arrangeElementsIntoPanelsAndAddToFrame();
        addPanelsToFrameAndSetVisible();
    }

    public void arrangeElementsIntoPanelsAndAddToFrame()  {
        labelComboBoxPanel = new JPanel();
        labelComboBoxPanel.setLayout(new FlowLayout());
        labelComboBoxPanel.add(parkingSlotLabel);
        labelComboBoxPanel.add(parkingSlotBox);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(deleteBtn);
        buttonsPanel.add(cancelBtn);
    }

    /**
     * Adds the Panels to the Frame and sets the visibility of the Frame to true
     */
    public void addPanelsToFrameAndSetVisible()  {
        deleteParkingSlotFrame.add(labelComboBoxPanel, BorderLayout.NORTH);
        deleteParkingSlotFrame.add(buttonsPanel, BorderLayout.CENTER);
        deleteParkingSlotFrame.setVisible(true);
    }
}
