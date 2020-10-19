package carpark.gui;

import carpark.code.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RemoveCarGUI implements ActionListener {
    private JLabel carRegistrationLabel;
    private JTextField carRegistrationFld;


    private JButton removeBtn, cancelBtn;
    private JFrame frame;

    public RemoveCarGUI() {
        buildFrame2();
        makeMenuBar(frame);
    }

    void buildFrame2()     {
        frame= new JFrame("Remove Car from Parking Slot");
        frame.setSize(500,300);
        frame.setLayout(null);

        //Initialize find button and set bounds
        removeBtn = new JButton("Remove Car");
        removeBtn.setBounds(250, 90, 80, 30);

        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String errorText = "Car Registration is not of the required format";

                if (!Application.isValidCarRegistration(carRegistrationFld.getText()))
                    JOptionPane.showMessageDialog(frame, errorText);
                }

        });

        //Initialize exit button and set bounds
        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(380, 90, 80, 30);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frame, Application.isStringNumber(visitorsParksFld.getText()));
/*                mainMenuFrame.setVisible(true);
                parkCarFrame.dispose();*/
            }
        });

        //Initialize label and set bounds
        carRegistrationLabel = new JLabel("Enter Car Registration of Parked Car");
        carRegistrationLabel.setBounds(50, 50, 250, 50);

        carRegistrationFld = new JTextField();
        carRegistrationFld.setBounds(50, 90, 80, 30);

        //Add UI elements to frame
        frame.add(carRegistrationLabel);
        frame.add(carRegistrationFld);
        frame.add(removeBtn);
        frame.add(cancelBtn);


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
        new RemoveCarGUI();
    }
}
