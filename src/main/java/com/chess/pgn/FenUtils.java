package com.chess.pgn;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Color;
import com.chess.engine.piece.Pawn;
import com.chess.engine.piece.Rook;

import static com.chess.engine.board.Board.BOARD_SIZE;
import static com.chess.engine.move.MoveUtils.INDEX_TO_CHESS_LETTER_NOTATION;
import static com.chess.engine.move.MoveUtils.INDEX_TO_CHESS_NUMBER_NOTATION;

public class FenUtils {

    private FenUtils() {
        throw new RuntimeException("Don't call this!");
    }

    public static Board createBoardFromFen(String fenStr) {
        // input: RNBQKBNR/1PPPPPPP/8/P7/p7/8/1ppppppp/rnbqkbnr w KQkq a6 0 1
        FenParser fenParser = new FenParser(fenStr);
        return fenParser.constructBoard();
    }

    public static String createFenFromBoard(Board board) {
        return createBoardText(board) + " " +
                createCurrentPlayerText(board) + " " +
                createCastleText(board) + " " +
                createEnPassantText(board) + " " + "0 1";
    }

    private static String createEnPassantText(Board board) {
        Pawn pawn = board.getEnPassantPawn();
        if (pawn != null ) {
            int tileBehindEnPassant = pawn.getColor().equals(Color.WHITE) ? 1 : -1;
            return INDEX_TO_CHESS_LETTER_NOTATION.get(pawn.getPosition().y) +
                    INDEX_TO_CHESS_NUMBER_NOTATION.get(pawn.getPosition().x + tileBehindEnPassant);
        }
        return "-";
    }

    private static String createCastleText(Board board) {
        StringBuilder sb = new StringBuilder();

        if (board.getWhitePlayer().isKingSideCastleCapable()
                && board.getTile(7, 7).isOccupied()
                && board.getTile(7, 7).getPiece() instanceof Rook rook
                && rook.isFirstMove()) {
            sb.append("K");
        }
        if (board.getWhitePlayer().isQueenSideCastleCapable()
                && board.getTile(7, 0).isOccupied()
                && board.getTile(7, 0).getPiece() instanceof Rook rook
                && rook.isFirstMove()) {
            sb.append("Q");
        }
        if (board.getBlackPlayer().isKingSideCastleCapable()
                && board.getTile(0, 7).isOccupied()
                && board.getTile(0, 7).getPiece() instanceof Rook rook
                && rook.isFirstMove()) {
            sb.append("k");
        }
        if (board.getBlackPlayer().isQueenSideCastleCapable() && board.getTile(0, 0).isOccupied()
                && board.getTile(0, 0).getPiece() instanceof Rook rook
                && rook.isFirstMove()) {
            sb.append("q");
        }
        String result = sb.toString();
        return result.isEmpty() ? "-" : result;
    }

    private static String createCurrentPlayerText(Board board) {
        return board.getActivePlayer().toString().substring(0, 1).toLowerCase();
    }

    private static String createBoardText(Board board) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                String tileText = board.getTile(i, j).toString();
                text.append(tileText);
            }
        }
        text.insert(8, '/')
                .insert(17, '/')
                .insert(26, '/')
                .insert(35, '/')
                .insert(44, '/')
                .insert(53, '/')
                .insert(62, '/');

        return text.toString().replaceAll("--------", "8")
                .replaceAll("-------", "7")
                .replaceAll("------", "6")
                .replaceAll("-----", "5")
                .replaceAll("----", "4")
                .replaceAll("---", "3")
                .replaceAll("--", "2")
                .replaceAll("-", "1");
    }
}
