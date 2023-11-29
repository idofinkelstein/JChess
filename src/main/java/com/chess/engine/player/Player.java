package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import com.chess.engine.move.MoveAttempt;
import com.chess.engine.piece.*;
import com.chess.engine.piece.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.board.Board.BOARD_SIZE;

public abstract class Player {

    private final boolean isIncheck;
    protected List<Piece> availablePieces;
    protected List<Move> availableMoves;
    protected List<Move> opponentAvailableMoves;
    protected King king;
    protected List<Move> previousMoves;

    public Player(Board board, List<Piece> availablePieces, List<Move> availableMoves, List<Move> opponentAvailableMoves) {
        this.availablePieces = availablePieces;
        this.availableMoves = availableMoves;
        this.opponentAvailableMoves = opponentAvailableMoves;
        this.king = establishKing(board);
        this.previousMoves = new ArrayList<>();
        this.isIncheck = calculateAttackOnTile(king.getPosition(), opponentAvailableMoves);
    }

    private static boolean calculateAttackOnTile(Point position, List<Move> moves) {
        for (Move move : moves) {
            if (move.getDestination().equals(position)) {
                return true;
            }
        }
        return false;
    }

    private King establishKing(Board board) {
        for (Piece piece : availablePieces) {
            if (piece instanceof King) {
                return (King) piece;
            }
        }
        throw new IllegalStateException("No king found");
    }

    public List<Piece> calculateAvailablePieces(Board board) {
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

    public List<Move> calculateAvailableMoves(Board board) {
        MoveVisitor moveVisitor = new MoveVisitorImpl();
        List<Move> moves = new ArrayList<Move>();

        for (Piece piece : availablePieces) {
            moves.addAll(piece.accept(moveVisitor, board));
        }
        return moves;
    }

    public boolean isInCheck(Board board) {
        return false;
    }

    public boolean isCheckmate(Board board) {
        return false;
    }

    public boolean isInStalemate(Board board) {
        return false;
    }

    public abstract Player getOpponent(Board board);

    // Consider passing the board to the constructor instead of calling the makeMove() with the board as argument
    public MoveAttempt makeMove(Move move, Board board) {

        if (!isMoveLegal(move)) {
            return new MoveAttempt(board, move, MoveAttempt.MoveStatus.ILLEGAL_MOVE);
        }

//        Board board = move.makeMove();
        // check if current player's king is in check


        return null;
    }

    public boolean isMoveLegal(Move move) {
        return availableMoves.contains(move);
    }

    public abstract Color getColor();
}
