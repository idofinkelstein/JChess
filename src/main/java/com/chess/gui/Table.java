package com.chess.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table {

    private final JFrame gameFrame;

    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(1000, 600);

    public Table() {
        this.gameFrame = new JFrame("Chess");
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        final JMenuBar tableMenuBar = new JMenuBar();

        populateMenu(tableMenuBar);
        this.gameFrame.setJMenuBar(tableMenuBar);

//        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.gameFrame.setResizable(false);
//        this.gameFrame.setLocationRelativeTo(null);

        this.gameFrame.setVisible(true);
    }

    private void populateMenu(JMenuBar tableMenuBar) {
        tableMenuBar.add(createFileMenu());
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");

        final JMenuItem openPGN = new JMenuItem("Load PGN file");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("open up that PGN file");

            }
        });
        fileMenu.add(openPGN);
        return fileMenu;
    }
}
