package com.mystudio.dungeon_adventure.helpers;

public enum ItemTypes {
    Generic(0),
    Actionable(1),
    Wearable(2);

    private final int value;

    public int getItemValue() {
        return this.value;
    }

    private ItemTypes(int value) {
        this.value = value;
    }
}