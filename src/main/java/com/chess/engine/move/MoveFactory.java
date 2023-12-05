package com.chess.engine.move;

import com.chess.engine.board.Board;

import java.awt.*;

public class MoveFactory {

    private MoveFactory(){} // should not be instantiated

    public static Move constructMove(Board board, Point destination, Point source) {
        for (Move move : board.getAvailableMoves()) {
            if (move.getSource().equals(source) && move.getDestination().equals(destination)) {
                return move;
            }
        }
        return null;
    }
}
