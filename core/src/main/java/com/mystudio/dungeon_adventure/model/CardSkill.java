package com.mystudio.dungeon_adventure.model;

/**
 * Child class of CarBase. Skills.
 * @author Maximilian Bleggi
 */
public class CardSkill extends CardBase {

    public CardSkill(int pwrCost, String desc, String title) {
        super.powerCost = pwrCost;
        super.description = desc;
        super.title = title;
    }
}
