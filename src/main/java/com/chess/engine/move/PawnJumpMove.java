package com.chess.engine.move;

import com.chess.engine.piece.Piece;

import java.awt.*;

public class PawnJumpMove extends Move{
    public PawnJumpMove(Piece movedPiece, Point destination, Point source) {
        super(movedPiece, destination, source);
    }
}
