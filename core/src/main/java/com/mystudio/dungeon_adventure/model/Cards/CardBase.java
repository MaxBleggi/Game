package com.mystudio.dungeon_adventure.model.Cards;

import java.io.Serializable;

/**
 * The base card class for all card types
 * @author Maximilian Bleggi
 */
public class CardBase implements Serializable {

    protected int powerCost;
    protected String description;
    protected String title;

    // cannot update after creation
    // unique ID for this individual card to be identified
    protected int cardID;
    // each type of card also a an ID attached
    protected int cardTypeID;

    /* TODO: hold image of card here */

    public CardBase() {
        // this class will never be instantiated directly, only extended
    }

    /**
     * Updates cost of card
     * @param powerDelta the change in power
     * @return current cost of card
     */
    public int updatePowerCost(int powerDelta) {
        this.powerCost += powerDelta;
        return powerCost;
    }

    /**
     * Retrieves cost of card
     * @return cost of card
     */
    public int getPowerCost() {
        return this.powerCost;
    }

    /**
     * Updates description of card
     * @param newDesc the new card description
     */
    public void updateDescription(String newDesc) {
        this.description = newDesc;
    }

    /**
     * Retrieves description of card
     * @return the description of the card
     */
    public String getDescritption() {
        return this.description;
    }

    /**
     * Updates title of card
     * @param newTitle the new title of the card
     */
    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }

    /**
     * Retrieves title of card
     * @return the new title of the card
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Retrieves unique ID of card
     * @return ID of card
     */
    public int getCardID() {
        return this.cardID;
    }

    /**
     * Retrieves card ID type
     * @return card ID type
     */
    public int getCardTypeID() {
        return this.cardTypeID;
    }
}
