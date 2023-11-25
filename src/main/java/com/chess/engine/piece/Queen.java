package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;

import java.awt.*;
import java.util.List;

public class Queen extends Piece {
    public Queen(Point position, Color color){
        super(position, color);
    }

    @Override
    public List<Move> accept(MoveVisitor moveVisitor, Board board) {
        return moveVisitor.visit(this, board);
    }

    @Override
    public String toString() {
        return PieceType.QUEEN.toString();
    }
}
