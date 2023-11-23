package com.chess.engine.piece;

import java.awt.*;

public class Knight extends Piece {
    public Knight(Point position, Color color){
        super(position, color);
    }
    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }
}

