package com.chess.engine.board;

import com.chess.engine.piece.Piece;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

import static com.chess.engine.board.BoardUtils.POSITIONS;

@Getter
@Setter
public abstract class Tile {

    protected final Point position;
    protected final Piece piece;

    protected Tile(int x, int y, Piece piece) {
        this.position = POSITIONS[x][y];
        this.piece = piece;
    }

    public abstract boolean isOccupied();

}
