package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public class KingAttackMove extends KingMove {

    private final Piece attackedPiece;
    public KingAttackMove(Piece movedPiece, Point destination, Point source, Piece attackedPiece) {
        super(movedPiece, destination, source);
        this.attackedPiece = attackedPiece;
    }
}
