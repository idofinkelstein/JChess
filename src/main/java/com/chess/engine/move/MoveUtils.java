package com.chess.engine.move;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

    public static final Map<Integer, String> INDEX_TO_CHESS_LETTER_NOTATION = initializeIndexToChessLetterNotation();
    public static final Map<Integer, String> INDEX_TO_CHESS_NUMBER_NOTATION = initializeIndexToChessNumberNotation();
    public static final Map<String, Integer> CHESS_LETTER_NOTATION_TO_INDEX = initializeLetterNotationToIndex();
    public static final Map<String, Integer> CHESS_NUMBER_NOTATION_TO_INDEX = initializeNumberNotationToIndex();

    private static Map<String, Integer> initializeLetterNotationToIndex() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 0);
        map.put("b", 1);
        map.put("c", 2);
        map.put("d", 3);
        map.put("e", 4);
        map.put("f", 5);
        map.put("g", 6);
        map.put("h", 7);
        return map;
    }


    private static Map<String, Integer> initializeNumberNotationToIndex() {
        Map<String, Integer> map = new HashMap<>();
        map.put("8", 0);
        map.put("7", 1);
        map.put("6", 2);
        map.put("5", 3);
        map.put("4", 4);
        map.put("3", 5);
        map.put("2", 6);
        map.put("1", 7);
        return map;

    }

    private static Map<Integer, String> initializeIndexToChessNumberNotation() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "8");
        map.put(1, "7");
        map.put(2, "6");
        map.put(3, "5");
        map.put(4, "4");
        map.put(5, "3");
        map.put(6, "2");
        map.put(7, "1");
        return map;
    }

    private static Map<Integer, String> initializeIndexToChessLetterNotation() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "a");
        map.put(1, "b");
        map.put(2, "c");
        map.put(3, "d");
        map.put(4, "e");
        map.put(5, "f");
        map.put(6, "g");
        map.put(7, "h");
        return map;
    }
}
