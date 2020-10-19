package carpark.gui;

import carpark.code.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartMenuGUI implements ActionListener {
    private JLabel staffLabel, visitorLabel;
    private JButton startBtn, exitBtn;


    private JTextField staffParksFld, visitorsParksFld;
    private JPanel buttonsPanel, emptyPanel, fieldsPanel;
    private JFrame frame;

    public StartMenuGUI() {
        buildFrame();
        makeMenuBar(frame);
    }

    void buildFrame() {
        frame= new JFrame("Car Park System");
        buttonsPanel = new JPanel();
        emptyPanel = new JPanel();
        fieldsPanel = new JPanel();
        frame.setSize(350,250);

        staffLabel = new JLabel("Staff");
        visitorLabel = new JLabel("Visitors");
        staffParksFld = new JTextField("1");
        visitorsParksFld = new JTextField("1");
        startBtn = new JButton("Create Car Park");
        exitBtn = new JButton("Exit");

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!Application.isStringNumber(staffParksFld.getText()) || !Application.isStringNumber(visitorsParksFld.getText()))
                    JOptionPane.showMessageDialog(frame, "Starting Parking Slots must be four digit numbers");
                else
                {
                    frame.dispose();
                    new CarParkMenuGUI(Integer.parseInt(staffParksFld.getText()), Integer.parseInt(visitorsParksFld.getText()));
                }
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frame, Application.isStringNumber(visitorsParksFld.getText()));
                frame.dispose();
                System.exit(0);
            }
        });

        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        buttonsPanel.setLayout(new FlowLayout());

        fieldsPanel.add(staffLabel);
        fieldsPanel.add(staffParksFld);
        fieldsPanel.add(visitorLabel);
        fieldsPanel.add(visitorsParksFld);
        buttonsPanel.add(startBtn);
        buttonsPanel.add(exitBtn);

        frame.add(fieldsPanel, BorderLayout.PAGE_START);
        frame.add(emptyPanel, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        new StartMenuGUI();
    }
}
