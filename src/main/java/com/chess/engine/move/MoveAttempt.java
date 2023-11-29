package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;

public class MoveAttempt {
    private final Board board;
    private final Piece MovedPiece;
    private final Piece CapturedPiece;

    public MoveAttempt(Board board, Piece MovedPiece, Piece CapturedPiece) {
        this.board = board;
        this.MovedPiece = MovedPiece;
        this.CapturedPiece = CapturedPiece;
    }

}
