package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;
import lombok.Getter;

public class MoveAttempt {

    @Getter
    private final Board board;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveAttempt(Board board, Move move, MoveStatus moveStatus) {
        this.board = board;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public enum MoveStatus {
        OK,
        ILLEGAL_MOVE,
        LEAVES_KING_IN_CHECK,
    }
}
