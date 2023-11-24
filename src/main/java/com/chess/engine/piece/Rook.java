package com.chess.engine.piece;

import com.chess.engine.board.Board;

import java.awt.*;

public class Rook extends Piece{

    private final PieceType pieceType;
    public Rook(Point position, Color color) {
        super(position, color);
        this.pieceType = PieceType.ROOK;
    }

    @Override
    public void accept(MoveVisitor moveVisitor, Board board) {
        moveVisitor.visit(this, board);
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public String toString() {
        return PieceType.ROOK.toString();
    }
}
