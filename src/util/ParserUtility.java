package util;

import tomotom.game.InvalidDataException;
import tomotom.game.dto.DoorDirection;
import tomotom.game.dto.Dungeon;
import tomotom.game.dto.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

public final class ParserUtility {

    private static final Pattern pattern = Pattern.compile("^([a-b0-9]{2})(( [n|s|e|w]:[a-b0-9]{2})+)$");

    private ParserUtility() {
    }

    public static Map<Room, Map<DoorDirection, Room>> parse(String fileName)
            throws IOException, InvalidDataException {
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
        return roomMap;
    }
}
