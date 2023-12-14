package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;
import com.chess.engine.player.Player;

import java.awt.*;
import java.util.List;

public class KingSideCastling extends CastlingMove {

    public KingSideCastling(Board board, Piece king, Piece rook, Point kingDestination, Point rookDestination) {
        super(board,king, rook, kingDestination, rookDestination);
    }

    @Override
    public Board makeMove() {
        Board.BoardBuilder builder = new Board.BoardBuilder();
        Player activePlayer = board.getCurrentPlayer();

        builder.setActivePlayer(activePlayer.getOpponent().getColor())
                .placePiecesExcluding(board.getCurrentPlayer()
                        .getOpponent().getAvailablePieces(), null)
                .placePiecesExcluding(board.getCurrentPlayer().getAvailablePieces(), List.of(movedPiece, rook));

        builder.placePiece(movedPiece.movePiece(destination));
        builder.placePiece(rook.movePiece(rookDestination));

        return builder.build();
    }
}
