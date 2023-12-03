package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;
import lombok.EqualsAndHashCode;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
public class KingAttackMove extends AttackMove {

    public KingAttackMove(Board board, Piece movedPiece, Point destination, Point source, Piece attackedPiece) {
        super(board, movedPiece, destination, source, attackedPiece);
    }
}
