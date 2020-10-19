package carpark.gui;

import carpark.code.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FindCarGUI implements ActionListener {
    private JLabel findCarLabel;
    private JTextField carRegistrationFld;


    private JButton findBtn, exitBtn;
    private JFrame frame;

    public FindCarGUI() {
        buildFrame2();
        //makeMenuBar(frame);
    }

    void buildFrame2()     {
        frame= new JFrame("Find Parked Car");
        frame.setSize(500,300);
        frame.setLayout(null);

        //Initialize find button and set bounds
        findBtn = new JButton("Find Car");
        findBtn.setBounds(250, 90, 80, 30);

        findBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String errorText = "Car Registration is not of the required format";

                if (!Application.isValidCarRegistration(carRegistrationFld.getText()))
                    JOptionPane.showMessageDialog(frame, errorText);
                }

        });

        //Initialize exit button and set bounds
        exitBtn = new JButton("Exit");
        exitBtn.setBounds(380, 90, 80, 30);

        //Initialize label and set bounds
        findCarLabel = new JLabel("Enter Car Registration of Parked Car");
        findCarLabel.setBounds(50, 50, 250, 50);

        carRegistrationFld = new JTextField();
        carRegistrationFld.setBounds(50, 90, 80, 30);

        //Add UI elements to frame
        frame.add(findCarLabel);
        frame.add(carRegistrationFld);
        frame.add(findBtn);
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
        new FindCarGUI();
    }
}
