package tomotom.game;

import tomotom.game.dto.DoorDirection;
import tomotom.game.dto.Dungeon;
import tomotom.game.dto.Room;

import java.io.IOException;
import java.util.Set;

public interface DungeonCrawling {

    /**
     * Navigate from the current room with given direction.
     *
     * @param doorDirection the direction of the movement.
     * @return the room entered by direction.
     * @throws IllegalArgumentException if no exit from the room in @param doorDirection.
     */
    Room move(DoorDirection doorDirection) throws IllegalArgumentException;

    /**
     * Draw dungeon.
     */
    void displayDungeon();

    /**
     * Display available moves from the current room.
     *
     * @return available moves.
     */
    Set<DoorDirection> displayAvailableMoves();

    /**
     * Find shortest path between rooms.
     *
     * @param sourceRoom      the room to start.
     * @param destinationRoom the destination room.
     */
    void findShortestPath(Room sourceRoom, Room destinationRoom);

    /**
     *
     * @return the current room name.
     */
    String getCurrentRoomName();
}
