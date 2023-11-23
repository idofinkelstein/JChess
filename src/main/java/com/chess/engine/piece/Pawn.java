package com.chess.engine.piece;

import lombok.Getter;

import java.awt.*;

public class Pawn extends Piece {
    public Pawn(Point position, Color color){
        super(position, color);
    }

    @Override
    public String toString(){
        return PieceType.PAWN.toString();
    }
}
