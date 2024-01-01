package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import com.chess.engine.move.MoveAttempt;
import com.chess.engine.move.PawnJumpMove;
import com.chess.engine.move.PawnMove;
import com.chess.engine.piece.Color;
import org.junit.jupiter.api.Test;

import static com.chess.engine.board.BoardUtils.POSITIONS;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    public void testNextMoveMaker() {
        // Test black as next player
        Board board = new Board.BoardBuilder().setStartingPlayer().populateMap().build();
        Player whitePlayer = board.getWhitePlayer();
        Move move = new PawnMove(board, board.getTile(6, 0).getPiece(), POSITIONS[5][0], POSITIONS[6][0]);
        MoveAttempt moveAttempt = whitePlayer.makeMove(move, board);
        board = moveAttempt.board();
        System.out.println(board);
        Player blackPlayer = board.getActivePlayer();
        assertEquals(Color.BLACK, blackPlayer.getColor());
        // Test white as next player
        move = new PawnJumpMove(board, board.getTile(1, 1).getPiece(), POSITIONS[3][1], POSITIONS[1][1]);
        moveAttempt = blackPlayer.makeMove(move, board);
        board = moveAttempt.board();
        System.out.println(board);
        assertEquals(Color.WHITE, whitePlayer.getColor());
    }

}