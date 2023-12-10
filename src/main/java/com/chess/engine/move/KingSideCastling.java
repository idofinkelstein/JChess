package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;

import java.awt.*;

public class KingSideCastling extends CastlingMove {

    public KingSideCastling(Board board, Piece king, Piece rook, Point kingDestination, Point rookDestination) {
        super(board,king, rook, kingDestination, rookDestination);
    }
}
