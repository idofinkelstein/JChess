package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public class KingAttackMove extends KingMove {

    private final Piece attackedPiece;
    public KingAttackMove(Point destination, Point source, Piece attackedPiece) {
        super(destination, source);
        this.attackedPiece = attackedPiece;
    }
}
