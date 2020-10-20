package carpark.gui;

import carpark.code.CarPark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RemoveCarGUI implements ActionListener {
    private JLabel carRegistrationLabel;
    private JTextField carRegistrationFld;


    private JButton removeBtn, cancelBtn;
    private JFrame removeCarFrame;

    private CarPark carPark;
    private Frame mainMenuFrame;
    private JComboBox carRegistrationBox;

    public RemoveCarGUI(CarPark carPark, JFrame mainMenuFrame) {
        this.carPark = carPark;
        this.mainMenuFrame = mainMenuFrame;

        buildFrame();
        makeMenuBar(removeCarFrame);
    }

    void buildFrame() {
        removeCarFrame = new JFrame("Remove Car from Parking Slot");
        removeCarFrame.setSize(500, 300);
        removeCarFrame.setLayout(null);

        //Initialize find button and set bounds
        removeBtn = new JButton("Remove Car");
        removeBtn.setBounds(250, 90, 80, 30);

        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (carRegistrationBox.getSelectedItem() == null)
                {
                    JOptionPane.showMessageDialog(removeCarFrame, "Please select a car registration number");
                    return;
                }

                String carRegistrationNumber = carRegistrationBox.getSelectedItem().toString();
                String message = carRegistrationNumber + " has been removed from Car Park";

                carPark.removeCarByRegistrationNumber(carRegistrationNumber);
                JOptionPane.showMessageDialog(removeCarFrame, message);
                carRegistrationBox.removeItem(carRegistrationNumber);
            }

        });

        //Initialize exit button and set bounds
        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(380, 90, 80, 30);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(true);
                removeCarFrame.dispose();
            }
        });

        //Initialize label and set bounds
        carRegistrationLabel = new JLabel("Enter Car Registration of Parked Car");
        carRegistrationLabel.setBounds(50, 50, 250, 50);

/*      carRegistrationFld = new JTextField();
        carRegistrationFld.setBounds(50, 90, 80, 30);*/

        String[] carRegistrations = carPark.retrieveCarRegistrations();
        carRegistrationBox = new JComboBox(carRegistrations);
        carRegistrationBox.setBounds(50, 90, 80, 30);

        removeCarFrame.add(carRegistrationBox);
        //Add UI elements to frame
        removeCarFrame.add(carRegistrationLabel);
        removeCarFrame.add(removeBtn);
        removeCarFrame.add(cancelBtn);

        removeCarFrame.setVisible(true);
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
