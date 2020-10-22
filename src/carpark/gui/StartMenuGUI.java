package carpark.gui;

import carpark.code.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartMenuGUI extends FrameSkeleton implements ActionListener {
    private JLabel headingLabel, staffLabel, visitorLabel;
    private JTextField staffParksFld, visitorsParksFld;
    private JButton startBtn;

    private JPanel buttonsPanel, emptyPanel, fieldsPanel;
    private JFrame startMenuFrame;

    public StartMenuGUI() {
        buildFrame();
        makeMenuBar(startMenuFrame);
    }

    void buildFrame() {
        startMenuFrame = super.makeFrame("Car Park Management System", 350, 250);
        buttonsPanel = new JPanel();
        emptyPanel = new JPanel();
        fieldsPanel = new JPanel();

        headingLabel = new JLabel("Enter Starting Parking Slots For System");
        staffLabel = new JLabel("Staff Parking Slots");
        staffParksFld = new JTextField("1");
        visitorLabel = new JLabel("Visitor Parking Slots");
        visitorsParksFld = new JTextField("1");
        startBtn = new JButton("Create Car Park");

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Application.isStringNumber(staffParksFld.getText()) || !Application.isStringNumber(visitorsParksFld.getText()))
                    JOptionPane.showMessageDialog(startMenuFrame, "Starting Parking Slots must be four digit numbers");
                else
                {
                    startMenuFrame.dispose();
                    new CarParkMenuGUI(Integer.parseInt(staffParksFld.getText()), Integer.parseInt(visitorsParksFld.getText()));
                }
            }
        });

        arrangeElementsIntoPanelsAndAddToFrame();
        addPanelsToFrameAndSetVisible();
    }

    public void arrangeElementsIntoPanelsAndAddToFrame()  {
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        fieldsPanel.add(headingLabel);
        fieldsPanel.add(emptyPanel);
        fieldsPanel.add(staffLabel);
        fieldsPanel.add(staffParksFld);
        fieldsPanel.add(visitorLabel);
        fieldsPanel.add(visitorsParksFld);

        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(startBtn);
        buttonsPanel.add(exitBtn);
    }

    /**
     * Adds the Panels to the Frame and sets the visibility of the Frame to true
     */
    public void addPanelsToFrameAndSetVisible()  {
        startMenuFrame.add(fieldsPanel, BorderLayout.PAGE_START);
        startMenuFrame.add(buttonsPanel, BorderLayout.PAGE_END);
        startMenuFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new StartMenuGUI();
    }
}
