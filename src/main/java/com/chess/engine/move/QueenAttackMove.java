package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public class QueenAttackMove extends QueenMove{

    private Piece AttackedPiece;
    public QueenAttackMove(Point destination, Point source, Piece attackedPiece) {
        super(destination, source);
        AttackedPiece = attackedPiece;
    }
}
