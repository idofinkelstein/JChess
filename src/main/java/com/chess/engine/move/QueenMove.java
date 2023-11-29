package com.chess.engine.move;

import com.chess.engine.piece.Piece;
import lombok.EqualsAndHashCode;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
public class QueenMove extends Move {
    public QueenMove(Piece movedPiece, Point destination, Point source) {
        super(movedPiece, destination, source);
    }
}
