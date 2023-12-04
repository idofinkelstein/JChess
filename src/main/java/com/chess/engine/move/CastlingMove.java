package com.chess.engine.move;

import com.chess.engine.piece.Piece;

public abstract class CastlingMove {
    private final Piece king;
    private final Piece rook;

    public CastlingMove(Piece king, Piece rook) {
        this.king = king;
        this.rook = rook;
    }
}
