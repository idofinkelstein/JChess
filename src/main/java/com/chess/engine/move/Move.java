package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;
import com.chess.engine.player.Player;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.swing.*;
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
        Player activePlayer = board.getCurrentPlayer();

        builder.setActivePlayer(activePlayer.getOpponent().getColor())
                .placePiecesExcluding(board.getCurrentPlayer()
                        .getOpponent().getAvailablePieces(), null)
                .placePiecesExcluding(board.getCurrentPlayer().getAvailablePieces(), List.of(movedPiece));

        movedPiece.setPosition(destination);
        builder.placePiece(movedPiece);

        return builder.build();
    }

    public Piece getAttackedPiece() {
        return null;
    }
}
