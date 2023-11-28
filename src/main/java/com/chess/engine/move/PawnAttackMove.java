package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public class PawnAttackMove extends Move{
    private final Piece attackedPiece;
    public PawnAttackMove(Point destination, Point source, Piece attackedPiece) {
        super(destination, source);
        this.attackedPiece = attackedPiece;
    }
}
