package com.chess.pgn;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import com.chess.engine.move.MoveAttempt;
import com.chess.engine.move.PawnJumpMove;
import com.chess.engine.piece.*;
import com.chess.engine.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.chess.engine.board.BoardUtils.POSITIONS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FenUtilsTest {

    @Test
    public void testStandardBoardToFen() {
        Board standardBoard = new Board.BoardBuilder().setStartingPlayer().populateMap().build();
        String boardText = FenUtils.createGameFromBoard(standardBoard);
        String expectedFenNotation = "RNBQKBNR/PPPPPPPP/8/8/8/8/pppppppp/rnbqkbnr w KQkq - 0 1";
        Assertions.assertEquals(expectedFenNotation, boardText);
    }

    @Test
    public void testWhiteEnPassantBoardToFen() {
        Board board = new Board.BoardBuilder().setStartingPlayer().populateMap().build();
        // white move
        Player whitePlayer = board.getWhitePlayer();
        Move move = new PawnJumpMove(board, board.getTile(6, 0).getPiece(), POSITIONS[4][0], POSITIONS[6][0]);
        MoveAttempt moveAttempt = whitePlayer.makeMove(move, board);
        board = moveAttempt.board();

        String boardText = FenUtils.createGameFromBoard(board);
        String expectedFenNotation = "RNBQKBNR/1PPPPPPP/8/P7/8/8/pppppppp/rnbqkbnr b KQkq a3 0 1";
        Assertions.assertEquals(expectedFenNotation, boardText);
    }

    @Test
    public void testBlackEnPassantBoardToFen() {
        Board board = new Board.BoardBuilder().setStartingPlayer().populateMap().build();

        // white move
        Player whitePlayer = board.getWhitePlayer();
        Move whiteMove = new PawnJumpMove(board, board.getTile(6, 0).getPiece(), POSITIONS[4][0], POSITIONS[6][0]);
        MoveAttempt WhiteMoveAttempt = whitePlayer.makeMove(whiteMove, board);
        board = WhiteMoveAttempt.board();

        // black move
        Player blackPlayer = board.getBlackPlayer();
        Move blackMove = new PawnJumpMove(board, board.getTile(1, 0).getPiece(), POSITIONS[3][0], POSITIONS[1][0]);
        MoveAttempt blackMoveAttempt = blackPlayer.makeMove(blackMove, board);
        board = blackMoveAttempt.board();

        String boardText = FenUtils.createGameFromBoard(board);
        String expectedFenNotation = "RNBQKBNR/1PPPPPPP/8/P7/p7/8/1ppppppp/rnbqkbnr w KQkq a6 0 1";
        Assertions.assertEquals(expectedFenNotation, boardText);
    }

    @Test
    public void testFenToBoard_standardBoard() {
        String standardBoardText = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        Board standardBoard = FenUtils.createGameFromFen(standardBoardText);

        System.out.println(standardBoard);

        // Test all black pieces are in place
        // Don't care about pieces' first move except king, rook and pawn
        assertEquals(new Rook(POSITIONS[0][0], Color.BLACK, true), standardBoard.getTile(0, 0).getPiece());
        assertEquals(new Knight(POSITIONS[0][1], Color.BLACK, false), standardBoard.getTile(0, 1).getPiece());
        assertEquals(new Bishop(POSITIONS[0][2], Color.BLACK, false), standardBoard.getTile(0, 2).getPiece());
        assertEquals(new Queen(POSITIONS[0][3], Color.BLACK, false), standardBoard.getTile(0, 3).getPiece());
        assertEquals(new King(POSITIONS[0][4], Color.BLACK, true, true, true), standardBoard.getTile(0, 4).getPiece());
        assertEquals(new Bishop(POSITIONS[0][5], Color.BLACK, false), standardBoard.getTile(0, 5).getPiece());
        assertEquals(new Knight(POSITIONS[0][6], Color.BLACK, false), standardBoard.getTile(0, 6).getPiece());
        assertEquals(new Rook(POSITIONS[0][7], Color.BLACK, true), standardBoard.getTile(0, 7).getPiece());
        assertEquals(new Pawn(POSITIONS[1][0], Color.BLACK, true), standardBoard.getTile(1, 0).getPiece());
        assertEquals(new Pawn(POSITIONS[1][1], Color.BLACK, true), standardBoard.getTile(1, 1).getPiece());
        assertEquals(new Pawn(POSITIONS[1][2], Color.BLACK, true), standardBoard.getTile(1, 2).getPiece());
        assertEquals(new Pawn(POSITIONS[1][3], Color.BLACK, true), standardBoard.getTile(1, 3).getPiece());
        assertEquals(new Pawn(POSITIONS[1][4], Color.BLACK, true), standardBoard.getTile(1, 4).getPiece());
        assertEquals(new Pawn(POSITIONS[1][5], Color.BLACK, true), standardBoard.getTile(1, 5).getPiece());
        assertEquals(new Pawn(POSITIONS[1][6], Color.BLACK, true), standardBoard.getTile(1, 6).getPiece());
        assertEquals(new Pawn(POSITIONS[1][7], Color.BLACK, true), standardBoard.getTile(1, 7).getPiece());
        // Test all white pieces are in place
        assertEquals(new Pawn(POSITIONS[6][0], Color.WHITE, true), standardBoard.getTile(6, 0).getPiece());
        assertEquals(new Pawn(POSITIONS[6][1], Color.WHITE, true), standardBoard.getTile(6, 1).getPiece());
        assertEquals(new Pawn(POSITIONS[6][2], Color.WHITE, true), standardBoard.getTile(6, 2).getPiece());
        assertEquals(new Pawn(POSITIONS[6][3], Color.WHITE, true), standardBoard.getTile(6, 3).getPiece());
        assertEquals(new Pawn(POSITIONS[6][4], Color.WHITE, true), standardBoard.getTile(6, 4).getPiece());
        assertEquals(new Pawn(POSITIONS[6][5], Color.WHITE, true), standardBoard.getTile(6, 5).getPiece());
        assertEquals(new Pawn(POSITIONS[6][6], Color.WHITE, true), standardBoard.getTile(6, 6).getPiece());
        assertEquals(new Pawn(POSITIONS[6][7], Color.WHITE, true), standardBoard.getTile(6, 7).getPiece());
        assertEquals(new Rook(POSITIONS[7][0], Color.WHITE, true), standardBoard.getTile(7, 0).getPiece());
        assertEquals(new Knight(POSITIONS[7][1], Color.WHITE, false), standardBoard.getTile(7, 1).getPiece());
        assertEquals(new Bishop(POSITIONS[7][2], Color.WHITE, false), standardBoard.getTile(7, 2).getPiece());
        assertEquals(new Queen(POSITIONS[7][3], Color.WHITE, false), standardBoard.getTile(7, 3).getPiece());
        assertEquals(new King(POSITIONS[7][4], Color.WHITE, true, true, true), standardBoard.getTile(7, 4).getPiece());
        assertEquals(new Bishop(POSITIONS[7][5], Color.WHITE, false), standardBoard.getTile(7, 5).getPiece());
        assertEquals(new Knight(POSITIONS[7][6], Color.WHITE, false), standardBoard.getTile(7, 6).getPiece());
        assertEquals(new Rook(POSITIONS[7][7], Color.WHITE, true), standardBoard.getTile(7, 7).getPiece());

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                assertFalse(standardBoard.getTile(i, j).isOccupied());
            }
        }
        assertEquals(Color.WHITE, standardBoard.getActivePlayer().getColor());
        assertEquals(Color.BLACK, standardBoard.getActivePlayer().getOpponent().getColor());
        assertFalse(standardBoard.getActivePlayer().isInCheck());
        assertFalse(standardBoard.getActivePlayer().isInCheckMate());
        assertFalse(standardBoard.getActivePlayer().isInStalemate());
        assertFalse(standardBoard.getActivePlayer().getOpponent().isInCheck());
        assertFalse(standardBoard.getActivePlayer().getOpponent().isInCheckMate());
        assertFalse(standardBoard.getActivePlayer().getOpponent().isInStalemate());
    }

}