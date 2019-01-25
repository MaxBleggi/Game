package com.mystudio.dungeon_adventure.model;

public class PlayerBasicClass extends PlayerBaseClass {

    private static final int BASIC_CLASS_DEFAULT_HP = 100;
    private static final int BASIC_CLASS_DEFAULT_HAND_SIZE = 100;
    private static final int BASIC_CLASS_DEFAULT_POWER = 3;

    /**
     * Initialize the player
     */
    public PlayerBasicClass(String name) {
        super.name = name;
        super.currentHP = BASIC_CLASS_DEFAULT_HP;
        super.maxHP = BASIC_CLASS_DEFAULT_HP;
        super.currentHandSize = 0;
        super.defaultHandSize = BASIC_CLASS_DEFAULT_HAND_SIZE;
        super.currentPower = BASIC_CLASS_DEFAULT_POWER;
        super.defaultPower = BASIC_CLASS_DEFAULT_POWER;

        super.inventory = new PlayerInventory();
        super.cardDeck = new CardDeck();
    }
}
