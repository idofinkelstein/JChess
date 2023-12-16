package com.chess.gui;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.move.CastlingMove;
import com.chess.engine.move.Move;
import com.chess.engine.move.MoveAttempt;
import com.chess.engine.move.MoveFactory;
import com.chess.engine.piece.King;
import com.chess.engine.piece.MoveVisitor;
import com.chess.engine.piece.MoveVisitorImpl;
import com.chess.engine.piece.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private boolean highlightLegalMoves = false;

    private final TakenPiecesPanel takenPiecesPanel;
    private final GameHistoryPanel gameHistoryPanel;

    private MoveLog moveLog;

    public Table() {
        this.moveLog = new MoveLog();
        this.board = new Board.BoardBuilder().setStartingPlayer().populateMap().build();
        this.gameFrame = new JFrame("Chess");
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.boardPanel = new BoardPanel();
        this.takenPiecesPanel = new TakenPiecesPanel();
        this.gameHistoryPanel = new GameHistoryPanel();
        this.gameFrame.add(takenPiecesPanel, BorderLayout.WEST);
        this.gameFrame.add(gameHistoryPanel, BorderLayout.EAST);
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        final JMenuBar tableMenuBar = new JMenuBar();

        populateMenu(tableMenuBar);
        this.gameFrame.setJMenuBar(tableMenuBar);


        this.gameFrame.setVisible(true);
    }

    private void populateMenu(JMenuBar tableMenuBar) {
        tableMenuBar.add(createFileMenu());
        tableMenuBar.add(createPreferenceMenu());
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

    private JMenu createPreferenceMenu() {
        final JMenu preferenceMenu = new JMenu("Preferences");

        final JCheckBoxMenuItem highLightCheckBoxMenuItem = new JCheckBoxMenuItem("Highlight Legal Moves", false);
        highLightCheckBoxMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Highlight Legal Moves!");
                highlightLegalMoves = highLightCheckBoxMenuItem.isSelected();

            }
        });
        preferenceMenu.add(highLightCheckBoxMenuItem);

        return preferenceMenu;
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
                                moveLog.addMove(move);
                            } else {
                                System.out.println(moveAttempt.getMoveStatus().toString());
                            }
                            System.out.println("now clicking on destination tile");
                            sourceTile = null;
                            destinationTile = null;
                            sourcePiece = null;
                        }

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                gameHistoryPanel.redo(board, moveLog);
                                takenPiecesPanel.addTakenPiece(moveLog);
                                boardPanel.drawBoard(board);
                            }
                        });
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
//                    sourcePiece = board.getTile(tileRowId, tileColumnId).getPiece();
//                    System.out.println(sourcePiece);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
//                    sourcePiece.setPosition(e.getX(), e.getY());
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
//                    if (sourcePiece != null) {
//                        int x = e.getX();
//                        int y = e.getY();
//                        sourcePiece.setPosition(new Point(x, y));
//                        repaint();
//
//                    }
//                    if (isLeftMouseButton(e)) {
//                        if (sourceTile == null) {
//                            sourceTile = board.getTile(tileRowId, tileColumnId);
//                            sourcePiece = sourceTile.getPiece();
//                            if (sourcePiece == null) {
//                                System.out.println("no piece here");
//                                sourceTile = null;
//                            } else {
//                                System.out.println(sourcePiece.toString() + " is about to move");
//                            }
//                        }
//                    }
//                    else {
//                        destinationTile = board.getTile(tileRowId, tileColumnId);
//                        Move move = MoveFactory.constructMove(board, destinationTile.getPosition(),
//                                sourceTile.getPosition());
//                        MoveAttempt moveAttempt = board.getCurrentPlayer().makeMove(move, board);
//                        if (moveAttempt.getMoveStatus() == MoveAttempt.MoveStatus.OK) {
//                            board = moveAttempt.getBoard();
//                        }
//                        else {
//                            System.out.println(moveAttempt.getMoveStatus().toString());
//                        }
//
//                        sourceTile = null;
//                        destinationTile = null;
//                        sourcePiece = null;
//                    }
                }

                @Override
                public void mouseMoved(MouseEvent e) {

                }
            });
            validate();
        }

        private void assignPanelColor() {
            if ((((tileRowId & 1) == 0 && (tileColumnId & 1) == 0) || ((tileRowId & 1) != 0 && (tileColumnId & 1) != 0))) {
                setBackground(Color.decode("#F8DE7E"));
            } else {
                setBackground(Color.decode("#7E481C"));
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

        private void highlightLegalMoves(Board board) {
            if (highlightLegalMoves) {
                for (Move move : pieceMoves(board)) {
                    if (move.getDestination().equals(getTilePosition())) {
                        Tile destinationTile = board.getTile(getTilePosition().x, getTilePosition().y);
//                        Tile destinationTile = board.getTile(move.getDestination().x, move.getDestination().y);
//                        if ((destinationTile.isOccupied() && destinationTile.getPiece().getColor() != sourcePiece.getColor())) {
//                            try {
//                                final BufferedImage image = ImageIO.read(new File("src//main//resources//misc//red_dot.png"));
//                                add(new JLabel(new ImageIcon(image)));
//                            } catch (IOException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
                        if (!destinationTile.isOccupied() && board.getCurrentPlayer().makeMove(move, board).getMoveStatus() == MoveAttempt.MoveStatus.OK) {
                            if (move instanceof CastlingMove) {
                                try {
                                    System.out.println("green dot on tile" + getTilePosition());
                                    final BufferedImage image = ImageIO.read(new File("src//main//resources//misc//blue_dot.png"));
                                    add(new JLabel(new ImageIcon(image)));
                                }
                                catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            else {
                                try {
                                    System.out.println("green dot on tile" + getTilePosition());
                                    final BufferedImage image = ImageIO.read(new File("src//main//resources//misc//green_dot.png"));
                                    add(new JLabel(new ImageIcon(image)));
                                }
                                catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }

                    }
                }
            }
        }

        private Point getTilePosition() {
            return new Point(tileRowId, tileColumnId);
        }

        private List<Move> pieceMoves(Board board) {
            List<Move> pieceMoves = new ArrayList<>();
            if (sourcePiece != null && sourcePiece.getColor() == board.getCurrentPlayer().getColor()) {
                for (Move move : board.getCurrentPlayer().getAvailableMoves()) {
                    if (move.getMovedPiece() == sourcePiece) {
                        pieceMoves.add(move);
                    }
                }


//                MoveVisitor moveVisitor = new MoveVisitorImpl();
//                pieceMoves.addAll(sourcePiece.accept(moveVisitor, board));
                if (sourcePiece instanceof King) {
                    pieceMoves.addAll(board.getCurrentPlayer().calculateCastlingMoves());
                }
            }
            return pieceMoves;
        }

        public void drawTile(Board board) {
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignPanelColor();
            assignTilePieceIcon(board);
            highlightLegalMoves(board);
            validate();
            repaint();
        }
    }
}
