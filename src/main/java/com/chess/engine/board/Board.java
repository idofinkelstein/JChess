package com.chess.engine.board;

import com.chess.engine.move.Move;
import com.chess.engine.move.MoveUtils;
import com.chess.engine.piece.*;
import com.chess.engine.piece.Color;
import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.Player;
import com.chess.engine.player.WhitePlayer;
import lombok.Getter;


import java.awt.Point;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    public static final int BOARD_SIZE = 8;
    private final Tile[][] gameBoard = new Tile[BOARD_SIZE][BOARD_SIZE];
    private Player activePlayer;
    private final BlackPlayer blackPlayer;
    private final WhitePlayer whitePlayer;

    @Getter
    private final List<Move> availableMoves;

    private Board(BoardBuilder builder) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {

                Point position = new Point(x, y);
                if (builder.PieceOnTileMap.containsKey(position)) {
                    gameBoard[x][y] = new OccupiedTile(x, y, builder.PieceOnTileMap.get(position));
                } else {
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

        availableMoves = Stream.concat(whitePlayer.getAvailableMoves().stream(), blackPlayer.getAvailableMoves().stream()).toList();

        activePlayer = (builder.getActivePlayer() == Color.WHITE) ? whitePlayer : blackPlayer;
    }

    private List<Move> calculateCastleMoves() {
        // Conditions:
        // 1. King and rook hasn't moved yet
        // 2. King isn't threatened currently
        // 3. King will not be threatened after castling ends
        // 4. King can't cross tiles under threat
        // 5. There is a clear path between king and rook

        King whiteKing = whitePlayer.getKing();
        King blackKing = blackPlayer.getKing();

        // calculate white king's castle moves
        if (whiteKing.isFirstMove()
                && !whitePlayer.isInCheck()
                && getTile(7, 7).isOccupied()
                && getTile(7, 7).getPiece() instanceof Rook rook
                && rook.isFirstMove()) {                                                                             // rook is first move

            Tile involvedTile1 = getTile(7, 5);
            Tile involvedTile2 = getTile(7, 6);
            if (!involvedTile1.isOccupied() && Player.calculateAttackOnTile(involvedTile1.getPosition(), blackPlayer.getAvailableMoves()).isEmpty()
                    && !involvedTile2.isOccupied() && Player.calculateAttackOnTile(involvedTile2.getPosition(), blackPlayer.getAvailableMoves()).isEmpty()) {
                System.out.println();
            }


        }
        return null;
    }

    private static String prettyPrint(Tile tile) {
        if (tile.isOccupied()) {
            return (tile.getPiece().getColor() == Color.BLACK)
                    ? tile.getPiece().toString()
                    : tile.getPiece().toString().toLowerCase();
        }
        return "-";
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

    public Player getCurrentPlayer() {
        return activePlayer;
    }

    public Player getOpponent() {
        return activePlayer.getOpponent();
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

    private List<Move> calculateAvailableMoves(List<Piece> pieces) {
        List<Move> availableMoves = new ArrayList<>();
        MoveVisitor moveVisitor = new MoveVisitorImpl();

        for (Piece piece : pieces) {
            availableMoves.addAll(piece.accept(moveVisitor, this));
        }

        // TODO consider use forEach loop instead of for loop and remember to delete this line
//        pieces.forEach(piece -> availableMoves.addAll(piece.accept(moveVisitor, this)));

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

    public static class BoardBuilder {

        private final Map<Point, Piece> PieceOnTileMap = new HashMap<>();
        @Getter
        private Color activePlayer;


        public Board build() {
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
            placePiece(new Rook(new Point(0, 0), Color.BLACK, true));
            placePiece(new Knight(new Point(0, 1), Color.BLACK, true));
            placePiece(new Bishop(new Point(0, 2), Color.BLACK, true));
            placePiece(new Queen(new Point(0, 3), Color.BLACK, true));
            placePiece(new King(new Point(0, 4), Color.BLACK, true));
            placePiece(new Bishop(new Point(0, 5), Color.BLACK, true));
            placePiece(new Knight(new Point(0, 6), Color.BLACK, true));
            placePiece(new Rook(new Point(0, 7), Color.BLACK, true));
            placePiece(new Pawn(new Point(1, 0), Color.BLACK, true));
            placePiece(new Pawn(new Point(1, 1), Color.BLACK, true));
            placePiece(new Pawn(new Point(1, 2), Color.BLACK, true));
            placePiece(new Pawn(new Point(1, 3), Color.BLACK, true));
            placePiece(new Pawn(new Point(1, 4), Color.BLACK, true));
            placePiece(new Pawn(new Point(1, 5), Color.BLACK, true));
            placePiece(new Pawn(new Point(1, 6), Color.BLACK, true));
            placePiece(new Pawn(new Point(1, 7), Color.BLACK, true));
            placePiece(new Pawn(new Point(6, 0), Color.WHITE, true));
            placePiece(new Pawn(new Point(6, 1), Color.WHITE, true));
            placePiece(new Pawn(new Point(6, 2), Color.WHITE, true));
            placePiece(new Pawn(new Point(6, 3), Color.WHITE, true));
            placePiece(new Pawn(new Point(6, 4), Color.WHITE, true));
            placePiece(new Pawn(new Point(6, 5), Color.WHITE, true));
            placePiece(new Pawn(new Point(6, 6), Color.WHITE, true));
            placePiece(new Pawn(new Point(6, 7), Color.WHITE, true));
            placePiece(new Rook(new Point(7, 0), Color.WHITE, true));
            placePiece(new Knight(new Point(7, 1), Color.WHITE, true));
            placePiece(new Bishop(new Point(7, 2), Color.WHITE, true));
            placePiece(new Queen(new Point(7, 3), Color.WHITE, true));
            placePiece(new King(new Point(7, 4), Color.WHITE, true));
            placePiece(new Bishop(new Point(7, 5), Color.WHITE, true));
            placePiece(new Knight(new Point(7, 6), Color.WHITE, true));
            placePiece(new Rook(new Point(7, 7), Color.WHITE, true));
            return this;
        }

        public BoardBuilder placePiecesExcluding(List<Piece> pieces, List<Piece> excludedPieces) {
            for (Piece piece : pieces) {
                if (excludedPieces == null || !excludedPieces.contains(piece)) {
                    PieceOnTileMap.put(piece.getPosition(), piece);
                }
            }
            return this;
        }
    }
}
