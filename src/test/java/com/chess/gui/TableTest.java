package com.chess.gui;

import org.junit.jupiter.api.Test;


class TableTest {

    @Test
    public void testSingleTon() {
        Table table1 = Table.getInstance();
        Table table2 = Table.getInstance();
        assert table1 == table2;
    }

}