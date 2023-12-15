package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import com.chess.gui.MoveLog;

import java.awt.*;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Point position, Color color, boolean isFirstMove) {
        super(position, color, isFirstMove);
    }

    @Override
    public List<Move> accept(MoveVisitor moveVisitor, Board board) {
        return moveVisitor.visit(this, board);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }

    @Override
    public Piece movePiece(Point destination) {
        return new Bishop(destination, getColor(), false);
    }

    @Override
    public String toString() {
        return PieceType.BISHOP.toString();
    }
}
