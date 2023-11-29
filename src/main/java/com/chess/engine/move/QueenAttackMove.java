package com.chess.engine.move;

import com.chess.engine.piece.Piece;
import lombok.EqualsAndHashCode;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
public class QueenAttackMove extends QueenMove{

    private Piece AttackedPiece;
    public QueenAttackMove(Piece movedPiece, Point destination, Point source, Piece attackedPiece) {
        super(movedPiece, destination, source);
        AttackedPiece = attackedPiece;
    }
}
