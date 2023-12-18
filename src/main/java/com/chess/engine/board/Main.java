package com.chess.engine.board;

import com.chess.engine.move.Move;
import com.chess.engine.move.MoveAttempt;
import com.chess.engine.move.PawnJumpMove;
import com.chess.engine.player.Player;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Board board = new Board.BoardBuilder().setStartingPlayer().populateMap().build();
        System.out.println(board);

        Move move = new PawnJumpMove(board, board.getTile(6, 0).getPiece(), new Point(4, 0), new Point(6, 0));

        Player activePlayer = board.getActivePlayer();
        MoveAttempt moveAttempt = activePlayer.makeMove(move, board);
        Board transitionBoard = moveAttempt.getBoard();

        System.out.println(transitionBoard);





    }
}
