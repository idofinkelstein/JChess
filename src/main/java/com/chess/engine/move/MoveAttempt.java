package com.chess.engine.move;

import com.chess.engine.board.Board;

public record MoveAttempt(Board board, Move move, com.chess.engine.move.MoveAttempt.MoveStatus moveStatus) {

    public enum MoveStatus {
        OK,
        ILLEGAL_MOVE,
        LEAVES_KING_IN_CHECK,
    }
}
