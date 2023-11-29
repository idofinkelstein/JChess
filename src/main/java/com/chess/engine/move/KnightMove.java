package com.chess.engine.move;

import com.chess.engine.piece.Piece;
import lombok.EqualsAndHashCode;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
public class KnightMove extends Move{

    public KnightMove(Piece movedPiece, Point destination, Point source) {
        super(movedPiece, destination, source);
    }
}
