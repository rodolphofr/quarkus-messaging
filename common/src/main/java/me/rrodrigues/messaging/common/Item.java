package me.rrodrigues.messaging.common;

public enum Item {
    STANDARD_BURGER(1),
    STENCIL_BURGER(2),
    LUMBERJACK_BURGER(3),
    CHEESE_PLEASE_BURGER(4),
    FISHERMAN_BURGER(5),
    GREEN_ONE_BURGER(6),
    DOUBLE_SMASH_ONION_BURGER(7),
    CHICKEN_HOT_HONEY_BURGER(8);

    private int id;

    private Item(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
