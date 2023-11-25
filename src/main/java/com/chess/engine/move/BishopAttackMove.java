package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public class BishopAttackMove extends BishopMove{
    private final Piece AttackedPiece;
    public BishopAttackMove(Point source, Point destination, Piece attackedPiece) {
        super(source, destination);
        AttackedPiece = attackedPiece;
    }
}
