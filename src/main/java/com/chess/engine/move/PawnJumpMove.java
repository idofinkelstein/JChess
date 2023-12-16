package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Pawn;
import com.chess.engine.piece.Piece;
import com.chess.engine.player.Player;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class PawnJumpMove extends Move{
    public PawnJumpMove(Board board, Piece movedPiece, Point destination, Point source) {
        super(board, movedPiece, destination, source);
    }

    @Override
    public Board makeMove() {
        Board.BoardBuilder builder = new Board.BoardBuilder();
        Player activePlayer = board.getCurrentPlayer();

        builder.setActivePlayer(activePlayer.getOpponent().getColor())
                .placePiecesExcluding(board.getCurrentPlayer()
                        .getOpponent().getAvailablePieces(), null)
                .placePiecesExcluding(board.getCurrentPlayer().getAvailablePieces(), List.of(movedPiece));

        Pawn enPassant = (Pawn) movedPiece.movePiece(destination);
//        enPassant.setEnPassantPawn(true);

        return builder.placePiece(enPassant).setEnPassantPawn(enPassant).build();
    }
}
