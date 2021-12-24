package tomotom.game;

import tomotom.game.dto.DoorDirection;
import tomotom.game.dto.Dungeon;
import tomotom.game.dto.Room;

import java.io.IOException;
import java.util.Set;

public interface DungeonCrawling {

    /**
     *  Construct dungeon by given input data.
     * @param fileName the given file name.
     * @return the constructed dungeon.
     */
    public Dungeon constructDungeon(String fileName) throws IOException, InvalidDataException;

    /**
     * Navigate across rooms by given direction.
     * @param doorDirection represents direction of movement.
     * @throws IllegalStateException if no exit from the room in @param doorDirection.
     */
    public void move(DoorDirection doorDirection) throws IllegalStateException;

    /**
     * Draw dungeon in graphical way.
     * @param dungeon the dungeon to display.
     */
    public void displayDungeon(Dungeon dungeon);

    /**
     * Display available moves from current room.
     * @param room the current room to move from.
     * @return available moves.
     */
    public Set<DoorDirection> displayAvailableMoves(Room room);

    /**
     * Find shortest path between rooms.
     * @param sourceRoom the room to start.
     * @param destinationRoom the destination room.
     */
    public void findShortestPath(Room sourceRoom, Room destinationRoom);
}
