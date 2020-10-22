package carpark.gui;

import carpark.code.CarPark;
import carpark.code.ParkingSlot;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;


public class CarParkMenuGUI extends FrameSkeleton {
    private final int minimumTableRowWidth = 160;
    private JButton parkCarBtn, findCarBtn, createParkBtn, deleteParkBtn, removeCarBtn;
    private JTable parkingSlotTable;
    private JFrame mainMenuFrame;

    private JPanel buttonPanel, tablePanel;

    String[] columnNames = {"Parking Slot Id", "Type", "Car Details"};
    DefaultTableModel model;
    JScrollPane sp;

    CarPark carPark;

    public CarParkMenuGUI(int staffSpots, int visitorSpots) {
        mainMenuFrame = super.makeFrame("Car Park Management System Main Menu", 500, 300);
        mainMenuFrame.setVisible(true);
        mainMenuFrame.pack();

        buttonPanel = new JPanel();
        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

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
                resizeTable();
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
                new DeleteParkingSlotGUI(carPark, mainMenuFrame);
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

        buttonPanel.add(parkCarBtn);
        buttonPanel.add(findCarBtn);
        buttonPanel.add(createParkBtn);
        buttonPanel.add(deleteParkBtn);
        buttonPanel.add(removeCarBtn);
        buttonPanel.add(exitBtn);

        parkingSlotTable = createTable();
        sp = new JScrollPane(parkingSlotTable);
        resizeTable();

        tablePanel.add(sp);

        mainMenuFrame.add(buttonPanel, BorderLayout.PAGE_START);
        mainMenuFrame.add(tablePanel,BorderLayout.CENTER);
        mainMenuFrame.setSize(minimumTableRowWidth * 5,minimumTableRowWidth * 5);
    }

    private void resizeTable()  {
        parkingSlotTable.getColumnModel().getColumn(0).setPreferredWidth(minimumTableRowWidth);
        parkingSlotTable.getColumnModel().getColumn(1).setPreferredWidth(minimumTableRowWidth);
        parkingSlotTable.getColumnModel().getColumn(2).setPreferredWidth(minimumTableRowWidth * 6);
        parkingSlotTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    private JTable createTable() {
        //Create data to be set in table
        Object[][] data = createTableData();

        //declare new DefaultTableModel and set cells to uneditable
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
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

                if (slot.getCar() != null)
                    data[i][2] = slot.getCar().printUserFriendlyString();

                i++;
            }
        }

        return data;
    }
}
