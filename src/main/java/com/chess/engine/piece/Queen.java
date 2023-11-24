package com.chess.engine.piece;

import com.chess.engine.board.Board;

import java.awt.*;

public class Queen extends Piece {
    public Queen(Point position, Color color){
        super(position, color);
    }

    @Override
    public void accept(MoveVisitor moveVisitor, Board board) {
        moveVisitor.visit(this, board);
    }

    @Override
    public String toString() {
        return PieceType.QUEEN.toString();
    }
}
