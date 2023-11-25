package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public class RookAttackMove extends RookMove {
    private final Piece AttackedPiece;
    public RookAttackMove(Point source, Point destination, Piece attackedPiece) {
        super(source, destination);
        AttackedPiece = attackedPiece;
    }
}
