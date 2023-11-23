package com.chess.engine.piece;

import java.awt.*;

public class King extends Piece {
    public King(Point position, Color color){
        super(position, color);
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }
}
