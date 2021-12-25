package tomotom.game;

import tomotom.game.dto.DoorDirection;
import tomotom.game.dto.Dungeon;
import tomotom.game.dto.Room;

import java.io.IOException;
import java.util.Set;

public interface DungeonCrawling {

    /**
     * Construct dungeon by given input data.
     *
     * @param fileName the given file name.
     * @return the constructed dungeon.
     */
    Dungeon constructDungeon(String fileName) throws IOException, InvalidDataException;

    /**
     * Navigate across rooms by given direction.
     *
     * @param room          the room from move to.
     * @param doorDirection the direction of the movement.
     * @return the room entered by direction.
     * @throws IllegalArgumentException if no exit from the room in @param doorDirection.
     */
    Room move(Room room, DoorDirection doorDirection) throws IllegalArgumentException, NoRoomExistsException;

    /**
     * Draw dungeon in graphical way.
     *
     * @param dungeon the dungeon to display.
     */
    void displayDungeon(Dungeon dungeon);

    /**
     * Display available moves from current room.
     *
     * @param room the current room to move from.
     * @return available moves.
     */
    Set<DoorDirection> displayAvailableMoves(Room room) throws NoRoomExistsException;

    /**
     * Find shortest path between rooms.
     *
     * @param sourceRoom      the room to start.
     * @param destinationRoom the destination room.
     */
    void findShortestPath(Room sourceRoom, Room destinationRoom);
}
