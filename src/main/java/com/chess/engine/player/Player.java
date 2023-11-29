package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import com.chess.engine.move.MoveAttempt;
import com.chess.engine.piece.*;

import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.board.Board.BOARD_SIZE;

public abstract class Player {

    protected List<Piece> availablePieces;
    protected List<Move> availableMoves;
    protected King king;
    protected List<Move> previousMoves;

    public Player(Board board) {
        this.availablePieces = calculateAvailablePieces(board);
        this.availableMoves = calculateAvailableMoves(board);
        this.king = establishKing(board);
        this.previousMoves = new ArrayList<>();
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

    public MoveAttempt makeMove(Move move) {
        move.makeMove();
        return null;
    }

    public boolean isMoveLegal(Move move) {

        return availableMoves.contains(move);
    }

    public abstract Color getColor();
}
