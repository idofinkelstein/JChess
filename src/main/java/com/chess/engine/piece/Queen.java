package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;

import java.awt.*;
import java.util.List;

public class Queen extends Piece {
    public Queen(Point position, Color color, boolean isFirstMove){
        super(position, color, isFirstMove);
    }

    @Override
    public List<Move> accept(MoveVisitor moveVisitor, Board board) {
        return moveVisitor.visit(this, board);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }

    @Override
    public Piece movePiece(Point destination) {
        return new Queen(destination, getColor(), false);
    }

    @Override
    public String toString() {
        return color.equals(Color.WHITE) ? PieceType.QUEEN.toString() : PieceType.QUEEN.toString().toLowerCase();
    }

}
