package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;

import java.awt.*;
import java.util.List;

public class Rook extends Piece{

    public Rook(Point position, Color color, boolean isFirstMove) {
        super(position, color, isFirstMove);
    }

    @Override
    public List<Move> accept(MoveVisitor moveVisitor, Board board) {
        return moveVisitor.visit(this, board);
    }

    public PieceType getPieceType() {
        return PieceType.ROOK;
    }

    @Override
    public String toString() {
        return color.equals(Color.WHITE) ? PieceType.ROOK.toString() : PieceType.ROOK.toString().toLowerCase();
    }

    @Override
    public Piece movePiece(Point destination) {
        return new Rook(destination, getColor(), false);
    }
}
