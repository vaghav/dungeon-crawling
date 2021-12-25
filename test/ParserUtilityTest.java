import org.junit.jupiter.api.Test;
import tomotom.game.dto.DoorDirection;
import tomotom.game.dto.Room;
import util.ParserUtility;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserUtilityTest {

    @Test
    void construct_dungeon_from_file() throws Exception {
        Map<Room, Map<DoorDirection, Room>> roomMap = ParserUtility.parse("adjacent-rooms.txt");

        Map<DoorDirection, Room> firstExit = new HashMap<>();
        firstExit.put(DoorDirection.NORTH, new Room("a3"));
        assertThat(roomMap.get(new Room("a0"))).isEqualTo(firstExit);
        Map<DoorDirection, Room> exit = new HashMap<>();
        exit.put(DoorDirection.EAST, new Room("a2"));
        exit.put(DoorDirection.SOUTH, new Room("a3"));
        assertThat(roomMap.get(new Room("a1"))).isEqualTo(exit);
    }
}
