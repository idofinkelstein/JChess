package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;
import com.chess.engine.player.Player;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.util.List;
@EqualsAndHashCode(callSuper = true)
public abstract class AttackMove extends Move{
    protected final Piece attackedPiece;

    public AttackMove(Board board, Piece movedPiece, Point destination, Point source, Piece attackedPiece) {
        super(board, movedPiece, destination, source);
        this.attackedPiece = attackedPiece;
    }

    @Override
    public Board makeMove() {
        Board.BoardBuilder builder = new Board.BoardBuilder();
        Player activePlayer = board.getActivePlayer();

        builder.setActivePlayer(activePlayer.getOpponent().getColor())
                .placePiecesExcluding(board.getActivePlayer()
                        .getOpponent().getAvailablePieces(), List.of(attackedPiece))
                .placePiecesExcluding(board.getActivePlayer().getAvailablePieces(), List.of(movedPiece));
        builder.placePiece(movedPiece.movePiece(getDestination()));
        return builder.build();
    }

    @Override
    public Piece getAttackedPiece() {
        return attackedPiece;
    }
}
