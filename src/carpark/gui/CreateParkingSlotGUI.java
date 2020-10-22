package carpark.gui;

import carpark.code.CarPark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CreateParkingSlotGUI extends FrameSkeleton implements ActionListener {
    private JLabel parkingSlotTypeLabel;
    ButtonGroup parkingSlotTypeGroup;
    JRadioButton staffTypeRadioBtn;
    JRadioButton visitorTypeRadioBtn;

    CarPark carPark;

    private JButton createBtn;
    private JPanel buttonsPanel, labelPanel, radioButtonsPanel;
    private JFrame createParkingSlotFrame;
    private JFrame mainMenuFrame;

    public CreateParkingSlotGUI(CarPark carPark, JFrame mainMenuFrame) {
        this.carPark = carPark;
        this.mainMenuFrame = mainMenuFrame;

        buildFrame();
        makeMenuBar(createParkingSlotFrame);
    }

    void buildFrame()     {
        createParkingSlotFrame = super.makeFrame("Create New parking Slot", 500, 300);

        //Initialization of radio buttons
        parkingSlotTypeLabel = new JLabel("Select Parking Slot Type To Be Created");

        parkingSlotTypeGroup = new ButtonGroup();
        staffTypeRadioBtn = new JRadioButton("Staff", true);
        visitorTypeRadioBtn = new JRadioButton("Visitor");
        parkingSlotTypeGroup.add(staffTypeRadioBtn);
        parkingSlotTypeGroup.add(visitorTypeRadioBtn);

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

        arrangeElementsIntoPanelsAndAddToFrame();
        addPanelsToFrameAndSetVisible();
    }

    public void arrangeElementsIntoPanelsAndAddToFrame()  {
        labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout());
        labelPanel.add(parkingSlotTypeLabel);

        radioButtonsPanel = new JPanel();
        radioButtonsPanel.setLayout(new FlowLayout());
        radioButtonsPanel.add(staffTypeRadioBtn);
        radioButtonsPanel.add(visitorTypeRadioBtn);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(createBtn);
        buttonsPanel.add(cancelBtn);
    }

    /**
     * Adds the Panels to the Frame and sets the visibility of the Frame to true
     */
    public void addPanelsToFrameAndSetVisible()  {
        createParkingSlotFrame.add(labelPanel, BorderLayout.NORTH);
        createParkingSlotFrame.add(radioButtonsPanel, BorderLayout.CENTER);
        createParkingSlotFrame.add(buttonsPanel, BorderLayout.SOUTH);
        createParkingSlotFrame.setVisible(true);
    }
}