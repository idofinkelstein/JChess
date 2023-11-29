package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public class KnightAttackMove extends KnightMove{

    private final Piece attackedPiece;
    public KnightAttackMove(Piece movedPiece, Point destination, Point source, Piece attackedPiece) {
        super(movedPiece, destination, source);
        this.attackedPiece = attackedPiece;
    }
}
