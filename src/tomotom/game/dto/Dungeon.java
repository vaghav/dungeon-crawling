package tomotom.game.dto;

import java.util.HashMap;
import java.util.Map;

public class Dungeon {
    private Map<Room, Map<DoorDirection, Room>> roomMap = new HashMap<>();

    public Dungeon(Map<Room, Map<DoorDirection, Room>> roomMap) {
        this.roomMap = roomMap;
    }

    public Map<Room, Map<DoorDirection, Room>> getRoomMap() {
        return roomMap;
    }

    public void setRoomMap(Map<Room, Map<DoorDirection, Room>> roomMap) {
        this.roomMap = roomMap;
    }
}
