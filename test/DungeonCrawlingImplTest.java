import org.junit.jupiter.api.Test;
import tomotom.game.DungeonCrawling;
import tomotom.game.DungeonCrawlingImpl;
import tomotom.game.dto.DoorDirection;
import tomotom.game.dto.Room;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class DungeonCrawlingImplTest {


    @Test
    void move_by_direction() {
        DungeonCrawling dungeonCrawling = new DungeonCrawlingImpl();

        Room actualRoom = dungeonCrawling.move(DoorDirection.NORTH);

        assertThat(actualRoom).isEqualTo(new Room("a3"));
    }

    @Test
    void move_by_direction_throws_exception_invalid_direction() throws Exception {
        DungeonCrawling dungeonCrawling = new DungeonCrawlingImpl();

        assertThatThrownBy(() ->
                dungeonCrawling.move(DoorDirection.SOUTH))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("There is no exit with the provided direction");
    }


    @Test
    void display_dungeon() {
        DungeonCrawling dungeonCrawling = new DungeonCrawlingImpl();
        dungeonCrawling.displayDungeon();
        // Check the log to see drawn dungeon.
    }


    @Test
    void display_all_moves_from_room() throws Exception {
        DungeonCrawling dungeonCrawling = new DungeonCrawlingImpl();
        Set<DoorDirection> directions = dungeonCrawling.displayAvailableMoves();

        assertThat(directions).containsAll(List.of(DoorDirection.NORTH));
    }

    @Test
    void findShortestPath() {
        //TODO: Implement unit test.
    }
}