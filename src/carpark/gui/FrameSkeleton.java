package carpark.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class FrameSkeleton {
    JButton cancelBtn, exitBtn;


    public FrameSkeleton() {
        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        cancelBtn = new JButton("Cancel");
    }

    private JFrame makeFrame(String title) {
        JFrame frame = new JFrame(title);
        makeMenuBar(frame);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        return frame;
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
