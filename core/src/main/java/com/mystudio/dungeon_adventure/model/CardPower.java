package com.mystudio.dungeon_adventure.model;

/**
 * Child class of CarBase. Powers.
 * @author Maximilian Bleggi
 */
public class CardPower extends CardBase {

    public CardPower(int pwrCost, String desc, String title) {
        super.powerCost = pwrCost;
        super.description = desc;
        super.title = title;
    }
}
