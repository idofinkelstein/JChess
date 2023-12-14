package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;

import java.awt.*;
import java.util.List;

public class King extends Piece {
    public King(Point position, Color color, boolean isFirstMove){
        super(position, color, isFirstMove);
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
        return PieceType.KING.toString();
    }

    public boolean isFirstMove(){
        return true;
    }

    @Override
    public Piece movePiece(Point destination) {
        return new King(destination, getColor(), false);
    }
}
