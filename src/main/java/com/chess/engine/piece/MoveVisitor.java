package com.chess.engine.piece;

import com.chess.engine.board.Board;

public interface MoveVisitor {

    public void visit(Pawn pawn, Board board);
    public void visit(Knight knight, Board board);
    public void visit(Bishop bishop, Board board);
    public void visit(Rook rook, Board board);
    public void visit(Queen queen, Board board);
    public void visit(King king, Board board);






}
