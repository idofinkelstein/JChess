package com.chess.engine.board;

import com.chess.engine.piece.Piece;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public abstract class Tile {


    protected final Point position;
    protected final Piece piece;

    protected Tile(int x, int y, Piece piece) {
        this.position = new Point(x, y);
        this.piece = piece;
    }

    public int getXPosition() {
        return position.x;
    }

    public int getYPosition() {
        return position.y;
    }
    abstract boolean isOccupied();
    abstract boolean isEmpty();
}
