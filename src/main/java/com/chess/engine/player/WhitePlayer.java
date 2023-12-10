package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.move.CastlingMove;
import com.chess.engine.move.KingSideCastling;
import com.chess.engine.move.Move;
import com.chess.engine.piece.Color;
import com.chess.engine.piece.King;
import com.chess.engine.piece.Piece;
import com.chess.engine.piece.Rook;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WhitePlayer extends Player {
    public WhitePlayer(Board board,
                       List<Piece> availablePieces,
                       List<Move> availableMoves,
                       List<Move> opponentAvailableMoves) {
        super(board, availablePieces, availableMoves, opponentAvailableMoves);
    }

    @Override
    public Player getOpponent() {
        return board.getBlackPlayer();
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }

    @Override
    public List<Move> calculateCastlingMoves() {
        // Conditions:
        // 1. King and rook hasn't moved yet
        // 2. King isn't threatened currently
        // 3. King will not be threatened after castling ends
        // 4. King can't cross tiles under threat
        // 5. There is a clear path between king and rook
        List<Move> castlingMoves = new ArrayList<>();
        King king = getKing();

        if (king.isFirstMove() && !isInCheck() && board.getTile(7, 7).isOccupied()
                && board.getTile(7, 7).getPiece() instanceof Rook rook
                && rook.isFirstMove()) {

            if (isTileSafeToGo(board.getTile(7, 6).getPosition()) && isTileSafeToGo(board.getTile(7, 5).getPosition())) {
                CastlingMove kingSideCastle = new KingSideCastling(board, king, board.getTile(7, 7).getPiece(), new Point(7, 6), new Point(7, 5));
                castlingMoves.add(kingSideCastle);
            }
        }
        return castlingMoves;
    }
}
