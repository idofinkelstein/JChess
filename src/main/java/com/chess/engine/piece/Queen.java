package com.chess.engine.piece;

import java.awt.*;

public class Queen extends Piece {
    public Queen(Point position, Color color){
        super(position, color);
    }

    @Override
    public String toString() {
        return PieceType.QUEEN.toString();
    }
}
