package com.chess.engine.piece;

import java.awt.*;

public class Rook extends Piece{

    private final PieceType pieceType;
    public Rook(Point position, Color color) {
        super(position, color);
        this.pieceType = PieceType.ROOK;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public String toString() {
        return PieceType.ROOK.toString();
    }
}
