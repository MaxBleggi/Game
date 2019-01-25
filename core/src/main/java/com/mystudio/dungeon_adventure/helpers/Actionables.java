package com.mystudio.dungeon_adventure.helpers;

public enum Actionables {
    SHIELD(-1),
    SWORD(1);

    private final int value;

    public int getActionableValue() {
        return this.value;
    }

    private Actionables(int value) {
        this.value = value;
    }
}
