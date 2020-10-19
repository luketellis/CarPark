package carpark.gui;

import carpark.code.CarPark;
import carpark.code.ParkingSlot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CarParkMenuGUI {
    private final JButton parkCarBtn, findCarBtn, createParkBtn, deleteParkBtn, removeCarBtn, exitBtn;

    private JFrame frame;

    CarPark carPark;


    public CarParkMenuGUI(int staffSpots, int visitorSpots) {
        makeFrame();


        carPark = new CarPark();
        carPark.addParkingSlotsByNumberAndType(staffSpots, "staff");

        carPark.addParkingSlotsByNumberAndType(visitorSpots, "visitor");


        parkCarBtn = new JButton("Park Car");


        findCarBtn = new JButton("Find Car");
        findCarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new FindCarGUI();
            }
        });

        createParkBtn = new JButton("Create Parking Slot");

        createParkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new AddParkingSlotGUI();
            }
        });

        deleteParkBtn = new JButton("Delete Parking Slot");

        deleteParkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new DeleteParkingSlotGUI();
            }
        });

        removeCarBtn = new JButton("Remove Car");

        removeCarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new RemoveCarGUI();
            }
        });


        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frame, Application.isStringNumber(visitorsParksFld.getText()));
                frame.dispose();
                System.exit(0);
            }
        });

        frame.add(parkCarBtn);
        frame.add(findCarBtn);
        frame.add(createParkBtn);
        frame.add(deleteParkBtn);
        frame.add(removeCarBtn);
        frame.add(exitBtn);


        JTable table = createTable();
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);

        frame.setLayout(new FlowLayout());
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JTable createTable() {
        // Data to be displayed in the JTable

        Object[][] object = new Object[carPark.carParkList.size()][3];
        int i = 0;
        if (carPark.carParkList.size() != 0) {
            for (ParkingSlot slot : carPark.carParkList) {
                object[i][0] = slot.getId();
                object[i][1] = slot.getType();
                object[i][2] = slot.getCar();
                i++;
            }
        }

        // Column Names
        String[] columnNames = {"Parking Slot Id", "Type", "Car"};

        JTable j = new JTable(object, columnNames);

        j.setBounds(30, 40, 200, 300);

        return j;
    }

    private void makeFrame() {
        frame = new JFrame("Car Park");
        makeMenuBar(frame);
        frame.setVisible(true);
        frame.pack();
    }

    void makeMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        JMenuItem quitItem = new JMenuItem("Quit");
        fileMenu.add(quitItem);
    }
}
