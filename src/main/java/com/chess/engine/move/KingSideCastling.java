package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;
import com.chess.engine.player.Player;

import java.awt.*;
import java.util.List;

public class KingSideCastling extends CastlingMove {

    public KingSideCastling(Board board, Piece king, Piece rook, Point kingDestination, Point rookDestination) {
        super(board, king, rook, kingDestination, rookDestination);
    }

    @Override
    public String toString() {
        return "O-O";
    }
}
