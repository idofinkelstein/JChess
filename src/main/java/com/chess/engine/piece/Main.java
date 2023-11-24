package com.chess.engine.piece;

import com.chess.engine.board.Board;

public class Main {
    public static void main(String[] args) {
        Board board = new Board.BoardBuilder().setStartingPlayer().populateMap().build();
        Piece knight = board.getTile(0, 1).getPiece();
        knight.accept(new MoveVisitorImpl(), board);
    }
}
