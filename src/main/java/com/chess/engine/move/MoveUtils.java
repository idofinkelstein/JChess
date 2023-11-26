package com.chess.engine.move;

import java.awt.*;

public class MoveUtils {
    public static boolean isMoveValid(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    public static final Point[] KNIGHT_POSSIBLE_MOVES = {
            new Point(1, 2),
            new Point(1, -2),
            new Point(-1, 2),
            new Point(-1, -2),
            new Point(2, 1),
            new Point(2, -1),
    };

    public static final Point[] BISHOP_POSSIBLE_MOVES = {
            new Point(1, 1),
            new Point(1, -1),
            new Point(-1, 1),
            new Point(-1, -1),
    };

    public static final Point[] ROOK_POSSIBLE_MOVES = {
            new Point(1, 0),
            new Point(-1, 0),
            new Point(0, 1),
            new Point(0, -1),
    };

    public static final Point[] QUEEN_POSSIBLE_MOVES = {
            new Point(1, 1),
            new Point(1, -1),
            new Point(-1, 1),
            new Point(-1, -1),
            new Point(1, 0),
            new Point(-1, 0),
            new Point(0, 1),
            new Point(0, -1),
    };

    public static final Point[] KING_POSSIBLE_MOVES = QUEEN_POSSIBLE_MOVES;
}