package com.chess.engine.board;

public class Main {
    public static void main(String[] args) {

        Board board = new Board.BoardBuilder().setStartingPlayer().populateMap().build();
        System.out.println(board);


    }
}
