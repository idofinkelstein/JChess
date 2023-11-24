package com.chess.engine.piece;

import com.chess.engine.board.Board;

import java.awt.*;

public class Knight extends Piece {
    public Knight(Point position, Color color){
        super(position, color);
    }
    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }

    @Override
    public void accept(MoveVisitor moveVisitor, Board board) {
        moveVisitor.visit(this, board);
    }
}

