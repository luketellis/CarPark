package carpark.gui;

import carpark.code.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartMenuGUI extends FrameSkeleton implements ActionListener {
    private JLabel staffLabel, visitorLabel;
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

        staffLabel = new JLabel("Staff");
        staffParksFld = new JTextField("1");
        visitorLabel = new JLabel("Visitors");
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

        //Set layout and add elements to panels
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        fieldsPanel.add(staffLabel);
        fieldsPanel.add(staffParksFld);
        fieldsPanel.add(visitorLabel);
        fieldsPanel.add(visitorsParksFld);

        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(startBtn);
        buttonsPanel.add(exitBtn);

        //Add panels to frame and set as visible
        startMenuFrame.add(fieldsPanel, BorderLayout.PAGE_START);
        startMenuFrame.add(emptyPanel, BorderLayout.CENTER);
        startMenuFrame.add(buttonsPanel, BorderLayout.PAGE_END);
        startMenuFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new StartMenuGUI();
    }
}
