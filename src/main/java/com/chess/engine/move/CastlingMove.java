package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;
import com.chess.engine.player.Player;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public abstract class CastlingMove extends Move{
    // king piece and king position are extended from base class (Move)
    protected final Piece rook;
    protected final Point rookDestination;

    public CastlingMove(Board board, Piece king, Piece rook, Point kingDestination, Point rookDestination) {
        super(board, king, kingDestination, king.getPosition());
        this.rook = rook;
        this.rookDestination = rookDestination;
    }

    @Override
    public Board makeMove() {
        Board.BoardBuilder builder = new Board.BoardBuilder();
        Player activePlayer = board.getActivePlayer();

        builder.setActivePlayer(activePlayer.getOpponent().getColor())
                .placePiecesExcluding(board.getActivePlayer()
                        .getOpponent().getAvailablePieces(), null)
                .placePiecesExcluding(board.getActivePlayer().getAvailablePieces(), List.of(movedPiece, rook));

        builder.placePiece(movedPiece.movePiece(destination));
        builder.placePiece(rook.movePiece(rookDestination));

        return builder.build();
    }
}
