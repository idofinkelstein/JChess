package com.chess.engine.piece;

import java.awt.*;

public class Bishop extends Piece {
    public Bishop(Point position, Color color) {
        super(position, color);
    }

    @Override
    public String toString() {
        return PieceType.BISHOP.toString();
    }
}
