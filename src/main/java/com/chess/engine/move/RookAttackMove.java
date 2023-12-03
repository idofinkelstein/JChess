package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;
import lombok.EqualsAndHashCode;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
public class RookAttackMove extends AttackMove {
    public RookAttackMove(Board board, Piece movedPiece, Point source, Point destination, Piece attackedPiece) {
        super(board, movedPiece, source, destination, attackedPiece);
    }
}
