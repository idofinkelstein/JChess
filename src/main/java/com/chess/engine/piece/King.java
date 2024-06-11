package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import lombok.Getter;

import java.awt.*;
import java.util.List;

@Getter
public class King extends Piece {
    private final boolean isKingSideCapable;
    private final boolean isQueenSideCapable;
    public King(Point position, Color color, boolean isFirstMove, boolean isKingSideCapable, boolean isQueenSideCapable){
        super(position, color, isFirstMove);
        this.isKingSideCapable = isKingSideCapable;
        this.isQueenSideCapable = isQueenSideCapable;
    }

    @Override
    public List<Move> accept(MoveVisitor moveVisitor, Board board){
        return moveVisitor.visit(this, board);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }

    @Override
    public String toString() {
        return color.equals(Color.WHITE) ? PieceType.KING.toString() : PieceType.KING.toString().toLowerCase();
    }

    @Override
    public Piece movePiece(Point destination) {
        return new King(destination, getColor(), false, false, false);
    }
}
