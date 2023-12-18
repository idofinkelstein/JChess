package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;

import java.awt.*;

public class PawnPromotion extends PawnMove{

    public PawnPromotion(Board board, Piece movedPiece, Point destination, Point source) {
        super(board, movedPiece, destination, source);
    }
}
