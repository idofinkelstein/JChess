package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public abstract class Move {
    private final Piece movedPiece;
    private final Point destination;
    private final Point source;

    protected Move(Piece movedPiece, Point destination, Point source) {
        this.movedPiece = movedPiece;
        this.destination = destination;
        this.source = source;
    }

    public Move makeMove() {
        return null;
    }
}
