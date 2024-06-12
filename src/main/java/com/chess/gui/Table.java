package com.chess.gui;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.move.*;
import com.chess.engine.piece.King;
import com.chess.engine.piece.Piece;
import com.chess.engine.piece.PieceType;
import com.chess.pgn.FenUtils;
import com.chess.pgn.IOHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.chess.engine.board.Board.*;
import static com.chess.engine.board.BoardUtils.POSITIONS;
import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class Table {

    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);
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
    private PieceType promotionPiece;
    private final MoveLog moveLog;
    private static volatile Table INSTANCE;

    private Table() {
        this.moveLog = new MoveLog();
        this.board = new Board.BoardBuilder().setStartingPlayer().populateMap().build();
        this.gameFrame = new JFrame("Chess");
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public static Table getInstance() {
        if (INSTANCE == null) {
            synchronized (Table.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Table();
                }
            }
        }
        return INSTANCE;
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

        final JMenuItem loadGame = new JMenuItem("Load Game");
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Load Game");
                JFileChooser fileChooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("fen files", "fen");
                fileChooser.addChoosableFileFilter(filter);
                fileChooser.setCurrentDirectory(new java.io.File("src/main/resources/saved_games"));
                fileChooser.setDialogTitle("Choose Game");


                int ret = fileChooser.showDialog(null, "Open file");

                File file = null;
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                }
                if (file != null) {
                    try {
                        String gameText = IOHandler.loadGame(String.valueOf(file));
                        board = FenUtils.createBoardFromFen(gameText);
                        boardPanel.drawBoard(board);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        fileMenu.add(loadGame);

        final JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Save game");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String filename = sdf.format(new Date())
                        .replaceAll("\\s+", "_")
                        .replaceAll(":", "_")
                        .concat(".fen");

                Path filePath = Path.of("src/main/resources/saved_games");
                Path absolutePath = Path.of(String.valueOf(filePath), filename);
                String gameText = FenUtils.createFenFromBoard(board);

                try {
                    IOHandler.saveGame(gameText, absolutePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        fileMenu.add(saveGame);

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
        private boolean isHighlighted;


        TilePanel(final BoardPanel boardPanel, final int tileRowId, final int tileColumnId) {
            super(new GridLayout());
            this.tileRowId = tileRowId;
            this.tileColumnId = tileColumnId;
            this.isHighlighted = false;
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
                        isHighlighted = false;
                    } else if (isLeftMouseButton(e)) {
                        if (sourceTile == null) {

                            sourceTile = board.getTile(tileRowId, tileColumnId);
                            sourcePiece = sourceTile.getPiece();
                            if (sourcePiece == null) {

                                isHighlighted = false;
                                System.out.println("no piece here");
                                sourceTile = null;
                            } else {
                                isHighlighted = true;
                                System.out.println(sourcePiece + " is about to move");
                            }
                        } else if (destinationTile == null) {

                            destinationTile = board.getTile(tileRowId, tileColumnId);
                            Move move = MoveFactory.constructMove(board, destinationTile.getPosition(),
                                    sourceTile.getPosition());

                            MoveAttempt moveAttempt = board.getActivePlayer().makeMove(move, board);
                            if (moveAttempt.moveStatus() == MoveAttempt.MoveStatus.OK) {
                                if (moveAttempt.move() instanceof PawnPromotion pawnPromotion) {

                                    PieceType[] possibilities = {PieceType.QUEEN, PieceType.ROOK, PieceType.BISHOP, PieceType.KNIGHT};
                                    promotionPiece = (PieceType) JOptionPane.showInputDialog(
                                            gameFrame,
                                            "Choose piece:",
                                            "Piece Promotion",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            possibilities,
                                            PieceType.QUEEN);
                                    pawnPromotion.setPromotionType(promotionPiece);
                                    moveAttempt = board.getActivePlayer().makeMove(move, board);
                                }
                                board = moveAttempt.board();
                                moveLog.addMove(move);
                            } else {
                                System.out.println(moveAttempt.moveStatus().toString());
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
                                isHighlighted = false;
                            }
                        });
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
//                    sourcePiece = board.getTile(tileRowId, tileColumnId).getPiece();
//                    System.out.println(sourcePiece);
//
                }

                @Override
                public void mouseReleased(MouseEvent e) {
//                    sourcePiece.setPosition(new Point(e.getX(), e.getY()));
//                    SwingUtilities.invokeLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            boardPanel.drawBoard(board);
//                        }
//                    });
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
                        if (!destinationTile.isOccupied() && board.getActivePlayer().makeMove(move, board).moveStatus() == MoveAttempt.MoveStatus.OK) {
                            if (move instanceof CastlingMove) {
                                try {
                                    System.out.println("green dot on tile" + getTilePosition());
                                    final BufferedImage image = ImageIO.read(new File("src//main//resources//misc//blue_dot.png"));
                                    add(new JLabel(new ImageIcon(image)));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                try {
                                    System.out.println("green dot on tile" + getTilePosition());
                                    final BufferedImage image = ImageIO.read(new File("src//main//resources//misc//green_dot.png"));
                                    add(new JLabel(new ImageIcon(image)));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }
            }
        }

        private Point getTilePosition() {
            return POSITIONS[tileRowId][tileColumnId];
        }

        private List<Move> pieceMoves(Board board) {
            List<Move> pieceMoves = new ArrayList<>();
            if (sourcePiece != null && sourcePiece.getColor() == board.getActivePlayer().getColor()) {

                for (Move move : board.getActivePlayer().getAvailableMoves()) {
                    if (move.getMovedPiece() == sourcePiece) {
                        pieceMoves.add(move);
                    }
                }
                if (sourcePiece instanceof King) {
                    pieceMoves.addAll(board.getActivePlayer().calculateCastlingMoves());
                }
            }
            return pieceMoves;
        }

        public void drawTile(Board board) {
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignPanelColor();
            assignPanelBorder(board);
            assignTilePieceIcon(board);
            highlightLegalMoves(board);
            validate();
            repaint();
        }

        private void assignPanelBorder(Board board) {
            if (isHighlighted && board.getActivePlayer().getColor().
                    equals(board.getTile(tileRowId, tileColumnId).getPiece().getColor())) {

                this.setBorder(BorderFactory.createLineBorder(Color.cyan, 5));
                isHighlighted = false;
            } else {
                this.setBorder(null);
            }
        }
    }
}
