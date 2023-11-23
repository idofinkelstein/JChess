package com.chess.engine.board;

import com.chess.engine.piece.Piece;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Tile {

    protected final int x;
    protected final int y;
    protected final Piece piece;

    protected Tile(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    abstract boolean isOccupied();
    abstract boolean isEmpty();
}
