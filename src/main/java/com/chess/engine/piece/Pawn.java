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
    public Pawn(Point position, Color color, boolean isFirstMove) {
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

    @Override
    public Piece movePiece(Point destination) {
        return new Pawn(destination, getColor(), false);
    }

    public boolean isLastSquare(Point position) {
        return getColor().isLastSquare(position);
    }

    public Piece getPromotionPiece(PieceType type) {
        switch (type) {
            case ROOK -> {
                return new Rook(getPosition(), getColor(), false);
            }
            case BISHOP -> {
                return new Bishop(getPosition(), getColor(), false);
            }
            case KNIGHT -> {
                return new Knight(getPosition(), getColor(), false);
            }
            default -> { // Queen is the default promotion
                return new Queen(getPosition(), getColor(), false);
            }
        }
    }
}
