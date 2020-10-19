package carpark.gui;

import carpark.code.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DeleteParkingSlotGUI implements ActionListener {
    private JLabel parkingSlotLabel;
    private JTextField parkingSlotFld;


    private JButton deleteBtn, exitBtn;
    private JFrame frame;

    public DeleteParkingSlotGUI() {
        buildFrame2();
        makeMenuBar(frame);
    }

    void buildFrame2()     {
        frame= new JFrame("Delete Parking Slot");
        frame.setSize(500,300);
        frame.setLayout(null);

        //Initialize find button and set bounds
        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(250, 90, 80, 30);

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String errorText = "Parking Slot Id is not of the required format";

                if (!Application.isValidParkingSlotId(parkingSlotFld.getText()))
                    JOptionPane.showMessageDialog(frame, errorText);
                }

        });

        //Initialize exit button and set bounds
        exitBtn = new JButton("Exit");
        exitBtn.setBounds(380, 90, 80, 30);

        //Initialize label and set bounds
        parkingSlotLabel = new JLabel("Enter Parking Slot Id of Parked Car");
        parkingSlotLabel.setBounds(50, 50, 250, 50);

        parkingSlotFld = new JTextField();
        parkingSlotFld.setBounds(50, 90, 80, 30);

        //Add UI elements to frame
        frame.add(parkingSlotLabel);
        frame.add(parkingSlotFld);
        frame.add(deleteBtn);
        frame.add(exitBtn);


        frame.setVisible(true);
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

    public static void main(String[] args) {
        new DeleteParkingSlotGUI();
    }
}
