package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;
import com.chess.engine.player.Player;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.awt.*;
import java.util.List;

@EqualsAndHashCode
@Getter
public abstract class Move {
    protected final Board board;
    protected final Piece movedPiece;
    protected final Point destination;
    protected final Point source;

    public Move(Board board, Piece movedPiece, Point destination, Point source) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destination = destination;
        this.source = source;
    }

    public Board makeMove() {
        Board.BoardBuilder builder = new Board.BoardBuilder();
        Player activePlayer = board.getActivePlayer();

        builder.setActivePlayer(activePlayer.getOpponent().getColor())
                .placePiecesExcluding(board.getActivePlayer()
                        .getOpponent().getAvailablePieces(), null)
                .placePiecesExcluding(board.getActivePlayer().getAvailablePieces(), List.of(movedPiece));


        return builder.placePiece(movedPiece.movePiece(destination)).build();
    }

    public Piece getAttackedPiece() {
        return null;
    }
}
