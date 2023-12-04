package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;
import lombok.EqualsAndHashCode;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
public class PawnJumpMove extends Move{
    public PawnJumpMove(Board board, Piece movedPiece, Point destination, Point source) {
        super(board, movedPiece, destination, source);
    }
}
