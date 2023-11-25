package com.chess.engine.move;

import java.awt.*;

public abstract class Move {
    private final Point destination;
    private final Point source;

    protected Move(Point destination, Point source) {
        this.destination = destination;
        this.source = source;
    }
}
