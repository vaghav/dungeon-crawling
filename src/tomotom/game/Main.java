package tomotom.game;

import tomotom.game.dto.Dungeon;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InvalidDataException {
        DungeonCrawling dungeonCrawling = new DungeonCrawlingImpl();
        Dungeon dungeon = dungeonCrawling.constructDungeon("file.txt");
        dungeonCrawling.displayDungeon(dungeon);
    }
}

