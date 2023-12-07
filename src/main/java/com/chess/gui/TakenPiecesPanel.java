package com.chess.gui;

import com.chess.engine.move.Move;
import com.chess.engine.piece.Piece;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TakenPiecesPanel extends JPanel {

    private final JPanel upperBorder;
    private final JPanel lowerBorder;

    private static final EtchedBorder PANEL_BORDER = new EtchedBorder(EtchedBorder.RAISED);
    private static final Dimension TAKEN_PIECES_DIMENSION = new Dimension(40, 80);
    public TakenPiecesPanel() {
        super(new BorderLayout());
        setBackground(Color.DARK_GRAY);
        setBorder(PANEL_BORDER);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        upperBorder = new JPanel(new GridLayout(8, 2));
        lowerBorder = new JPanel(new GridLayout(8, 2));
        upperBorder.setBackground(Color.decode("#FCF4A3"));
        lowerBorder.setBackground(Color.decode("#FCF4A3"));
        add(upperBorder, BorderLayout.NORTH);
        add(lowerBorder, BorderLayout.SOUTH);
        setPreferredSize(TAKEN_PIECES_DIMENSION);
    }

    public void addTakenPiece(MoveLog moveLog) {
        upperBorder.removeAll();
        lowerBorder.removeAll();

        List<Move> blackTakenPieces = new ArrayList<>();
        List<Move> whiteTakenPieces = new ArrayList<>();

        for (Move move : moveLog.getMoves()) {
            if (move.getAttackedPiece() != null) {
                if (move.getAttackedPiece().getColor() == com.chess.engine.piece.Color.BLACK) {
                    blackTakenPieces.add(move);
                } else {
                    whiteTakenPieces.add(move);
                }
            }
        }

        blackTakenPieces.sort(Comparator.comparingInt(move -> move.getMovedPiece().getPieceValue()));
        whiteTakenPieces.sort(Comparator.comparingInt(move -> move.getMovedPiece().getPieceValue()));

        addTakenPiecesToPanel(blackTakenPieces, upperBorder);
        addTakenPiecesToPanel(whiteTakenPieces, lowerBorder);

        validate();
    }

    private static void addTakenPiecesToPanel(List<Move> blackTakenPieces, JPanel upperBorder) {
        for (Move move : blackTakenPieces) {
            upperBorder.add(new JLabel(move.getAttackedPiece().toString()));
        }
    }
}
