package com.chess.pgn;

import com.chess.engine.board.Board;
import com.chess.engine.piece.*;

import static com.chess.engine.board.Board.BOARD_SIZE;
import static com.chess.engine.board.BoardUtils.POSITIONS;

public class FenParser {
    private final String boardText;
    private final String currentPlayerText;
    private final String availableCastlingText;
    private final String enPassantText;
    private final String movesSinceLastCaptureText;
    private final String moveCountText;
    private boolean isWhiteKingSideRookMove;
    private boolean isWhiteQueenSideRookMove;
    private boolean isBlackKingSideRookMove;
    private boolean isBlackQueenSideRookMove;

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
        parseAvailableCastlingText();
    }

    public Board constructBoard() {
        // input: RNBQKBNR/1PPPPPPP/8/P7/p7/8/1ppppppp/rnbqkbnr w KQkq a6 0 1
        // to be implemented
        Board.BoardBuilder builder = parseBoardText();
        builder = parseCurrentPlayerText(builder);

        return builder.build();
    }

    private Board.BoardBuilder parseBoardText() {
        String boardLayout = boardText.replaceAll("8", "--------")
                .replaceAll("7", "-------")
                .replaceAll("6", "------")
                .replaceAll("5", "-----")
                .replaceAll("4", "----")
                .replaceAll("3", "---")
                .replaceAll("2", "--")
                .replaceAll("1", "-")
                .replaceAll("/", "");

        Board.BoardBuilder builder = new Board.BoardBuilder();
        int k = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                char c = boardLayout.charAt(k);
                switch (c) {
                    case 'p' -> {
                        boolean isFirstMove = false;
                        if (i == 1) {
                            isFirstMove = true;
                        }
                        builder.placePiece(new Pawn(POSITIONS[i][j], Color.BLACK, isFirstMove));
                    }
                    case 'n' -> builder.placePiece(new Knight(POSITIONS[i][j], Color.BLACK, false));
                    case 'b' -> builder.placePiece(new Bishop(POSITIONS[i][j], Color.BLACK, false));
                    case 'r' -> {
                        boolean isFirstMove = false;
                        if ((i == 0 && j == 0 && isBlackQueenSideRookMove) || (i == 0 && j == 7 && isBlackKingSideRookMove)) {
                            isFirstMove = true;
                        }
                        builder.placePiece(new Rook(POSITIONS[i][j], Color.BLACK, isFirstMove));
                    }
                    case 'q' -> builder.placePiece(new Queen(POSITIONS[i][j], Color.BLACK, false));
                    case 'k' -> {
                        boolean isFirstMove = false;
                        if (isBlackKingSideRookMove || isBlackQueenSideRookMove) {
                            isFirstMove = true;
                        }
                        builder.placePiece(new King(POSITIONS[i][j], Color.BLACK, isFirstMove, isBlackKingSideRookMove, isBlackQueenSideRookMove));
                    }
                    case 'P' -> {
                        boolean isFirstMove = false;
                        if (i == 6) {
                            isFirstMove = true;
                        }
                        builder.placePiece(new Pawn(POSITIONS[i][j], Color.WHITE, isFirstMove));
                    }
                    case 'N' -> builder.placePiece(new Knight(POSITIONS[i][j], Color.WHITE, false));
                    case 'B' -> builder.placePiece(new Bishop(POSITIONS[i][j], Color.WHITE, false));
                    case 'R' -> {
                        boolean isFirstMove = false;
                        if ((i == 7 && j == 0 && isWhiteQueenSideRookMove) || (i == 7 && j == 7 && isWhiteKingSideRookMove)) {
                            isFirstMove = true;
                        }
                        builder.placePiece(new Rook(POSITIONS[i][j], Color.WHITE, isFirstMove));
                    }
                    case 'Q' -> builder.placePiece(new Queen(POSITIONS[i][j], Color.WHITE, false));
                    case 'K' -> {
                        boolean isFirstMove = false;
                        if (isWhiteKingSideRookMove && isWhiteQueenSideRookMove) {
                            isFirstMove = true;
                        }
                        builder.placePiece(new King(POSITIONS[i][j], Color.WHITE, isFirstMove, isWhiteKingSideRookMove, isWhiteQueenSideRookMove));
                    }
                }
                k++;
            }
        }
        return builder;
    }

    private Board.BoardBuilder parseCurrentPlayerText(Board.BoardBuilder builder) {
        Color currentPlayerColor = currentPlayerText.equalsIgnoreCase("w")
                ? Color.WHITE
                : Color.BLACK;
        return builder.setActivePlayer(currentPlayerColor);
    }

    private void parseAvailableCastlingText() {
        for (int i = 0; i < availableCastlingText.length(); i++) {
            if (availableCastlingText.charAt(i) == 'K') {
                isWhiteKingSideRookMove = true;
            } else if (availableCastlingText.charAt(i) == 'Q') {
                isWhiteQueenSideRookMove = true;
            } else if (availableCastlingText.charAt(i) == 'k') {
                isBlackKingSideRookMove = true;
            } else if (availableCastlingText.charAt(i) == 'q') {
                isBlackQueenSideRookMove = true;
            }
        }

    }
}
