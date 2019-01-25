package com.mystudio.dungeon_adventure.Model;

/**
 * Child class of CarBase. This card attacks enemies.
 * @author Maximilian Bleggi
 */
public class AttackCard extends CardBase {

    public AttackCard(int pwrCost, String desc, String title) {
        super.powerCost = pwrCost;
        super.description = desc;
        super.title = title;
    }
}
