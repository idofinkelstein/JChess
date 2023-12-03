package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import com.chess.engine.piece.Color;
import com.chess.engine.piece.Piece;

import java.util.List;

public class WhitePlayer extends Player {
    public WhitePlayer(Board board,
                       List<Piece> availablePieces,
                       List<Move> availableMoves,
                       List<Move> opponentAvailableMoves) {
        super(board, availablePieces, availableMoves, opponentAvailableMoves);
    }

    @Override
    public Player getOpponent() {
        return board.getBlackPlayer();
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }
}
