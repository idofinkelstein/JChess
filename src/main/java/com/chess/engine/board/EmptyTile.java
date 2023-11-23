package com.chess.engine.board;

import com.chess.engine.piece.Piece;

public class EmptyTile extends Tile{
    protected EmptyTile(int x, int y, Piece piece) {
        super(x, y, piece);
    }

    @Override
    boolean isOccupied() {
        return false;
    }

    @Override
    boolean isEmpty() {
        return true;
    }

}
