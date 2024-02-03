package com.chess.pgn;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import com.chess.engine.move.MoveAttempt;
import com.chess.engine.move.PawnJumpMove;
import com.chess.engine.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.chess.engine.board.BoardUtils.POSITIONS;

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

}