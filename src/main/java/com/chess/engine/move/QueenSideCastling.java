package com.chess.engine.move;

import com.chess.engine.piece.Piece;

public class QueenSideCastling extends CastlingMove{
    public QueenSideCastling(Piece king, Piece rook) {
        super(king, rook);
    }
}
