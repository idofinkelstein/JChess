package com.chess.engine.piece;


import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
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
                Move move = new PawnMove(board, pawn, newPosition, pawn.getPosition());
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
                System.out.println("jump move");
                Point newPosition = new Point(newX, newY);
                Move move = new PawnJumpMove(board, pawn, newPosition, pawn.getPosition());
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

                if (!pieceOnTile.getColor().equals(pawn.getColor())) { // There is an enemy piece
                    Move move = new PawnAttackMove(board, pawn, newPosition, pawn.getPosition(), pieceOnTile);
                    moves.add(move);
                }
            }
        }

        // Calculate EnPassant Move
        for (Point possibleEnPassant : PAWN_POSSIBLE_EN_PASSANT_MOVES) {

            if (isMoveValid(currentX,currentY + possibleEnPassant.y)) {

                Tile enPassantTile = board.getTile(currentX,currentY + possibleEnPassant.y);
                Pawn enPassantPawn = board.getEnPassantPawn();
                if (enPassantPawn != null && enPassantPawn == enPassantTile.getPiece()) {

                    System.out.println("EnPassant");
                    int xPosition = (pawn.getColor().equals(Color.WHITE) ? -1 : 1) + currentX;
                    Point newPosition = new Point(xPosition, currentY + possibleEnPassant.y);

                    Move move = new EnPassantMove(board, pawn, newPosition, pawn.getPosition(), enPassantPawn);
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
                if (board.getTile(newX, newY).isOccupied()) { // There is a piece on the tile
                    Piece pieceOnTile = board.getTile(newX, newY).getPiece();

                    if (!pieceOnTile.getColor().equals(knight.getColor())) { // There is an enemy piece on the tile
                        Move attackMove = new KnightAttackMove(board, knight, newPosition, knight.getPosition(), pieceOnTile);
                        moves.add(attackMove);
                    }
                } else {
                    Move move = new KnightMove(board, knight, newPosition, knight.getPosition());
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
                if (board.getTile(newX, newY).isOccupied()) { // There is a piece on the tile
                    Piece pieceOnTile = board.getTile(newX, newY).getPiece();

                    if (!pieceOnTile.getColor().equals(bishop.getColor())) { // There is an enemy piece on the tile
                        Move attackMove = new BishopAttackMove(board, bishop,  newPosition, bishop.getPosition(), pieceOnTile);
                        moves.add(attackMove);
                    }
                    break;
                } else {
                    Move move = new BishopMove(board, bishop, newPosition, bishop.getPosition());
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
                if (board.getTile(newX, newY).isOccupied()) { // There is a piece on the tile

                    Piece pieceOnTile = board.getTile(newX, newY).getPiece();
                    if (!pieceOnTile.getColor().equals(rook.getColor())) { // There is an enemy piece on the tile

                        Move attackMove = new RookAttackMove(board, rook,  newPosition, rook.getPosition(), pieceOnTile);
                        moves.add(attackMove);
                    }
                    break;
                } else {
                    Move move = new RookMove(board, rook, newPosition, rook.getPosition());
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
                if (board.getTile(newX, newY).isOccupied()) { // There is a piece on the tile

                    Piece pieceOnTile = board.getTile(newX, newY).getPiece();
                    if (!pieceOnTile.getColor().equals(queen.getColor())) { // There is an enemy piece on the tile

                        Move attackMove = new QueenAttackMove(board, queen,  newPosition, queen.getPosition(), pieceOnTile);
                        moves.add(attackMove);
                    }
                    break;
                } else {
                    Move move = new QueenMove(board, queen, newPosition, queen.getPosition());
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
                if (board.getTile(newX, newY).isOccupied()) { // There is a piece on the tile
                    Piece pieceOnTile = board.getTile(newX, newY).getPiece();
                    if (!pieceOnTile.getColor().equals(king.getColor())) { // There is an enemy piece on the tile

                        Move attackMove = new KingAttackMove(board, king,  newPosition, king.getPosition(), pieceOnTile);
                        moves.add(attackMove);
                    }
                } else {
                    Move move = new KingMove(board, king, newPosition, king.getPosition());
                    moves.add(move);
                }
            }
        }
        return Collections.unmodifiableList(moves);
    }
}
