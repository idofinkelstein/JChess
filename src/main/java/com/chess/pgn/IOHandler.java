package com.chess.pgn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOHandler {

    public static void saveGame(String gameText, Path fileName) throws IOException {

        byte[] strToBytes = gameText.getBytes();

        Files.write(fileName, strToBytes);
    }

    public static String loadGame(String fileName) throws IOException {
        return Files.readString(Path.of(fileName));
    }
}
