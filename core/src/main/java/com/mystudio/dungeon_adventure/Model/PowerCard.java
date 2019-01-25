package com.mystudio.dungeon_adventure.Model;

/**
 * Child class of CarBase. Powers.
 * @author Maximilian Bleggi
 */
public class PowerCard extends CardBase {

    public PowerCard(int pwrCost, String desc, String title) {
        super.powerCost = pwrCost;
        super.description = desc;
        super.title = title;
    }
}
