package com.chess.engine.piece;

import lombok.Getter;

public enum PieceType {
    PAWN("P", 1),
    KNIGHT("N",2 ),
    BISHOP("B", 2),
    ROOK("R", 3),
    QUEEN("Q", 4),
    KING("K", 5);

    private final String symbol;
    @Getter
    private final int value;

    PieceType(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
