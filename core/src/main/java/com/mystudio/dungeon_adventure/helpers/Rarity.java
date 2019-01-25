package com.mystudio.dungeon_adventure.helpers;

public enum Rarity {
    COMMON(1),
    UNCOMMON(2),
    RARE(3),
    SACRED(4);

    private final int value;

    public int getRarityValue() {
        return this.value;
    }

    private Rarity(int value) {
        this.value = value;
    }
}
