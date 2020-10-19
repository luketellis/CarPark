package carpark.gui;

import carpark.code.Application;
import carpark.code.CarPark;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FindCarGUI implements ActionListener {
    private JLabel findCarLabel;
    private JTextField carRegistrationFld;


    private JButton findBtn, cancelBtn;
    private JFrame findCarFrame;
    private JFrame mainMenuFrame;


    CarPark carPark;

    public FindCarGUI(CarPark carPark, JFrame mainMenuFrame) {
        this.carPark = carPark;
        this.mainMenuFrame = mainMenuFrame;

        buildFrame();
        makeMenuBar(findCarFrame);
    }

    void buildFrame()     {
        findCarFrame = new JFrame("Find Parked Car");
        findCarFrame.setSize(500,300);
        findCarFrame.setLayout(null);

        //Initialize find button and set bounds
        findBtn = new JButton("Find Car");
        findBtn.setBounds(250, 90, 80, 30);

        findBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String errorText = "Car Registration is not of the required format";

                if (!Application.isValidCarRegistration(carRegistrationFld.getText()))
                    JOptionPane.showMessageDialog(findCarFrame, errorText);
                }

        });

        //Initialize exit button and set bounds
        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(380, 90, 80, 30);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frame, Application.isStringNumber(visitorsParksFld.getText()));
                mainMenuFrame.setVisible(true);
                findCarFrame.dispose();
            }
        });

        //Initialize label and set bounds
        findCarLabel = new JLabel("Enter Car Registration of Parked Car");
        findCarLabel.setBounds(50, 50, 250, 50);

        carRegistrationFld = new JTextField();
        carRegistrationFld.setBounds(50, 90, 80, 30);

        //Add UI elements to frame
        findCarFrame.add(findCarLabel);
        findCarFrame.add(carRegistrationFld);
        findCarFrame.add(findBtn);
        findCarFrame.add(cancelBtn);


        findCarFrame.setVisible(true);
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
