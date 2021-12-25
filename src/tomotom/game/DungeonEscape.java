package tomotom.game;

import tomotom.game.dto.DoorDirection;
import tomotom.game.dto.Dungeon;
import tomotom.game.dto.Room;
import util.Command;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class DungeonEscape {

    public static void main(String[] args) {
        DungeonCrawling dungeonCrawling = new DungeonCrawlingImpl();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose one of the actions: DISPLAY, MOVE, AVAILABLE_EXITS, TERMINATE");
        while (!scanner.hasNext("TERMINATE")) {
            String enteredCommand = scanner.next();
            try {
                switch (Command.valueOf(enteredCommand)) {
                    case DISPLAY:
                        dungeonCrawling.displayDungeon();
                        break;
                    case MOVE:
                        System.out.println("Please enter the direction to move");
                        String direction = scanner.next();
                        try {
                            Room enteredRoom = dungeonCrawling.move(DoorDirection.valueOf(direction));
                            System.out.println("You entered the room: " + enteredRoom.getName());
                        } catch (IllegalArgumentException ex) {
                            System.out.println("Please provide a valid direction name");
                        }
                        break;
                    case AVAILABLE_EXITS:
                        Set<DoorDirection> directions = dungeonCrawling.displayAvailableMoves();
                        System.out.printf("Available directions from the current room '%s' are: %s%n",
                                dungeonCrawling.getCurrentRoomName(),
                                directions.toString());
                        break;
                    case SHORTEST_PATH:
                        //TODO: Implement this case.
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Please provide ą valid action");
            }
            System.out.println("Please choose one of the actions: DISPLAY, MOVE, AVAILABLE_EXITS, TERMINATE");
        }
    }
}

