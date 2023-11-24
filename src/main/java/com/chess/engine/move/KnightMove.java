package com.chess.engine.move;

import java.awt.*;

public class KnightMove extends Move{
    private final Point destination;
    private final Point source;

    public KnightMove(Point source, Point destination) {
        this.source = source;
        this.destination = destination;
    }
}
