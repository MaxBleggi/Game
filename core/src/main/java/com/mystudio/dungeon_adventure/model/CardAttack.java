package com.mystudio.dungeon_adventure.model;

/**
 * Child class of CarBase. This card attacks enemies.
 * @author Maximilian Bleggi
 */
public class CardAttack extends CardBase {

    public CardAttack(int pwrCost, String desc, String title) {
        super.powerCost = pwrCost;
        super.description = desc;
        super.title = title;
    }
}
