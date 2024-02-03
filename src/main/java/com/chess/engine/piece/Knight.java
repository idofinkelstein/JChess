package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;

import java.awt.*;
import java.util.List;

public class Knight extends Piece {
    public Knight(Point position, Color color, boolean isFirstMove){
        super(position, color, isFirstMove);
    }
    @Override
    public String toString() {
        return color.equals(Color.WHITE) ? PieceType.KNIGHT.toString() : PieceType.KNIGHT.toString().toLowerCase();
    }

    @Override
    public List<Move> accept(MoveVisitor moveVisitor, Board board) {
        return moveVisitor.visit(this, board);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }

    @Override
    public Piece movePiece(Point destination) {
        return new Knight(destination, getColor(), false);
    }
}

