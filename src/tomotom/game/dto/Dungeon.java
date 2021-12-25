package tomotom.game.dto;

import tomotom.game.InvalidDataException;
import util.ParserUtility;

import java.io.IOException;
import java.util.Map;

public class Dungeon {
    private Map<Room, Map<DoorDirection, Room>> roomMap;

    public Dungeon()  {
        try {
            this.roomMap = ParserUtility.parse("adjacent-rooms.txt");
        } catch (IOException | InvalidDataException e) {
            throw new RuntimeException("Invalid data was provided!");
        }
    }

    public Dungeon(Map<Room, Map<DoorDirection, Room>> roomMap) {
        this.roomMap = roomMap;
    }

    public Map<Room, Map<DoorDirection, Room>> getRoomMap() {
        return roomMap;
    }
}
