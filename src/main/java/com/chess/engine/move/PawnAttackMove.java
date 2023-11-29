package com.chess.engine.move;

import com.chess.engine.piece.Piece;
import lombok.EqualsAndHashCode;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
public class PawnAttackMove extends Move{
    private final Piece attackedPiece;
    public PawnAttackMove(Piece movedPiece, Point destination, Point source, Piece attackedPiece) {
        super(movedPiece, destination, source);
        this.attackedPiece = attackedPiece;
    }
}
