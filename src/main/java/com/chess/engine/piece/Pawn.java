package com.chess.engine.piece;

import com.chess.engine.board.Board;
import lombok.Getter;

import java.awt.*;

public class Pawn extends Piece {
    public Pawn(Point position, Color color){
        super(position, color);
    }

    @Override
    public void accept(MoveVisitor moveVisitor, Board board) {
        moveVisitor.visit(this, board);
    }

    @Override
    public String toString(){
        return PieceType.PAWN.toString();
    }
}
