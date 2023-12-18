package com.chess.gui;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import com.chess.engine.piece.Color;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameHistoryPanel extends JPanel {
    private final DataModel model;
    private final JScrollPane scrollPane;
    private static final Dimension HISTORY_PANEL_DIMENSION = new Dimension(100, 400);

    public GameHistoryPanel() {
        setLayout(new BorderLayout());
        model = new DataModel();
        final JTable table = new JTable(model);
        table.setRowHeight(15);
        scrollPane = new JScrollPane(table);
        scrollPane.setColumnHeaderView(table.getTableHeader());
        scrollPane.setPreferredSize(HISTORY_PANEL_DIMENSION);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public void redo(final Board board, final MoveLog moveLog) {
        int currentRow = 0;
        model.clear();

        for (final Move move : moveLog.getMoves()) {
            final String moveText = move.toString();
            if (move.getMovedPiece().getColor().equals(Color.WHITE)) {
                model.setValueAt(moveText, currentRow, 0);
            } else {
                model.setValueAt(moveText, currentRow, 1);
                currentRow++;
            }
        }
        if (!moveLog.getMoves().isEmpty()) {
            final Move lastMove = moveLog.getMoves().get(moveLog.getMoves().size() - 1);
            final String moveText = lastMove.toString();

            if (lastMove.getMovedPiece().getColor().equals(Color.WHITE)) {
                model.setValueAt(moveText + calculateCheckAndCheckMateHash(board), currentRow, 0);
            } else if (lastMove.getMovedPiece().getColor().equals(Color.BLACK)) {
                model.setValueAt(moveText + calculateCheckAndCheckMateHash(board), currentRow - 1, 1);
            }
        }

        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    private String calculateCheckAndCheckMateHash(final Board board) {
        if (board.getActivePlayer().isInCheckMate()) {
            return "#";
        } else if (board.getActivePlayer().isInCheck()) {
            return "+";
        }
        return "";
    }


    public static class DataModel extends DefaultTableModel {
        private final List<Row> values;
        private static final String[] NAMES = {"White", "Black"};

        public DataModel() {
            values = new ArrayList<>();
        }

        public void clear() {
            values.clear();
            setRowCount(0);
        }

        @Override
        public int getRowCount() {
            if (values == null) {
                return 0;
            }
            return values.size();
        }

        @Override
        public int getColumnCount() {
            return NAMES.length;
        }

        @Override
        public String getValueAt(final int row, final int column) {
            final Row currentRow = values.get(row);
            if (column == 0) {
                return currentRow.getWhiteMove();
            } else if (column == 1) {
                return currentRow.getBlackMove();
            }
            return null;
        }

        @Override
        public void setValueAt(final Object aValue, final int row, final int column) {
            final Row currentRow;
            if (values.size() <= row) {
                currentRow = new Row();
                values.add(currentRow);
            } else {
                currentRow = values.get(row);
            }
            if (column == 0) {
                currentRow.setWhiteMove((String) aValue);
                fireTableRowsInserted(row, row);
            } else if (column == 1) {
                currentRow.setBlackMove((String) aValue);
                fireTableCellUpdated(row, column);
            }
        }

        @Override
        public Class<?> getColumnClass(final int column) {
            return Move.class;
        }

        @Override
        public String getColumnName(final int column) {
            return NAMES[column];
        }
    }

    @Getter
    @Setter
    public static class Row {

        private String whiteMove;
        private String blackMove;
        public Row() {}
    }
}
