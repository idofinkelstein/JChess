package com.chess.engine.piece;

import lombok.Getter;

import java.awt.*;

@Getter
public abstract class Piece {

    protected final Point position;

    protected Piece(Point position) {
        this.position = position;
    }

}
