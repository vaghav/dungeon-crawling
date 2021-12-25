package tomotom.game;

import tomotom.game.dto.DoorDirection;
import tomotom.game.dto.Dungeon;
import tomotom.game.dto.Room;
import util.Command;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class DungeonEscape {

    public static void main(String[] args) throws IOException, InvalidDataException {
        DungeonCrawling dungeonCrawling = new DungeonCrawlingImpl();
        Dungeon dungeon = dungeonCrawling.constructDungeon("file.txt");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose one of the actions: DISPLAY, MOVE, AVAILABLE_EXITS, EXIT");
        while (!scanner.hasNext("EXIT")) {
            String enteredCommand = scanner.next();
            switch (Command.valueOf(enteredCommand)) {
                case DISPLAY:
                    dungeonCrawling.displayDungeon(dungeon);
                    break;
                case MOVE:
                    System.out.println("Please enter the room name");
                    String roomName = scanner.next();
                    System.out.println("Please enter the direction to move");
                    String direction = scanner.next();
                    try {
                        Room enteredRoom = dungeonCrawling.move(new Room(roomName), DoorDirection.valueOf(direction));
                        System.out.println("You entered the room: " + enteredRoom.getName());
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Please provide a valid direction name");
                    } catch (NoRoomExistsException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case AVAILABLE_EXITS:
                    System.out.println("Please enter the room name");
                    String rName = scanner.next();
                    try {
                        Set<DoorDirection> directions = dungeonCrawling.displayAvailableMoves(new Room(rName));
                        System.out.printf("Available directions from %s are: %s%n", rName, directions.toString());
                    } catch (NoRoomExistsException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case SHORTEST_PATH:
                    //TODO: Implement this case.
            }
            System.out.println("Please choose one of the actions: DISPLAY, MOVE, AVAILABLE_EXITS");
        }
    }
}

