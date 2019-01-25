package com.mystudio.dungeon_adventure.Model;

/**
 * The base class for a player.
 * All future
 * @author Maximilian Bleggi
 */
public class PlayerBaseClass {

    /**
     * Fundamental attributes shared by all player classes
     */
    protected String name;
    protected int currentHP;
    protected int maxHP;
    protected int defaultHandSize;

    public PlayerBaseClass(){
        // this class will never be instantiated directly, only extended
    }

    /**
     * Updates the hp of the player
     * @param hpDelta the change in hp
     * @return updated hp level, returns -1 if player has died
     */
    public int updateHP(int hpDelta) {
        // ensure hp does not go above max hp
        this.currentHP += hpDelta;

        if (this.currentHP > this.maxHP) {
            this.currentHP = this.maxHP;
        }
        // check if user has died
        else if (this.currentHP <= 0) {
            return -1;
        }

        return this.currentHP;
    }

    /**
     * Updates the max hp of the player, also raises current hp by that much
     * @param hpDelta the change in max hp
     */
    public void updateMaxHP(int hpDelta) {
        this.maxHP += hpDelta;

        // ensure current health is updated, and is not above max
        this.currentHP += hpDelta;
        if (this.currentHP > this.maxHP) {
            this.currentHP = this.maxHP;
        }
    }

    /**
     * Retrieves player's current hp
     * @return player's hp
     */
    public int getCurrentHP() {
        return this.currentHP;
    }

    /**
     * Retrieves player's max hp
     * @return player's max hp
     */
    public int getMaxHP() {
        return this.maxHP;
    }

    /**
     * Sets the player's name
     * @param name player's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves player's name
     * @return player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Update player's max hand size for cards
     * @param sizeDelta the change in hand size
     * @return player's current max hand size
     */
    public int updateDefaultHandSize(int sizeDelta) {
        this.defaultHandSize += sizeDelta;
        return this.defaultHandSize;
    }

    /**
     * Retrieve player's default hand size
     * @return player's default hand size
     */
    public int getDefaultHandSize() {
        return this.defaultHandSize;
    }
}
