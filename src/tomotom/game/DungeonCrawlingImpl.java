package tomotom.game;

import tomotom.game.dto.DoorDirection;
import tomotom.game.dto.Dungeon;
import tomotom.game.dto.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

public class DungeonCrawlingImpl implements DungeonCrawling {

    private final Pattern pattern = Pattern.compile("^([a-b0-9]{2})(( [n|s|e|w]:[a-b0-9]{2})+)$");
    private final Dungeon dungeon = new Dungeon(new HashMap<>());

    @Override
    public Dungeon constructDungeon(String fileName) throws IOException, InvalidDataException {
        Map<Room, Map<DoorDirection, Room>> roomMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (!matcher.matches()) {
                    throw new InvalidDataException("Invalid input data is provided!");
                }
                //TODO: Use group names instead of group indexes.
                String room = matcher.group(1).trim();
                String[] neighbours = matcher.group(2).trim().split(" ");
                Map<DoorDirection, Room> directionRoomMap = Arrays.stream(neighbours)
                        .collect(toMap(item -> DoorDirection.fromString(item.split(":")[0]),
                                item -> new Room(item.split(":")[1])));
                roomMap.put(new Room(room), directionRoomMap);
            }
        }
        dungeon.setRoomMap(roomMap);
        return dungeon;
    }

    @Override
    public void move(DoorDirection doorDirection) throws IllegalStateException {

    }

    @Override
    public void displayDungeon(Dungeon dungeon) {
        int roomCount = dungeon.getRoomMap().keySet().size();
        String[][] dungeonMap = new String[4 * roomCount - 2][4 * roomCount - 2];

        for (String[] value : dungeonMap) {
            Arrays.fill(value, "  ");
        }

        int startX = dungeonMap.length / 2;
        int startY = dungeonMap.length / 2;
        dungeonMap[startX][startY] = "a0";
        Set<Room> drawnRooms = new HashSet<>();

        Room startingRoom = new Room("a0");
        drawnRooms.add(startingRoom);
        drawDungeon(startY, startY, startingRoom, dungeon, drawnRooms, dungeonMap);

        for (String[] strings : dungeonMap) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
        //TODO: Clean map i.e. clean unused rows and coulmns.
    }

    private void drawDungeon(int row, int column, Room room, Dungeon dungeon,
                             Set<Room> drawn, String[][] dungeonMap) {
        Map<DoorDirection, Room> doorDirectionRoomMap = dungeon.getRoomMap().get(room);
        for (Map.Entry<DoorDirection, Room> entry : doorDirectionRoomMap.entrySet()) {
            Room currentRoom = entry.getValue();
            if (!drawn.contains(currentRoom)) {
                drawn.add(currentRoom);
                DoorDirection doorDirection = entry.getKey();
                if (doorDirection == DoorDirection.SOUTH) {
                    dungeonMap[column + 1][row] = "| ";
                    dungeonMap[column + 2][row] = currentRoom.getName();
                    drawDungeon(row, column + 2, currentRoom, dungeon, drawn, dungeonMap);
                } else if (doorDirection == DoorDirection.NORTH) {
                    dungeonMap[column - 1][row] = "| ";
                    dungeonMap[column - 2][row] = currentRoom.getName();
                    drawDungeon(row, column - 2, currentRoom, dungeon, drawn, dungeonMap);
                } else if (doorDirection == DoorDirection.EAST) {
                    dungeonMap[column][row + 1] = "--";
                    dungeonMap[column][row + 2] = currentRoom.getName();
                    drawDungeon(row + 2, column, currentRoom, dungeon, drawn, dungeonMap);
                } else if (doorDirection == DoorDirection.WEST) {
                    dungeonMap[column][row - 1] = "--";
                    dungeonMap[column][row - 2] = currentRoom.getName();
                    drawDungeon(row - 2, column, currentRoom, dungeon, drawn, dungeonMap);
                }
            }
        }
    }


    @Override
    public Set<DoorDirection> displayAvailableMoves(Room room) {
        if (dungeon.getRoomMap().get(room) == null) {
            throw new IllegalArgumentException("Invalid room was provided");
        }
        return dungeon.getRoomMap().get(room).keySet();
    }

    @Override
    public void findShortestPath(Room sourceRoom, Room destinationRoom) {
        // TODO: Implement Dijkstra's algorithm.
    }
}
