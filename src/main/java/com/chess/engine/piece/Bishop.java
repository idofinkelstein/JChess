package com.chess.engine.piece;

import java.awt.*;

public class Bishop extends Piece {
    public Bishop(Point position, Color color) {
        super(position, color);
    }

    @Override
    public String toString() {
        return (color == Color.BLACK) ? PieceType.BISHOP.toString() : PieceType.BISHOP.toString().toLowerCase();
    }
}
