package com.chess.engine.board;

import com.chess.engine.move.CastlingMove;
import com.chess.engine.move.KingSideCastling;
import com.chess.engine.piece.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.chess.engine.board.BoardUtils.POSITIONS;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board.BoardBuilder().setStartingPlayer().populateMap().build();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void testLegalMoves() {
        assertEquals(40, board.getAvailableMoves().size());
    }

    @Test
    void testWhiteMoves() {
        assertEquals(20, board.getWhitePlayer().getAvailableMoves().size());
    }

    @Test
    void testBlackMoves() {
        assertEquals(20, board.getBlackPlayer().getAvailableMoves().size());
    }
    @Test
    void testStartingPlayer() {
        assertEquals(Color.WHITE, board.getActivePlayer().getColor());
    }

    @Test
    void testBlackPlayerPlaysAfterWhite() {
        assertEquals(Color.BLACK, board.getActivePlayer().getOpponent().getColor());
    }

    @Test
    void canCastleMove() {
        Piece whiteKing = new King(POSITIONS[7][4], Color.WHITE,true);
        Piece whiteRook = new Rook(POSITIONS[7][7], Color.WHITE, true);
        Piece whitePawn1 = new Pawn(POSITIONS[6][4], Color.WHITE, true);
        Piece whitePawn2 = new Pawn(POSITIONS[6][5], Color.WHITE, true);
        Piece whitePawn3 = new Pawn(POSITIONS[6][6], Color.WHITE, true);
        Piece whitePawn4 = new Pawn(POSITIONS[6][7], Color.WHITE, true);
        Piece blackKing = new King(POSITIONS[0][4], Color.BLACK, true);
        Piece blackQueen = new Queen(POSITIONS[0][3], Color.BLACK, true);
        Board board = new Board.BoardBuilder().setStartingPlayer()
                .placePiece(whiteKing).placePiece(whiteRook)
                .placePiece(whitePawn1).placePiece(whitePawn2)
                .placePiece(whitePawn3).placePiece(whitePawn4)
                .placePiece(blackKing).placePiece(blackQueen)
                .build();

        KingSideCastling whiteKingSideCastle = new KingSideCastling(board, whiteKing, whiteRook, POSITIONS[7][6], POSITIONS[7][5]);
        assertEquals(whiteKingSideCastle, board.getWhitePlayer().calculateCastlingMoves().get(0));
    }

    @Test
    void getOpponent() {
    }

    @Test
    void testToString() {
    }

    @Test
    void getEnPassantPawn() {
    }

    @Test
    void getAvailableMoves() {
    }
}