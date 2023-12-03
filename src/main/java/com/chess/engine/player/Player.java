package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import com.chess.engine.move.MoveAttempt;
import com.chess.engine.piece.*;
import com.chess.engine.piece.Color;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.board.Board.BOARD_SIZE;

@Getter
public abstract class Player {

    protected final Board board;

    private final boolean isIncheck;
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
        this.availableMoves = availableMoves;
        this.opponentAvailableMoves = opponentAvailableMoves;
        this.king = establishKing();
        this.previousMoves = new ArrayList<>();
        this.isIncheck = isKingInCheck();
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

    public List<Piece> calculateAvailablePieces() {
        List<Piece> availablePieces = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {

                if (board.getTile(i, j).isOccupied()) {
                    Piece piece = board.getTile(i, j).getPiece();

                    if (piece.getColor() == getColor()) {
                        availablePieces.add(piece);
                    }
                }
            }
        }
        return availablePieces;
    }

    public List<Move> calculateAvailableMoves() {
        MoveVisitor moveVisitor = new MoveVisitorImpl();
        List<Move> moves = new ArrayList<Move>();

        for (Piece piece : availablePieces) {
            moves.addAll(piece.accept(moveVisitor, board));
        }
        return moves;
    }

    public boolean isInCheck() {
        return false;
    }

    public boolean isCheckmate() {
        return false;
    }

    public boolean isInStalemate() {
        return false;
    }

    public boolean isMoveLegal(Move move) {
        return availableMoves.contains(move);
    }

    public boolean isKingInCheck() {
        return !calculateAttackOnTile(king.getPosition(), opponentAvailableMoves).isEmpty();
    }

    public abstract Player getOpponent();
    public abstract Color getColor();

    public MoveAttempt makeMove(Move move, Board board) {

        if (!isMoveLegal(move)) {
            return new MoveAttempt(board, move, MoveAttempt.MoveStatus.ILLEGAL_MOVE);
        }

        Board transitionBoard = move.makeMove();
        // check if current player's king is in check
        List<Move> attacksOnKing = calculateAttackOnTile(transitionBoard.getCurrentPlayer().getOpponent().getKing().getPosition(),
                transitionBoard.getCurrentPlayer().getAvailableMoves());

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
}
