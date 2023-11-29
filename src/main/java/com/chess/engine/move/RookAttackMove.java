package com.chess.engine.move;

import com.chess.engine.piece.Piece;
import lombok.EqualsAndHashCode;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
public class RookAttackMove extends RookMove {
    private final Piece AttackedPiece;
    public RookAttackMove(Piece movedPiece, Point source, Point destination, Piece attackedPiece) {
        super(movedPiece, source, destination);
        AttackedPiece = attackedPiece;
    }
}
