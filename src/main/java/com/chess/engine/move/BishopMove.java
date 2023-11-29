package com.chess.engine.move;

import com.chess.engine.piece.Piece;
import lombok.EqualsAndHashCode;

import java.awt.*;

@EqualsAndHashCode(callSuper = true)
public class BishopMove extends Move {

    public BishopMove(Piece movedPiece, Point source, Point destination) {
        super(movedPiece, destination, source);
    }
}
