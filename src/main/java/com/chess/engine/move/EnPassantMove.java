package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.piece.Pawn;
import com.chess.engine.piece.Piece;

import java.awt.*;

public class EnPassantMove extends AttackMove{

    public EnPassantMove(Board board, Piece movedPiece, Point destination, Point source, Piece attackedPiece) {
        super(board, movedPiece, destination, source, attackedPiece);
    }

    // Conditions:
    // 1. the attacker will be in its fifth row. (from the start of the board)
    // 2. the attacked pawn hasn't moved yet
    // 3. the attacked pawn will be one column away from the attacker left or right
    // 4. the attacked pawn moves 2 steps forward
    // 5. the attacker attacks right after
    // 6. the attacker moves to the tile beyond the attacked piece
}
