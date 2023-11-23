package com.chess.engine.board;

import com.chess.engine.piece.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final int BOARD_SIZE = 8;
    private Tile[][] tiles = new Tile[BOARD_SIZE][BOARD_SIZE];

    private Board(BoardBuilder builder) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                Point position = new Point(x, y);
                if (builder.PieceOnTileMap.containsKey(position)) {
                    tiles[x][y] = new OccupiedTile(x, y, builder.PieceOnTileMap.get(position));
                }
                else {
                    tiles[x][y] = new EmptyTile(x, y, null);
                }
            }
        }

    }
    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }




    private class BoardBuilder {


        private final Map<Point, Piece> PieceOnTileMap = new HashMap<>();


        public Board build() {
            populateMap();
            return new Board(this);
        }

        public BoardBuilder placePiece(Piece piece) {
            PieceOnTileMap.put(piece.getPosition(), piece);
            return this;
        }

        public BoardBuilder populateMap() {
            placePiece(new Rook(new Point(0, 0)));
            placePiece(new Knight(new Point(0, 1)));
            placePiece(new Bishop(new Point(0, 2)));
            placePiece(new Queen(new Point(0, 3)));
            placePiece(new King(new Point(0, 4)));
            placePiece(new Bishop(new Point(0, 5)));
            placePiece(new Knight(new Point(0, 6)));
            placePiece(new Rook(new Point(0, 7)));
            placePiece(new Pawn(new Point(1, 0)));
            placePiece(new Pawn(new Point(1, 1)));
            placePiece(new Pawn(new Point(1, 2)));
            placePiece(new Pawn(new Point(1, 3)));
            placePiece(new Pawn(new Point(1, 4)));
            placePiece(new Pawn(new Point(1, 5)));
            placePiece(new Pawn(new Point(1, 6)));
            placePiece(new Pawn(new Point(1, 7)));
            placePiece(new Pawn(new Point(6, 0)));
            placePiece(new Pawn(new Point(6, 1)));
            placePiece(new Pawn(new Point(6, 2)));
            placePiece(new Pawn(new Point(6, 3)));
            placePiece(new Pawn(new Point(6, 4)));
            placePiece(new Pawn(new Point(6, 5)));
            placePiece(new Pawn(new Point(6, 6)));
            placePiece(new Pawn(new Point(6, 7)));
            placePiece(new Rook(new Point(7, 0)));
            placePiece(new Knight(new Point(7, 1)));
            placePiece(new Bishop(new Point(7, 2)));
            placePiece(new Queen(new Point(7, 3)));
            placePiece(new King(new Point(7, 4)));
            placePiece(new Bishop(new Point(7, 5)));
            placePiece(new Knight(new Point(7, 6)));
            placePiece(new Rook(new Point(7, 7)));
            return this;


        }

    }
}
