package com.chess.gui;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.move.Move;
import com.chess.engine.move.MoveAttempt;
import com.chess.engine.move.MoveFactory;
import com.chess.engine.piece.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.chess.engine.board.Board.*;
import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class Table {

    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(800, 800);
    public static final Dimension BOARD_PANEL_DIMENSION = new Dimension(350, 400);
    public static final Dimension TILE_PANEL_DIMENSION = new Dimension(35, 35);
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private Board board;
    private Tile sourceTile;
    private Tile destinationTile;
    private Piece sourcePiece;

    public Table() {
        this.board = new Board.BoardBuilder().setStartingPlayer().populateMap().build();
        this.gameFrame = new JFrame("Chess");
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        final JMenuBar tableMenuBar = new JMenuBar();

        populateMenu(tableMenuBar);
        this.gameFrame.setJMenuBar(tableMenuBar);


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

        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
                System.out.println("Exit JChess!");

            }
        });
        fileMenu.add(exitMenuItem);

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

        public void drawBoard(Board board) {
            removeAll();
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {

                    tilePanels[i][j].drawTile(board);
                    add(tilePanels[i][j]);
                }
            }
            validate();
            repaint();
        }
    }

    private class TilePanel extends JPanel {

        private static final String PIECE_ICONS_PATH = "src//main//resources//piece_icons";
        private final int tileRowId;
        private final int tileColumnId;


        TilePanel(final BoardPanel boardPanel, final int tileRowId, final int tileColumnId) {
            super(new GridLayout());
            this.tileRowId = tileRowId;
            this.tileColumnId = tileColumnId;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignPanelColor();
            assignTilePieceIcon(board);
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (isRightMouseButton(e)) {
                        // cancel left mouse click
                        sourceTile = null;
                        destinationTile = null;
                        sourcePiece = null;
                    } else if (isLeftMouseButton(e)) {
                        if (sourceTile == null) {
                            sourceTile = board.getTile(tileRowId, tileColumnId);
                            sourcePiece = sourceTile.getPiece();
                            if (sourcePiece == null) {
                                System.out.println("no piece here");
                                sourceTile = null;
                            } else {
                                System.out.println(sourcePiece.toString() + " is about to move");
                            }
                        } else if (destinationTile == null) {
                            destinationTile = board.getTile(tileRowId, tileColumnId);
                            Move move = MoveFactory.constructMove(board, destinationTile.getPosition(),
                                    sourceTile.getPosition());
                            MoveAttempt moveAttempt = board.getCurrentPlayer().makeMove(move, board);
                            if (moveAttempt.getMoveStatus() == MoveAttempt.MoveStatus.OK) {
                                board = moveAttempt.getBoard();
                            }
                            System.out.println("now clicking on destination tile");
                            sourceTile = null;
                            destinationTile = null;
                            sourcePiece = null;
                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                boardPanel.drawBoard(board);
                            }
                        });
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            validate();
        }

        private void assignPanelColor() {
            if ((((tileRowId & 1) == 0 && (tileColumnId & 1) == 0) || ((tileRowId & 1) != 0 && (tileColumnId & 1) != 0))) {
                setBackground(Color.WHITE);
            } else {
                setBackground(Color.DARK_GRAY);
            }
        }

        private void assignTilePieceIcon(final Board board) {
            this.removeAll();
            if (board.getTile(tileRowId, tileColumnId).isOccupied()) {
                try {
                    final BufferedImage image = ImageIO.read(new File(PIECE_ICONS_PATH + File.separator + board.getTile(tileRowId, tileColumnId).getPiece().getColor().toString().charAt(0) +
                            board.getTile(tileRowId, tileColumnId).getPiece().toString() + ".gif"));
                    add(new JLabel(new ImageIcon(image)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        public void drawTile(Board board) {
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignPanelColor();
            assignTilePieceIcon(board);
            validate();
            repaint();
        }
    }
}
