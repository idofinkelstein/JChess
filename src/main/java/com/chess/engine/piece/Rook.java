package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;

import java.awt.*;
import java.util.List;

public class Rook extends Piece{

    private final PieceType pieceType;
    public Rook(Point position, Color color, boolean isFirstMove) {
        super(position, color, isFirstMove);
        this.pieceType = PieceType.ROOK;
    }

    @Override
    public List<Move> accept(MoveVisitor moveVisitor, Board board) {
        return moveVisitor.visit(this, board);
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public String toString() {
        return PieceType.ROOK.toString();
    }

    @Override
    public Piece movePiece(Point destination) {
        return new Rook(destination, getColor(), false);
    }
}
