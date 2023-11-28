package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Color;

public class WhitePlayer extends Player {
    public WhitePlayer(Board board) {
        super(board);
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }
}
