package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public class KnightAttackMove extends KnightMove{

    private final Piece attackedPiece;
    public KnightAttackMove(Point source, Point destination, Piece attackedPiece) {
        super(source, destination);
        this.attackedPiece = attackedPiece;
    }
}
