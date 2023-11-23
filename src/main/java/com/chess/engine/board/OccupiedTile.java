package com.chess.engine.board;

import com.chess.engine.piece.Piece;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class OccupiedTile extends Tile{
    protected OccupiedTile(int x, int y, Piece piece) {
        super(x, y, piece);
    }

    @Override
    boolean isOccupied() {
        return true;
    }

    @Override
    boolean isEmpty() {
        return false;
    }
}
