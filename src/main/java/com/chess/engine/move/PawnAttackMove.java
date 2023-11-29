package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public class PawnAttackMove extends Move{
    private final Piece attackedPiece;
    public PawnAttackMove(Piece movedPiece, Point destination, Point source, Piece attackedPiece) {
        super(movedPiece, destination, source);
        this.attackedPiece = attackedPiece;
    }
}
