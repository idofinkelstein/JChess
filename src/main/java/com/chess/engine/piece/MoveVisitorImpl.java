package com.chess.engine.piece;


import com.chess.engine.board.Board;
import com.chess.engine.move.KnightAttackMove;
import com.chess.engine.move.KnightMove;
import com.chess.engine.move.Move;

import java.awt.*;

import static com.chess.engine.move.MoveUtils.KNIGHT_POSSIBLE_MOVES;
import static com.chess.engine.move.MoveUtils.isMoveValid;

public class MoveVisitorImpl implements MoveVisitor{
    @Override
    public void visit(Pawn pawn, Board board) {

    }

    @Override
    public void visit(Knight knight, Board board) {
        int currentX = knight.getPosition().x;
        int currentY = knight.getPosition().y;

        for (int i = 0; i < KNIGHT_POSSIBLE_MOVES.length; i++) {
            int newX = currentX + KNIGHT_POSSIBLE_MOVES[i].x;
            int newY = currentY + KNIGHT_POSSIBLE_MOVES[i].y;

            if (isMoveValid(newX, newY)) {
                System.out.println(newX + " " + newY);
                Point newPosition = new Point(newX, newY);
                if (board.getTile(newX, newY).isOccupied()
                        && !board.getTile(newX, newY)
                        .getPiece().getColor()
                        .equals(knight.getColor())) {
                    Move attackMove = new KnightAttackMove(knight.getPosition(), newPosition, board.getTile(newX, newY)
                            .getPiece());
                    knight.possibleMoves.add(attackMove);
                }
                else {
                    Move move = new KnightMove(knight.getPosition(), newPosition);
                    knight.possibleMoves.add(move);

                }
            }
        }
    }

    @Override
    public void visit(Bishop bishop, Board board) {

    }

    @Override
    public void visit(Rook rook, Board board) {

    }

    @Override
    public void visit(Queen queen, Board board) {

    }

    @Override
    public void visit(King king, Board board) {

    }
}
