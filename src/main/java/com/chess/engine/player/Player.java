package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.move.KingSideCastling;
import com.chess.engine.move.Move;
import com.chess.engine.move.MoveAttempt;
import com.chess.engine.piece.*;
import com.chess.engine.piece.Color;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
public abstract class Player {

    protected final Board board;

    private final boolean isInCheck;
    protected List<Piece> availablePieces;
    protected List<Move> availableMoves;
    protected List<Move> opponentAvailableMoves;
    protected King king;
    protected List<Move> previousMoves;

    public Player(Board board,
                  List<Piece> availablePieces,
                  List<Move> availableMoves,
                  List<Move> opponentAvailableMoves) {

        this.board = board;
        this.availablePieces = availablePieces;
        this.king = establishKing();
        this.opponentAvailableMoves = opponentAvailableMoves;
        this.availableMoves = Stream.concat(availableMoves.stream(), calculateCastlingMoves().stream()).toList();
        this.previousMoves = new ArrayList<>();
        this.isInCheck = !calculateAttackOnTile(king.getPosition(), opponentAvailableMoves).isEmpty();
    }
    public abstract Player getOpponent();
    public abstract Color getColor();
    public abstract List<Move> calculateCastlingMoves();

    public boolean isTileSafeToGo(Point position) {
        return !board.getTile(position.x, position.y).isOccupied() && calculateAttackOnTile(position, opponentAvailableMoves).isEmpty();
    }

    public static List<Move> calculateAttackOnTile(Point position, List<Move> moves) {
        List<Move> movesOnTile = new ArrayList<>();
        for (Move move : moves) {
            if (move.getDestination().equals(position)) {
                movesOnTile.add(move);
            }
        }
        return movesOnTile;
    }

    public boolean isInCheck() {
        return isInCheck;
    }

    public boolean isInCheckMate() {
        return isInCheck && !hasEscapeMove();
    }
    public boolean isInStalemate() {
        return !isInCheck && !hasEscapeMove();
    }

    private boolean hasEscapeMove() {
        for (Move move : availableMoves) {
            MoveAttempt moveAttempt = makeMove(move, board);
            if (moveAttempt.moveStatus() == MoveAttempt.MoveStatus.OK) {
                return true;
            }
        }
        return false;
    }


    public boolean isMoveLegal(Move move) {
        return availableMoves.contains(move);
    }


    public MoveAttempt makeMove(Move move, Board board) {

        if (!isMoveLegal(move)) {
            return new MoveAttempt(board, move, MoveAttempt.MoveStatus.ILLEGAL_MOVE);
        }

        Board transitionBoard = move.makeMove();
        // check if current player's king is in check
        List<Move> attacksOnKing = calculateAttackOnTile(transitionBoard.getActivePlayer().getOpponent().getKing().getPosition(),
                transitionBoard.getActivePlayer().getAvailableMoves());

        if (!attacksOnKing.isEmpty()) { // this move leaves king in check
            return new MoveAttempt(transitionBoard, move, MoveAttempt.MoveStatus.LEAVES_KING_IN_CHECK);
        }

        return new MoveAttempt(transitionBoard, move, MoveAttempt.MoveStatus.OK);
    }

    private King establishKing() {
        for (Piece piece : availablePieces) {
            if (piece instanceof King) {
                return (King) piece;
            }
        }
        throw new IllegalStateException("No king found");
    }

    public boolean isKingSideCastleCapable() {
        return getKing().isKingSideCapable();
    }

    public boolean isQueenSideCastleCapable() {
        return getKing().isQueenSideCapable();
    }
}
