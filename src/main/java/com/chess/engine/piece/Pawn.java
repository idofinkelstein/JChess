package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;

import java.awt.*;
import java.util.List;

import static com.chess.engine.move.MoveUtils.BLACK_PAWN_STARTING_ROW;
import static com.chess.engine.move.MoveUtils.WHITE_PAWN_STARTING_ROW;

public class Pawn extends Piece {
    public Pawn(Point position, Color color, boolean isFirstMove){
        super(position, color, isFirstMove);
    }

    @Override
    public List<Move> accept(MoveVisitor moveVisitor, Board board) {
        return  moveVisitor.visit(this, board);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }

    @Override
    public String toString(){
        return PieceType.PAWN.toString();
    }

//    public boolean isFirstMove() {
//        if (color.equals(Color.WHITE) && getPosition().x == WHITE_PAWN_STARTING_ROW) {
//            return true;
//        } else
//            return color.equals(Color.BLACK) && getPosition().x == BLACK_PAWN_STARTING_ROW;
//    }

    @Override
    public Piece movePiece(Point destination) {
        return new Pawn(destination, getColor(), false);
    }
}
