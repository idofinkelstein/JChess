package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;
import com.chess.engine.player.Player;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.awt.*;
import java.util.List;

import static com.chess.engine.move.MoveUtils.NUM_TO_LETTER_TEXT;
import static com.chess.engine.move.MoveUtils.NUM_TO_NUM_TEXT;

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

    @Override
    public String toString() {
        return NUM_TO_LETTER_TEXT.get(getDestination().y) + NUM_TO_NUM_TEXT.get(getDestination().x);
    }
}
