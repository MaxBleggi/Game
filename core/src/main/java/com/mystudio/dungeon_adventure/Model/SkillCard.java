package com.mystudio.dungeon_adventure.Model;

/**
 * Child class of CarBase. Skills.
 * @author Maximilian Bleggi
 */
public class SkillCard extends CardBase {

    public SkillCard(int pwrCost, String desc, String title) {
        super.powerCost = pwrCost;
        super.description = desc;
        super.title = title;
    }
}
