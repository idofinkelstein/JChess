package com.chess.engine.piece;

import java.awt.*;

import static com.chess.engine.board.BoardUtils.BLACK_LAST_SQUARE;
import static com.chess.engine.board.BoardUtils.WHITE_LAST_SQUARE;

public enum Color {
    WHITE{
        @Override
        public boolean isLastSquare(Point position) {
            return position.x == WHITE_LAST_SQUARE;
        }
    },
    BLACK{
        @Override
        public boolean isLastSquare(Point position) {
            return position.x == BLACK_LAST_SQUARE;
        }
    };

    public abstract boolean isLastSquare(Point position);
}
