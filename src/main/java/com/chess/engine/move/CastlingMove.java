package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;

import java.awt.*;

public abstract class CastlingMove extends Move{
    // king piece and king position are extended from base class (Move)
    protected final Piece rook;
    protected final Point rookDestination;

    public CastlingMove(Board board, Piece king, Piece rook, Point kingDestination, Point rookDestination) {
        super(board, king, kingDestination, king.getPosition());
        this.rook = rook;
        this.rookDestination = rookDestination;
    }
}
