package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;

import java.awt.*;

public class QueenSideCastling extends CastlingMove{
    public QueenSideCastling(Board board, Piece king, Piece rook, Point kingDestination, Point rookDestination) {
        super(board, king, rook, kingDestination, rookDestination);
    }
}
