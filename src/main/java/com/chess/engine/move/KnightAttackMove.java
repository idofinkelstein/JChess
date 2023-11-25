package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public class KnightAttackMove extends KnightMove{

    private final Piece attackedPiece;
    public KnightAttackMove(Point destination, Point source, Piece attackedPiece) {
        super(destination, source);
        this.attackedPiece = attackedPiece;
    }
}
