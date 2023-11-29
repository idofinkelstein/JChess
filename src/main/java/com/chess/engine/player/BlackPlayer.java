package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import com.chess.engine.piece.Color;
import com.chess.engine.piece.MoveVisitor;
import com.chess.engine.piece.MoveVisitorImpl;
import com.chess.engine.piece.Piece;

import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.board.Board.BOARD_SIZE;

public class BlackPlayer extends Player{
    public BlackPlayer(Board board, List<Piece> availablePieces, List<Move> availableMoves, List<Move> opponentAvailableMoves) {
        super(board, availablePieces, availableMoves, opponentAvailableMoves);
    }

    @Override
    public Player getOpponent(Board board) {
        return board.getWhitePlayer();
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }
}
