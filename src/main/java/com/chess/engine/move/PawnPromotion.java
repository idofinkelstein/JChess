package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Pawn;
import com.chess.engine.piece.Piece;
import com.chess.engine.piece.PieceType;
import com.chess.engine.player.Player;
import lombok.Setter;

import java.util.List;


public class PawnPromotion extends Move {

    private final Move decoratedMove;
    private final Pawn promotionPawn;
    private PieceType promotionType;

    public PawnPromotion(Move decoratadMove) {
        super(decoratadMove.getBoard(), decoratadMove.getMovedPiece(), decoratadMove.getDestination(), decoratadMove.getSource());
        this.decoratedMove = decoratadMove;
        this.promotionPawn = (Pawn) decoratadMove.getMovedPiece();
        this.promotionType = PieceType.QUEEN;
    }

    @Override
    public Board makeMove() {
        Board pawnMoveBoard = decoratedMove.makeMove();
        Board.BoardBuilder builder = new Board.BoardBuilder();
        Player activePlayer = board.getActivePlayer();
        Piece promotionPiece = promotionPawn.getPromotionPiece(promotionType);

        builder.setActivePlayer(activePlayer.getOpponent().getColor())
                .placePiecesExcluding(pawnMoveBoard.getActivePlayer()
                        .getOpponent().getAvailablePieces(), List.of(movedPiece))
                .placePiecesExcluding(pawnMoveBoard.getActivePlayer().getAvailablePieces(), null);

        return builder.placePiece(promotionPiece.movePiece(destination)).build();
    }

    public void setPromotionType(PieceType promotionType) {
        if (promotionType != null) {
            this.promotionType = promotionType;
        }
    }
}
