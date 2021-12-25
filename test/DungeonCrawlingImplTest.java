import org.junit.jupiter.api.Test;
import tomotom.game.DungeonCrawling;
import tomotom.game.DungeonCrawlingImpl;
import tomotom.game.NoRoomExistsException;
import tomotom.game.dto.DoorDirection;
import tomotom.game.dto.Dungeon;
import tomotom.game.dto.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class DungeonCrawlingImplTest {

    @Test
    void construct_dungeon_from_file() throws Exception {
        DungeonCrawling dungeonCrawling = new DungeonCrawlingImpl();
        Dungeon dungeon = dungeonCrawling.constructDungeon("file.txt");

        Map<DoorDirection, Room> firstExit = new HashMap<>();
        firstExit.put(DoorDirection.NORTH, new Room("a3"));
        assertThat(dungeon.getRoomMap().get(new Room("a0"))).isEqualTo(firstExit);
        Map<DoorDirection, Room> exit = new HashMap<>();
        exit.put(DoorDirection.EAST, new Room("a2"));
        exit.put(DoorDirection.SOUTH, new Room("a3"));
        assertThat(dungeon.getRoomMap().get(new Room("a1"))).isEqualTo(exit);
    }


    @Test
    void move_by_direction() throws Exception {
        DungeonCrawling dungeonCrawling = new DungeonCrawlingImpl();
        dungeonCrawling.constructDungeon("file.txt");

        Room actualRoom = dungeonCrawling.move(new Room("a0"), DoorDirection.NORTH);

        assertThat(actualRoom).isEqualTo(new Room("a3"));
    }


    @Test
    void move_by_direction_throws_exception_invalid_direction() throws Exception {
        DungeonCrawling dungeonCrawling = new DungeonCrawlingImpl();
        dungeonCrawling.constructDungeon("file.txt");

        assertThatThrownBy(() ->
                dungeonCrawling.move(new Room("a0"), DoorDirection.SOUTH))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("There is no exit with the provided direction!");
    }


    @Test
    void display_dungeon() {
        DungeonCrawling dungeonCrawling = new DungeonCrawlingImpl();
        Map<Room, Map<DoorDirection, Room>> dungeonMap = new HashMap<>();

        Map<DoorDirection, Room> exit = new HashMap<>();
        exit.put(DoorDirection.NORTH, new Room("a3"));
        dungeonMap.put(new Room("a0"), exit);

        Map<DoorDirection, Room> exit1 = new HashMap<>();
        exit1.put(DoorDirection.EAST, new Room("a2"));
        exit1.put(DoorDirection.SOUTH, new Room("a3"));
        dungeonMap.put(new Room("a1"), exit1);

        Map<DoorDirection, Room> exit2 = new HashMap<>();
        exit2.put(DoorDirection.SOUTH, new Room("a5"));
        exit2.put(DoorDirection.WEST, new Room("a1"));
        dungeonMap.put(new Room("a2"), exit2);

        Map<DoorDirection, Room> exit3 = new HashMap<>();
        exit3.put(DoorDirection.NORTH, new Room("a1"));
        exit3.put(DoorDirection.SOUTH, new Room("a0"));
        exit3.put(DoorDirection.WEST, new Room("a4"));
        dungeonMap.put(new Room("a3"), exit3);

        Map<DoorDirection, Room> exit4 = new HashMap<>();
        exit4.put(DoorDirection.EAST, new Room("a3"));
        exit4.put(DoorDirection.SOUTH, new Room("b0"));
        dungeonMap.put(new Room("a4"), exit4);

        Dungeon dungeon = new Dungeon(dungeonMap);
        dungeonCrawling.displayDungeon(dungeon);
    }


    @Test
    void display_all_moves_from_room() throws Exception {
        DungeonCrawling dungeonCrawling = new DungeonCrawlingImpl();
        dungeonCrawling.constructDungeon("file.txt");
        Set<DoorDirection> directions = dungeonCrawling.displayAvailableMoves(new Room("a3"));

        assertThat(directions).containsAll(List.of(DoorDirection.WEST, DoorDirection.NORTH, DoorDirection.SOUTH));
    }

    @Test
    void throws_exception_invalid_room_name() throws Exception {
        DungeonCrawling dungeonCrawling = new DungeonCrawlingImpl();
        dungeonCrawling.constructDungeon("file.txt");

        assertThatThrownBy(() ->
                dungeonCrawling.displayAvailableMoves(new Room("b7")))
                .isInstanceOf(NoRoomExistsException.class)
                .hasMessageContaining("Please enter a valid room name");
    }


    @Test
    void findShortestPath() {
        //TODO: Implement unit test.
    }
}