package com.chess.engine.board;

import java.awt.*;

import static com.chess.engine.board.Board.BOARD_SIZE;

public class BoardUtils {

    public static final Point[][] POSITIONS = initializePositions();

    private static Point[][] initializePositions() {
        Point[][] positions = new Point[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                positions[i][j] = new Point(i, j);
            }
        }
        return positions;
    }
}
