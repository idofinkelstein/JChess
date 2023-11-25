package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;

import java.util.List;

public interface MoveVisitor {

    public List<Move> visit(Pawn pawn, Board board);
    public List<Move> visit(Knight knight, Board board);
    public List<Move> visit(Bishop bishop, Board board);
    public List<Move> visit(Rook rook, Board board);
    public List<Move> visit(Queen queen, Board board);
    public List<Move> visit(King king, Board board);






}
