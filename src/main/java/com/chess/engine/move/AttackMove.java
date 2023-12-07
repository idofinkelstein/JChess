package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Piece;
import com.chess.engine.player.Player;

import java.awt.*;
import java.util.List;

public abstract class AttackMove extends Move{
    protected final Piece attackedPiece;

    public AttackMove(Board board, Piece movedPiece, Point destination, Point source, Piece attackedPiece) {
        super(board, movedPiece, destination, source);
        this.attackedPiece = attackedPiece;
    }

    @Override
    public Board makeMove() {
        Board.BoardBuilder builder = new Board.BoardBuilder();
        Player activePlayer = board.getCurrentPlayer();

        builder.setActivePlayer(activePlayer.getOpponent().getColor())
                .placePiecesExcluding(board.getCurrentPlayer()
                        .getOpponent().getAvailablePieces(), List.of(attackedPiece))
                .placePiecesExcluding(board.getCurrentPlayer().getAvailablePieces(), List.of(movedPiece));
        movedPiece.setPosition(destination);
        builder.placePiece(movedPiece);
        return builder.build();
    }

    @Override
    public Piece getAttackedPiece() {
        return attackedPiece;
    }
}
