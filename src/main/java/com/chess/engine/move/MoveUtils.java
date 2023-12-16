package com.chess.engine.move;

import java.awt.*;

public class MoveUtils {

    public static final int WHITE_PAWN_STARTING_ROW = 6;
    public static final int BLACK_PAWN_STARTING_ROW = 1;
    public static boolean isMoveValid(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    public static final Point[] KNIGHT_POSSIBLE_MOVES = {
            new Point(-2, 1),
            new Point(-1, 2),
            new Point(1, 2),
            new Point(2, 1),
            new Point(2, -1),
            new Point(1, -2),
            new Point(-1, -2),
            new Point(-2, -1),
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

    public static final Point[] WHITE_PAWN_POSSIBLE_MOVES = {
            new Point(-1, 0),
    };

    public static final Point[] WHITE_PAWN_POSSIBLE_JUMP_MOVES = {
            new Point(-2, 0),
    };

    public static final Point[] WHITE_PAWN_POSSIBLE_ATTACK_MOVES = {
            new Point(-1, -1),
            new Point(-1, 1),
    };

    public static final Point[] BLACK_PAWN_POSSIBLE_MOVES = {
            new Point(1, 0),
    };

    public static final Point[] BLACK_PAWN_POSSIBLE_JUMP_MOVES = {
            new Point(2, 0),
    };

    public static final Point[] BLACK_PAWN_POSSIBLE_ATTACK_MOVES = {
            new Point(1, -1),
            new Point(1, 1),
    };
    public static final Point[] PAWN_POSSIBLE_EN_PASSANT_MOVES = {
            new Point(0, -1),
            new Point(0, 1),
    };

    public static final Point[] KING_POSSIBLE_MOVES = QUEEN_POSSIBLE_MOVES;

    public static final Point[] WHITE_KING_SIDE_CASTLE_MOVE_TILES = {
            new Point(7, 4),
            new Point(7, 5),
            new Point(7, 6),
            new Point(7, 7),
    };

    public static final Point[] WHITE_QUEEN_SIDE_CASTLE_MOVE_TILES = {
            new Point(7, 4),
            new Point(7, 3),
            new Point(7, 2),
            new Point(7, 1),
            new Point(7, 0),
    };

    public static final Point[] BLACK_KING_SIDE_CASTLE_MOVE_TILES = {
            new Point(0, 4),
            new Point(0, 5),
            new Point(0, 6),
            new Point(0, 7),
    };

    public static final Point[] BLACK_QUEEN_SIDE_CASTLE_MOVE_TILES = {
            new Point(0, 4),
            new Point(0, 3),
            new Point(0, 2),
            new Point(0, 1),
            new Point(0, 0),
    };
}
