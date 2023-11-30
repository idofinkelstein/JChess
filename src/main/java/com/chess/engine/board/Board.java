package com.chess.engine.board;

import com.chess.engine.move.Move;
import com.chess.engine.piece.*;
import com.chess.engine.piece.Color;
import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.Player;
import com.chess.engine.player.WhitePlayer;


import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Board {

    public static final int BOARD_SIZE = 8;
    private final Tile[][] gameBoard = new Tile[BOARD_SIZE][BOARD_SIZE];
    private  Player activePlayer;
    private BlackPlayer blackPlayer;
    private WhitePlayer whitePlayer;

    private Board(BoardBuilder builder) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                Point position = new Point(x, y);
                if (builder.PieceOnTileMap.containsKey(position)) {
                    gameBoard[x][y] = new OccupiedTile(x, y, builder.PieceOnTileMap.get(position));
                }
                else {
                    gameBoard[x][y] = new EmptyTile(x, y, null);
                }
            }
        }
        List<Piece> whitePieces = calculateAvailablePieces(Color.WHITE);
        List<Piece> blackPieces = calculateAvailablePieces(Color.BLACK);
        List<Move> whiteMoves = calculateAvailableMoves(whitePieces);
        List<Move> blackMoves = calculateAvailableMoves(blackPieces);
        whitePlayer = new WhitePlayer(this, whitePieces, whiteMoves, blackMoves);
        blackPlayer = new BlackPlayer(this, blackPieces, blackMoves, whiteMoves);
    }

    private List<Move> calculateAvailableMoves(List<Piece> pieces) {
        List<Move> availableMoves = new ArrayList<>();
        MoveVisitor moveVisitor = new MoveVisitorImpl();

        for (Piece piece : pieces) {
            availableMoves.addAll(piece.accept(moveVisitor, this));
        }
        return availableMoves;
    }

    private List<Piece> calculateAvailablePieces(Color color) {
        List<Piece> availablePieces = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (gameBoard[i][j].isOccupied() && gameBoard[i][j].getPiece().getColor().equals(color)) {
                    availablePieces.add(gameBoard[i][j].getPiece());
                }
            }
        }
        return availablePieces;
    }

    public Tile getTile(int x, int y) {
        return gameBoard[x][y];
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                String s = prettyPrint(gameBoard[x][y]);
                builder.append(String.format("%3s", s));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private static String prettyPrint(Tile tile) {
        if (tile.isOccupied()) {
            return (tile.getPiece().getColor() == Color.BLACK)
                    ? tile.getPiece().toString()
                    : tile.getPiece().toString().toLowerCase();
        }
        return "-";
    }

    public Player getCurrentPlayer() {
        return null;
    }

    public Player getOpponent() {
        return null;
    }


    public static class BoardBuilder {

        private final Map<Point, Piece> PieceOnTileMap = new HashMap<>();
        private Color activePlayer;


        public Board build() {
//            populateMap();
            return new Board(this);
        }

        public BoardBuilder placePiece(Piece piece) {
            PieceOnTileMap.put(piece.getPosition(), piece);
            return this;
        }

        public BoardBuilder setStartingPlayer() {
            this.activePlayer = Color.WHITE;
            return this;
        }

        public BoardBuilder setActivePlayer(Color activePlayer) {
            this.activePlayer = activePlayer;
            return this;
        }

        public BoardBuilder populateMap() {
            placePiece(new Rook(new Point(0, 0), Color.BLACK));
            placePiece(new Knight(new Point(0, 1), Color.BLACK));
            placePiece(new Bishop(new Point(0, 2), Color.BLACK));
            placePiece(new Queen(new Point(0, 3), Color.BLACK));
            placePiece(new King(new Point(0, 4), Color.BLACK));
            placePiece(new Bishop(new Point(0, 5), Color.BLACK));
            placePiece(new Knight(new Point(0, 6), Color.BLACK));
            placePiece(new Rook(new Point(0, 7), Color.BLACK));
            placePiece(new Pawn(new Point(1, 0), Color.BLACK));
            placePiece(new Pawn(new Point(1, 1), Color.BLACK));
            placePiece(new Pawn(new Point(1, 2), Color.BLACK));
            placePiece(new Pawn(new Point(1, 3), Color.BLACK));
            placePiece(new Pawn(new Point(1, 4), Color.BLACK));
            placePiece(new Pawn(new Point(1, 5), Color.BLACK));
            placePiece(new Pawn(new Point(1, 6), Color.BLACK));
            placePiece(new Pawn(new Point(1, 7), Color.BLACK));
            placePiece(new Pawn(new Point(6, 0), Color.WHITE));
            placePiece(new Pawn(new Point(6, 1), Color.WHITE));
            placePiece(new Pawn(new Point(6, 2), Color.WHITE));
            placePiece(new Pawn(new Point(6, 3), Color.WHITE));
            placePiece(new Pawn(new Point(6, 4), Color.WHITE));
            placePiece(new Pawn(new Point(6, 5), Color.WHITE));
            placePiece(new Pawn(new Point(6, 6), Color.WHITE));
            placePiece(new Pawn(new Point(6, 7), Color.WHITE));
            placePiece(new Rook(new Point(7, 0), Color.WHITE));
            placePiece(new Knight(new Point(7, 1), Color.WHITE));
            placePiece(new Bishop(new Point(7, 2), Color.WHITE));
            placePiece(new Queen(new Point(7, 3), Color.WHITE));
            placePiece(new King(new Point(7, 4), Color.WHITE));
            placePiece(new Bishop(new Point(7, 5), Color.WHITE));
            placePiece(new Knight(new Point(7, 6), Color.WHITE));
            placePiece(new Rook(new Point(7, 7), Color.WHITE));
            return this;
        }

    }
}
