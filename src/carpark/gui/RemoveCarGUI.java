package carpark.gui;

import carpark.code.CarPark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RemoveCarGUI extends FrameSkeleton implements ActionListener {
    private JLabel carRegistrationLabel;
    private JButton removeBtn;
    private JComboBox carRegistrationBox;

    private JFrame mainMenuFrame, removeCarFrame;
    private JPanel labelComboBoxPanel, buttonsPanel;

    private CarPark carPark;

    public RemoveCarGUI(CarPark carPark, JFrame mainMenuFrame) {
        this.carPark = carPark;
        this.mainMenuFrame = mainMenuFrame;

        buildFrame();
        makeMenuBar(removeCarFrame);
    }

    void buildFrame() {
        removeCarFrame = super.makeFrame("Remove Car", 500, 300);

        carRegistrationLabel = new JLabel("Enter Car Registration of Parked Car To Be Removed");
        String[] carRegistrations = carPark.retrieveCarRegistrations();
        carRegistrationBox = new JComboBox(carRegistrations);
        carRegistrationBox.setPreferredSize(new Dimension(100, 20));


        removeBtn = new JButton("Remove Car");
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

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuFrame.setVisible(true);
                removeCarFrame.dispose();
            }
        });
        arrangeElementsIntoPanelsAndAddToFrame();
        addPanelsToFrameAndSetVisible();
    }

    public void arrangeElementsIntoPanelsAndAddToFrame()  {
        labelComboBoxPanel = new JPanel();
        labelComboBoxPanel.setLayout(new FlowLayout());
        labelComboBoxPanel.add(carRegistrationLabel);
        labelComboBoxPanel.add(carRegistrationBox);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(removeBtn);
        buttonsPanel.add(cancelBtn);

        removeCarFrame.add(labelComboBoxPanel, BorderLayout.NORTH);
        removeCarFrame.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds the Panels to the Frame and sets the visibility of the Frame to true
     */
    public void addPanelsToFrameAndSetVisible()  {
        removeCarFrame.add(labelComboBoxPanel, BorderLayout.NORTH);
        removeCarFrame.add(buttonsPanel, BorderLayout.SOUTH);
        removeCarFrame.setVisible(true);
    }
}
