package com.mystudio.dungeon_adventure.helpers;

public enum Wearables {
    HEAD(1),
    TORSO(2),
    ARMS(3),
    HANDS(4),
    LEGS(5),
    FEET(6);

    private final int value;

    public int getWearableValue() {
        return this.value;
    }

    private Wearables(int value) {
        this.value = value;
    }
}
