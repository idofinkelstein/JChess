package com.chess.engine.piece;


import com.chess.engine.board.Board;
import com.chess.engine.move.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.chess.engine.move.MoveUtils.*;

public class MoveVisitorImpl implements MoveVisitor {
    @Override
    public List<Move> visit(Pawn pawn, Board board) {
        return null;
    }

    @Override
    public List<Move> visit(Knight knight, Board board) {
        List<Move> moves = new ArrayList<>();
        int currentX = knight.getPosition().x;
        int currentY = knight.getPosition().y;

        for (Point knightPossibleMove : KNIGHT_POSSIBLE_MOVES) {
            int newX = currentX + knightPossibleMove.x;
            int newY = currentY + knightPossibleMove.y;

            if (isMoveValid(newX, newY)) {

                Point newPosition = new Point(newX, newY);
                if (board.getTile(newX, newY).isOccupied()) { // There is piece on the tile
                    Piece pieceOnTile = board.getTile(newX, newY).getPiece();

                    if (!pieceOnTile.getColor().equals(knight.getColor())) { // There is enemy piece on the tile
                        Move attackMove = new KnightAttackMove(knight.getPosition(), newPosition, pieceOnTile);
                        System.out.println(newX + " " + newY);
                        moves.add(attackMove);
                    }
                } else {
                    Move move = new KnightMove(knight.getPosition(), newPosition);
                    System.out.println(newX + " " + newY);
                    moves.add(move);
                }
            }
        }
        return Collections.unmodifiableList(moves);
    }

    @Override
    public List<Move> visit(Bishop bishop, Board board) {
        List<Move> moves = new ArrayList<>();
        int currentX = bishop.getPosition().x;
        int currentY = bishop.getPosition().y;

        for (Point bishopPossibleMove : BISHOP_POSSIBLE_MOVES) {

            int newX = currentX + bishopPossibleMove.x;
            int newY = currentY + bishopPossibleMove.y;

            while (isMoveValid(newX, newY)) {
                Point newPosition = new Point(newX, newY);
                if (board.getTile(newX, newY).isOccupied()) { // There is piece on the tile
                    Piece pieceOnTile = board.getTile(newX, newY).getPiece();

                    if (!pieceOnTile.getColor().equals(bishop.getColor())) { // There is enemy piece on the tile
                        Move attackMove = new BishopAttackMove(bishop.getPosition(), newPosition, pieceOnTile);
                        System.out.println(newX + " " + newY);
                        moves.add(attackMove);
                    }
                    break;
                } else {
                    Move move = new BishopMove(bishop.getPosition(), newPosition);
                    System.out.println(newX + " " + newY);
                    moves.add(move);
                }
                newX += bishopPossibleMove.x;
                newY += bishopPossibleMove.y;
            }
        }

        return Collections.unmodifiableList(moves);
    }

    @Override
    public List<Move> visit(Rook rook, Board board) {
        List<Move> moves = new ArrayList<>();
        int currentX = rook.getPosition().x;
        int currentY = rook.getPosition().y;

        for (Point rookPossibleMove : ROOK_POSSIBLE_MOVES) {
            int newX = currentX + rookPossibleMove.x;
            int newY = currentY + rookPossibleMove.y;

            while (isMoveValid(newX, newY)) {

                Point newPosition = new Point(newX, newY);
                if (board.getTile(newX, newY).isOccupied()) { // There is piece on the tile

                    Piece pieceOnTile = board.getTile(newX, newY).getPiece();
                    if (!pieceOnTile.getColor().equals(rook.getColor())) { // There is enemy piece on the tile

                        Move attackMove = new RookAttackMove(rook.getPosition(), newPosition, pieceOnTile);
                        System.out.println(newX + " " + newY);
                        moves.add(attackMove);
                    }
                    break;
                } else {
                    Move move = new RookMove(rook.getPosition(), newPosition);
                    System.out.println(newX + " " + newY);
                    moves.add(move);
                }
                newX += rookPossibleMove.x;
                newY += rookPossibleMove.y;
            }
        }
        return Collections.unmodifiableList(moves);
    }

    @Override
    public List<Move> visit(Queen queen, Board board) {
        List<Move> moves = new ArrayList<>();

        int currentX = queen.getPosition().x;
        int currentY = queen.getPosition().y;

        for (Point queenPossibleMove : QUEEN_POSSIBLE_MOVES) {
            int newX = currentX + queenPossibleMove.x;
            int newY = currentY + queenPossibleMove.y;

            while (isMoveValid(newX, newY)) {
                Point newPosition = new Point(newX, newY);
                if (board.getTile(newX, newY).isOccupied()) { // There is piece on the tile

                    Piece pieceOnTile = board.getTile(newX, newY).getPiece();
                    if (!pieceOnTile.getColor().equals(queen.getColor())) { // There is enemy piece on the tile

                        Move attackMove = new QueenAttackMove(queen.getPosition(), newPosition, pieceOnTile);
                        System.out.println(newX + " " + newY);
                        moves.add(attackMove);
                    }
                    break;
                } else {
                    Move move = new QueenMove(queen.getPosition(), newPosition);
                    System.out.println(newX + " " + newY);
                    moves.add(move);
                }
                newX += queenPossibleMove.x;
                newY += queenPossibleMove.y;
            }
        }
        return Collections.unmodifiableList(moves);
    }

    @Override
    public List<Move> visit(King king, Board board) {
        List<Move> moves = new ArrayList<>();
        int currentX = king.getPosition().x;
        int currentY = king.getPosition().y;

        for (Point kingPossibleMove : KING_POSSIBLE_MOVES) {
            int newX = currentX + kingPossibleMove.x;
            int newY = currentY + kingPossibleMove.y;

            if (isMoveValid(newX, newY)) {
                Point newPosition = new Point(newX, newY);
                if (board.getTile(newX, newY).isOccupied()) { // There is piece on the tile
                    Piece pieceOnTile = board.getTile(newX, newY).getPiece();
                    if (!pieceOnTile.getColor().equals(king.getColor())) { // There is enemy piece on the tile

                        Move attackMove = new KingAttackMove(king.getPosition(), newPosition, pieceOnTile);
                        System.out.println(newX + " " + newY);
                        moves.add(attackMove);
                    }
                } else {
                    Move move = new KingMove(king.getPosition(), newPosition);
                    System.out.println(newX + " " + newY);
                    moves.add(move);
                }
            }
        }
        return Collections.unmodifiableList(moves);
    }
}
