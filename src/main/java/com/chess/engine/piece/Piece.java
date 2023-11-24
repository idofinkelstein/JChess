package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Piece {

    protected final Point position;
    protected final Color color;
    protected final List<Move> possibleMoves;

    protected Piece(Point position, Color color) {
        this.position = position;
        this.color = color;
        this.possibleMoves = new ArrayList<>();

    }

    public abstract void accept(MoveVisitor moveVisitor, Board board);
}
