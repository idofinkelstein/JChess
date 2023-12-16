package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.List;

import static com.chess.engine.move.MoveUtils.BLACK_PAWN_STARTING_ROW;
import static com.chess.engine.move.MoveUtils.WHITE_PAWN_STARTING_ROW;

@Getter
@Setter
public class Pawn extends Piece {
//    private boolean isEnPassantPawn;
    public Pawn(Point position, Color color, boolean isFirstMove) {
        super(position, color, isFirstMove);
//        this.isEnPassantPawn = false;
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

    @Override
    public Piece movePiece(Point destination) {
        return new Pawn(destination, getColor(), false);
        // TOD: decide state of "isEnPassant" according to subclass of Move.
    }
}
