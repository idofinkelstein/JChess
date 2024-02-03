package com.chess.pgn;

import com.chess.engine.board.Board;

public class FenParser {
    private final String boardText;
    private final String currentPlayerText;
    private final String availableCastlingText;
    private final String enPassantText;
    private final String movesSinceLastCaptureText;
    private final String moveCountText;

    public FenParser(String FenText) {
        String[] splited = FenText.split("\\s+");

        if (splited.length != 6) {
            throw new IllegalArgumentException("FEN text must contain 6 parts separated by spaces!");
        }
        boardText = splited[0];
        currentPlayerText = splited[1];
        availableCastlingText = splited[2];
        enPassantText = splited[3];
        movesSinceLastCaptureText = splited[4];
        moveCountText = splited[5];
    }

    public Board constructBoard() {
        // input: RNBQKBNR/1PPPPPPP/8/P7/p7/8/1ppppppp/rnbqkbnr w KQkq a6 0 1
        // to be implemented

        return null;
    }
}
