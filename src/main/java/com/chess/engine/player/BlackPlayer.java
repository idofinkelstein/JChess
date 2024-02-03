package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.move.CastlingMove;
import com.chess.engine.move.KingSideCastling;
import com.chess.engine.move.Move;
import com.chess.engine.move.QueenSideCastling;
import com.chess.engine.piece.*;
import com.chess.engine.piece.Color;
import com.sun.source.util.DocSourcePositions;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.board.Board.BOARD_SIZE;
import static com.chess.engine.board.BoardUtils.POSITIONS;

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

        if (king.isFirstMove() && !isInCheck()) {

            if (board.getTile(0, 7).isOccupied()
                    && board.getTile(0, 7).getPiece() instanceof Rook rook
                    && rook.isFirstMove()
                    && isTileSafeToGo(board.getTile(0, 6).getPosition())
                    && isTileSafeToGo(board.getTile(0, 5).getPosition())) {
                CastlingMove kingSideCastle = new KingSideCastling(board, king, board.getTile(0, 7).getPiece(), POSITIONS[0][6], POSITIONS[0][5]);
                castlingMoves.add(kingSideCastle);
            }
            if (board.getTile(0, 0).isOccupied()
                    && board.getTile(0, 0).getPiece() instanceof Rook rook
                    && rook.isFirstMove()
                    && isTileSafeToGo(board.getTile(0, 3).getPosition())
                    && isTileSafeToGo(board.getTile(0, 2).getPosition())) {
                CastlingMove queenSideCastle = new QueenSideCastling(board, king, board.getTile(0, 0).getPiece(), POSITIONS[0][2], POSITIONS[0][3]);
                castlingMoves.add(queenSideCastle);
            }
        }
        return castlingMoves;
    }

    @Override
    public String toString() {
        return "BlackPlayer";
    }
}
