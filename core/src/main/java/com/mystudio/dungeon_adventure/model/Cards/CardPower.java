package com.mystudio.dungeon_adventure.model.Cards;

import java.io.Serializable;

/**
 * Child class of CarBase. Powers.
 * @author Maximilian Bleggi
 */
public class CardPower extends CardBase implements Serializable {

    public CardPower(int pwrCost, String desc, String title) {
        super.powerCost = pwrCost;
        super.description = desc;
        super.title = title;
    }
}
