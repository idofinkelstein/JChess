package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public class PawnMove extends Move{
    public PawnMove(Piece movedPiece, Point destination, Point source) {
        super(movedPiece, destination, source);
    }
}
