package com.chess.engine.board;

import com.chess.engine.move.CastlingMove;
import com.chess.engine.move.KingSideCastling;
import com.chess.engine.piece.*;
import com.chess.engine.player.Player;
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
        Piece whiteKing = new King(POSITIONS[7][4], Color.WHITE,true, true, true);
        Piece whiteRook = new Rook(POSITIONS[7][7], Color.WHITE, true);
        Piece whitePawn1 = new Pawn(POSITIONS[6][4], Color.WHITE, true);
        Piece whitePawn2 = new Pawn(POSITIONS[6][5], Color.WHITE, true);
        Piece whitePawn3 = new Pawn(POSITIONS[6][6], Color.WHITE, true);
        Piece whitePawn4 = new Pawn(POSITIONS[6][7], Color.WHITE, true);
        Piece blackKing = new King(POSITIONS[0][4], Color.BLACK, true, true, true);
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
        Player currentPlayer = board.getActivePlayer();
        assertNotEquals(currentPlayer.getColor(), currentPlayer.getOpponent().getColor());
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

    @Test
    void testStandardBoard() {
        // Test all black pieces are in place
        assertEquals(new Rook(POSITIONS[0][0], Color.BLACK, true), board.getTile(0, 0).getPiece());
        assertEquals(new Knight(POSITIONS[0][1], Color.BLACK, true), board.getTile(0, 1).getPiece());
        assertEquals(new Bishop(POSITIONS[0][2], Color.BLACK, true), board.getTile(0, 2).getPiece());
        assertEquals(new Queen(POSITIONS[0][3], Color.BLACK, true), board.getTile(0, 3).getPiece());
        assertEquals(new King(POSITIONS[0][4], Color.BLACK, true, true, true), board.getTile(0, 4).getPiece());
        assertEquals(new Bishop(POSITIONS[0][5], Color.BLACK, true), board.getTile(0, 5).getPiece());
        assertEquals(new Knight(POSITIONS[0][6], Color.BLACK, true), board.getTile(0, 6).getPiece());
        assertEquals(new Rook(POSITIONS[0][7], Color.BLACK, true), board.getTile(0, 7).getPiece());
        assertEquals(new Pawn(POSITIONS[1][0], Color.BLACK, true), board.getTile(1, 0).getPiece());
        assertEquals(new Pawn(POSITIONS[1][1], Color.BLACK, true), board.getTile(1, 1).getPiece());
        assertEquals(new Pawn(POSITIONS[1][2], Color.BLACK, true), board.getTile(1, 2).getPiece());
        assertEquals(new Pawn(POSITIONS[1][3], Color.BLACK, true), board.getTile(1, 3).getPiece());
        assertEquals(new Pawn(POSITIONS[1][4], Color.BLACK, true), board.getTile(1, 4).getPiece());
        assertEquals(new Pawn(POSITIONS[1][5], Color.BLACK, true), board.getTile(1, 5).getPiece());
        assertEquals(new Pawn(POSITIONS[1][6], Color.BLACK, true), board.getTile(1, 6).getPiece());
        assertEquals(new Pawn(POSITIONS[1][7], Color.BLACK, true), board.getTile(1, 7).getPiece());
        // Test all white pieces are in place
        assertEquals(new Pawn(POSITIONS[6][0], Color.WHITE, true), board.getTile(6, 0).getPiece());
        assertEquals(new Pawn(POSITIONS[6][1], Color.WHITE, true), board.getTile(6, 1).getPiece());
        assertEquals(new Pawn(POSITIONS[6][2], Color.WHITE, true), board.getTile(6, 2).getPiece());
        assertEquals(new Pawn(POSITIONS[6][3], Color.WHITE, true), board.getTile(6, 3).getPiece());
        assertEquals(new Pawn(POSITIONS[6][4], Color.WHITE, true), board.getTile(6, 4).getPiece());
        assertEquals(new Pawn(POSITIONS[6][5], Color.WHITE, true), board.getTile(6, 5).getPiece());
        assertEquals(new Pawn(POSITIONS[6][6], Color.WHITE, true), board.getTile(6, 6).getPiece());
        assertEquals(new Pawn(POSITIONS[6][7], Color.WHITE, true), board.getTile(6, 7).getPiece());
        assertEquals(new Rook(POSITIONS[7][0], Color.WHITE, true), board.getTile(7, 0).getPiece());
        assertEquals(new Knight(POSITIONS[7][1], Color.WHITE, true), board.getTile(7, 1).getPiece());
        assertEquals(new Bishop(POSITIONS[7][2], Color.WHITE, true), board.getTile(7, 2).getPiece());
        assertEquals(new Queen(POSITIONS[7][3], Color.WHITE, true), board.getTile(7, 3).getPiece());
        assertEquals(new King(POSITIONS[7][4], Color.WHITE, true, true, true), board.getTile(7, 4).getPiece());
        assertEquals(new Bishop(POSITIONS[7][5], Color.WHITE, true), board.getTile(7, 5).getPiece());
        assertEquals(new Knight(POSITIONS[7][6], Color.WHITE, true), board.getTile(7, 6).getPiece());
        assertEquals(new Rook(POSITIONS[7][7], Color.WHITE, true), board.getTile(7, 7).getPiece());

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                assertFalse(board.getTile(i, j).isOccupied());
            }
        }
        assertEquals(Color.WHITE, board.getActivePlayer().getColor());
        assertEquals(Color.BLACK, board.getActivePlayer().getOpponent().getColor());
        assertFalse(board.getActivePlayer().isInCheck());
        assertFalse(board.getActivePlayer().isInCheckMate());
        assertFalse(board.getActivePlayer().isInStalemate());
        assertFalse(board.getActivePlayer().getOpponent().isInCheck());
        assertFalse(board.getActivePlayer().getOpponent().isInCheckMate());
        assertFalse(board.getActivePlayer().getOpponent().isInStalemate());
        System.out.println(board);
    }
}