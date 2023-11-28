package com.chess.engine.move;

import com.chess.engine.piece.Piece;

public class KingSideCastling extends CastlingMove {

    public KingSideCastling(Piece king, Piece rook) {
        super(king, rook);
    }
}
