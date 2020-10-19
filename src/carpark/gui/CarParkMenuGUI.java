package carpark.gui;

import carpark.code.CarPark;
import carpark.code.ParkingSlot;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;


public class CarParkMenuGUI {
    private final JButton parkCarBtn, findCarBtn, createParkBtn, deleteParkBtn, removeCarBtn, exitBtn;

    private JFrame mainMenuFrame;

    static JTable parkingSlotTable;
    //Table Column Names
    String[] columnNames = {"Parking Slot Id", "Type", "Car"};
    DefaultTableModel model;
    JScrollPane sp;

    CarPark carPark;


    public CarParkMenuGUI(int staffSpots, int visitorSpots) {
        makeFrame();

        mainMenuFrame.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                model = new DefaultTableModel(createTableData(), columnNames) {

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                parkingSlotTable.setModel(model);
                model.fireTableStructureChanged();
            }
        });

        carPark = new CarPark();
        carPark.addParkingSlotsByNumberAndType(staffSpots, "staff");
        carPark.addParkingSlotsByNumberAndType(visitorSpots, "visitor");


        parkCarBtn = new JButton("Park Car");
        parkCarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(false);
                new ParkCarGUI(carPark, mainMenuFrame);
            }
        });

        findCarBtn = new JButton("Find Car");
        findCarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(false);
                new FindCarGUI(carPark, mainMenuFrame);
            }
        });

        createParkBtn = new JButton("Create Parking Slot");

        createParkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(false);
                new CreateParkingSlotGUI(carPark, mainMenuFrame);
            }
        });

        deleteParkBtn = new JButton("Delete Parking Slot");

        deleteParkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(false);
                new DeleteParkingSlotGUI();
            }
        });

        removeCarBtn = new JButton("Remove Car");

        removeCarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(false);
                new RemoveCarGUI(carPark, mainMenuFrame);
            }
        });


        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frame, Application.isStringNumber(visitorsParksFld.getText()));
                mainMenuFrame.dispose();
                System.exit(0);
            }
        });

        mainMenuFrame.add(parkCarBtn);
        mainMenuFrame.add(findCarBtn);
        mainMenuFrame.add(createParkBtn);
        mainMenuFrame.add(deleteParkBtn);
        mainMenuFrame.add(removeCarBtn);
        mainMenuFrame.add(exitBtn);


        parkingSlotTable = createTable();

        parkingSlotTable.setBounds(30, 40, 250, 500);

        sp = new JScrollPane(parkingSlotTable);
        mainMenuFrame.add(sp);

        mainMenuFrame.setLayout(new FlowLayout());
        mainMenuFrame.setSize(800,800);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JTable createTable() {
        // Data to be displayed in the JTable
        Object[][] data = createTableData();


        model = new DefaultTableModel(data, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        return parkingSlotTable = new JTable(model);
    }

    private Object[][] createTableData()   {
        Object[][] data = new Object[carPark.carParkList.size()][3];
        int i = 0;
        if (carPark.carParkList.size() != 0) {
            for (ParkingSlot slot : carPark.carParkList) {
                data[i][0] = slot.getId();
                data[i][1] = slot.getType();
                data[i][2] = slot.getCar();
                i++;
            }
        }

        return data;
    }

    private void makeFrame() {
        mainMenuFrame = new JFrame("Car Park");
        makeMenuBar(mainMenuFrame);
        mainMenuFrame.setVisible(true);
        mainMenuFrame.pack();
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
