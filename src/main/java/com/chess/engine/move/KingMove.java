package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public class KingMove extends Move {
    public KingMove(Piece movedPiece, Point destination, Point source) {
        super(movedPiece, destination, source);
    }
}
