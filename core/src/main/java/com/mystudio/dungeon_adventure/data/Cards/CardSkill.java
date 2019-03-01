package com.mystudio.dungeon_adventure.data.Cards;

import java.io.Serializable;

/**
 * Child class of CarBase. Skills.
 * @author Maximilian Bleggi
 */
public class CardSkill extends CardBase implements Serializable {

    public CardSkill(int pwrCost, String desc, String title) {
        super.powerCost = pwrCost;
        super.description = desc;
        super.title = title;
    }
}
