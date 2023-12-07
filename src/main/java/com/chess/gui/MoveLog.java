package com.chess.gui;

import com.chess.engine.move.Move;

import java.util.ArrayList;
import java.util.List;

public class MoveLog {
    private final List<Move> moves;

    public MoveLog() {
        moves = new ArrayList<>();
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void clear() {
        moves.clear();
    }

    public boolean isEmpty() {
        return moves.isEmpty();
    }

    public int size() {
        return moves.size();
    }

    public boolean contains(Move move) {
        return moves.contains(move);
    }

    public boolean remove(Move move) {
        return moves.remove(move);
    }
}
