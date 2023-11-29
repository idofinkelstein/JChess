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
        List<Move> moves = new ArrayList<>();
        int currentX = pawn.getPosition().x;
        int currentY = pawn.getPosition().y;
        Color currentColor = pawn.getColor();

        // Calculate regular moves
        Point[] pawnPossibleMoves = (currentColor.equals(Color.WHITE))
                ? WHITE_PAWN_POSSIBLE_MOVES
                : BLACK_PAWN_POSSIBLE_MOVES;

        for (Point pawnPossibleMove : pawnPossibleMoves) {

            int newX = currentX + pawnPossibleMove.x;
            int newY = currentY + pawnPossibleMove.y;

            if (isMoveValid(newX, newY) && !board.getTile(newX, newY).isOccupied()) {
                Point newPosition = new Point(newX, newY);
                Move move = new PawnMove(pawn, pawn.getPosition(), newPosition);
                moves.add(move);
            }
        }

        // Calculate jump moves
        Point[] pawnPossibleJumpMoves = (currentColor.equals(Color.WHITE))
                ? WHITE_PAWN_POSSIBLE_JUMP_MOVES
                : BLACK_PAWN_POSSIBLE_JUMP_MOVES;

        for (Point pawnPossibleJumpMove : pawnPossibleJumpMoves) {
            int newX = currentX + pawnPossibleJumpMove.x;
            int newY = currentY + pawnPossibleJumpMove.y;

            if (isMoveValid(newX, newY) && !board.getTile(newX, newY).isOccupied() && pawn.isFirstMove()) {
                Point newPosition = new Point(newX, newY);
                Move move = new PawnJumpMove(pawn, pawn.getPosition(), newPosition);
                moves.add(move);
            }
        }

        // Calculate attack moves
        Point[] pawnPossibleAttackMoves = (currentColor.equals(Color.WHITE))
                ? WHITE_PAWN_POSSIBLE_ATTACK_MOVES
                : BLACK_PAWN_POSSIBLE_ATTACK_MOVES;

        for (Point pawnPossibleAttackMove : pawnPossibleAttackMoves) {
            int newX = currentX + pawnPossibleAttackMove.x;
            int newY = currentY + pawnPossibleAttackMove.y;

            if (isMoveValid(newX, newY) && board.getTile(newX, newY).isOccupied()) {
                Point newPosition = new Point(newX, newY);
                Piece pieceOnTile = board.getTile(newX, newY).getPiece();

                if (!pieceOnTile.getColor().equals(pawn.getColor())) { // There is enemy piece
                    Move move = new PawnAttackMove(pawn, pawn.getPosition(), newPosition, pieceOnTile);
                    moves.add(move);
                }
            }
        }

        return Collections.unmodifiableList(moves);
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
                        Move attackMove = new KnightAttackMove(knight, knight.getPosition(), newPosition, pieceOnTile);
                        System.out.println(newX + " " + newY);
                        moves.add(attackMove);
                    }
                } else {
                    Move move = new KnightMove(knight, knight.getPosition(), newPosition);
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
                        Move attackMove = new BishopAttackMove(bishop, bishop.getPosition(), newPosition, pieceOnTile);
                        System.out.println(newX + " " + newY);
                        moves.add(attackMove);
                    }
                    break;
                } else {
                    Move move = new BishopMove(bishop, bishop.getPosition(), newPosition);
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

                        Move attackMove = new RookAttackMove(rook, rook.getPosition(), newPosition, pieceOnTile);
                        System.out.println(newX + " " + newY);
                        moves.add(attackMove);
                    }
                    break;
                } else {
                    Move move = new RookMove(rook, rook.getPosition(), newPosition);
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

                        Move attackMove = new QueenAttackMove(queen, queen.getPosition(), newPosition, pieceOnTile);
                        System.out.println(newX + " " + newY);
                        moves.add(attackMove);
                    }
                    break;
                } else {
                    Move move = new QueenMove(queen, queen.getPosition(), newPosition);
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

                        Move attackMove = new KingAttackMove(king, king.getPosition(), newPosition, pieceOnTile);
                        System.out.println(newX + " " + newY);
                        moves.add(attackMove);
                    }
                } else {
                    Move move = new KingMove(king, king.getPosition(), newPosition);
                    System.out.println(newX + " " + newY);
                    moves.add(move);
                }
            }
        }
        return Collections.unmodifiableList(moves);
    }
}
