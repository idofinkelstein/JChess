package com.chess.engine.piece;

import lombok.Getter;

import java.awt.*;

@Getter
public abstract class Piece {

    protected final Point position;
    protected final Color color;

    protected Piece(Point position, Color color) {
        this.position = position;
        this.color = color;
    }

}
