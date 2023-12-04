package com.chess.gui;

import com.chess.engine.board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.board.Board.*;

public class Table {

    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(800, 800);
    public static final Dimension BOARD_PANEL_DIMENSION = new Dimension(350, 40);
    public static final Dimension TILE_PANEL_DIMENSION = new Dimension(35, 35);
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;

    public Table() {
        this.gameFrame = new JFrame("Chess");
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
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

    private class BoardPanel extends JPanel {
        final TilePanel[][] tilePanels = new TilePanel[BOARD_SIZE][BOARD_SIZE];

        public BoardPanel() {
            super(new GridLayout(BOARD_SIZE, BOARD_SIZE));
//            this.tilePanels = new ArrayList<>();
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    TilePanel tilePanel = new TilePanel(this, i, j);
                    tilePanels[i][j] = tilePanel;
                    add(tilePanel);
                }
            }
            setPreferredSize(new Dimension(BOARD_PANEL_DIMENSION));
            validate();
        }
    }

    private class TilePanel extends JPanel {

        private final int tileRowId;
        private final int tileColumnId;


        TilePanel(final BoardPanel boardPanel, final int tileRowId, final int tileColumnId) {
            super(new GridLayout());
            this.tileRowId = tileRowId;
            this.tileColumnId = tileColumnId;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignPanelColor();
            validate();
        }

        private void assignPanelColor() {
            if ((((tileRowId & 1) == 0 && (tileColumnId & 1) == 0) || ((tileRowId & 1) != 0 && (tileColumnId & 1) != 0))) {
                setBackground(Color.WHITE);
            }
            else{
                setBackground(Color.DARK_GRAY);
            }
        }
    }
}
