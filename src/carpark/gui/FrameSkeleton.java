package carpark.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class FrameSkeleton {
    JButton cancelBtn, exitBtn;

    public JFrame makeFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setSize(width, height);
        //makeMenuBar(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        cancelBtn = new JButton("Cancel");
        return frame;
    }

    void makeMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenuItem aboutItem = new JMenuItem("About");
        fileMenu.add(aboutItem);
        JMenuItem quitItem = new JMenuItem("Quit");
        fileMenu.add(quitItem);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Quit")) {

        }
    }
}
