package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;
import com.chess.engine.player.Player;

import java.awt.*;
import java.util.List;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class BishopAttackMove extends AttackMove {
    public BishopAttackMove(Board board, Piece movedPiece, Point source, Point destination, Piece attackedPiece) {
        super(board, movedPiece, source, destination, attackedPiece);
    }

}
