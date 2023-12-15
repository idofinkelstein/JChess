package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import com.chess.gui.MoveLog;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public abstract class Piece {

    @Setter
    protected Point position;
    protected final Color color;
    private final boolean isFirstMove;

    protected Piece(Point position, Color color, boolean isFirstMove) {
        this.position = position;
        this.color = color;
        this.isFirstMove = isFirstMove;
    }

    public abstract List<Move> accept(MoveVisitor moveVisitor, Board board);
    public abstract PieceType getPieceType();

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public static List<Move> calculatePiecePreviousMoves(Piece piece, List<Move> moves) {
        List<Move> piecePreviousMoves = new ArrayList<Move>();
        for (Move move : moves) {
            if (move.getMovedPiece().equals(piece)) {
                piecePreviousMoves.add(move);
            }
        }
        return piecePreviousMoves;
    }

    public int getPieceValue() {
        return getPieceType().getPieceValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(position, piece.position)
                && color == piece.color
                && getPieceType().equals(((Piece) o).getPieceType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, color);
    }

    public abstract Piece movePiece(Point destination);

}
