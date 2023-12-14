package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.move.CastlingMove;
import com.chess.engine.move.KingSideCastling;
import com.chess.engine.move.Move;
import com.chess.engine.piece.*;
import com.chess.engine.piece.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.board.Board.BOARD_SIZE;

public class BlackPlayer extends Player{
    public BlackPlayer(Board board,
                       List<Piece> availablePieces,
                       List<Move> availableMoves,
                       List<Move> opponentAvailableMoves) {
        super(board, availablePieces, availableMoves, opponentAvailableMoves);
    }

    @Override
    public Player getOpponent() {
        return board.getWhitePlayer();
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    @Override
    public List<Move> calculateCastlingMoves() {
        List<Move> castlingMoves = new ArrayList<>();
        King king = getKing();

        if (king.isFirstMove() && !isInCheck() && board.getTile(0, 7).isOccupied()
                && board.getTile(0, 7).getPiece() instanceof Rook rook
                && rook.isFirstMove()) {

            if (isTileSafeToGo(board.getTile(0, 6).getPosition()) && isTileSafeToGo(board.getTile(0, 5).getPosition())) {
                CastlingMove kingSideCastle = new KingSideCastling(board, king, board.getTile(0, 7).getPiece(), new Point(0, 6), new Point(0, 5));
                castlingMoves.add(kingSideCastle);
            }
        }
        return castlingMoves;
    }
}
