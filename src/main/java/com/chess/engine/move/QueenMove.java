package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public class QueenMove extends Move {
    public QueenMove(Piece movedPiece, Point destination, Point source) {
        super(movedPiece, destination, source);
    }
}
