package tomotom.game.dto;

public enum DoorDirection {
    NORTH("n"), SOUTH("s"), EAST("e"), WEST("w");

    private String name;

    DoorDirection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static DoorDirection fromString(String name) {
        for (DoorDirection direction : DoorDirection.values()) {
            if (direction.name.equalsIgnoreCase(name)) {
                return direction;
            }
        }
        throw new IllegalStateException("Invalid direction was provided");
    }
}
