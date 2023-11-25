package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;

import java.awt.*;
import java.util.List;

public class Knight extends Piece {
    public Knight(Point position, Color color){
        super(position, color);
    }
    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }

    @Override
    public List<Move> accept(MoveVisitor moveVisitor, Board board) {
        return moveVisitor.visit(this, board);
    }
}

