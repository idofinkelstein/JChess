package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;

import java.awt.*;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Point position, Color color){
        super(position, color);
    }

    @Override
    public List<Move> accept(MoveVisitor moveVisitor, Board board) {
        return  moveVisitor.visit(this, board);
    }

    @Override
    public String toString(){
        return PieceType.PAWN.toString();
    }
}
