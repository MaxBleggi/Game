package com.mystudio.dungeon_adventure.data.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mystudio.dungeon_adventure.data.Cards.CardDeck;
import com.mystudio.dungeon_adventure.data.Inventory.PlayerInventory;

import java.io.Serializable;

/**
 * The base class for a player.
 * All playable classes are children of this class
 * @author Maximilian Bleggi
 */
public class PlayerBaseClass implements Serializable {

    protected static final int MAX_HAND_SIZE = 12;

    int t = 0;

    /**
     * Fundamental attributes shared by all player classes
     */

    protected String name;

    protected int currentHP;
    protected int maxHP;

    protected int defaultHandSize;
    protected int currentHandSize;

    protected int defaultPower;
    protected int currentPower;

    // player's inventory
    public PlayerInventory inventory;

    // player's card deck
    public CardDeck cardDeck;


    public PlayerBaseClass() {
        // this class will never be instantiated directly, only extended
    }

    /**
     * Updates the hp of the player
     * @param hpDelta the change in hp
     * @return updated hp level, returns -1 if player has died
     */
    public int updateCurrentHP(int hpDelta) {
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
     * Updates default power for a player
     * @param powerDelta the change in default power
     * @return current default power
     */
    public int updateDefaultPower(int powerDelta) {
        this.defaultPower += powerDelta;
        return this.defaultPower;
    }

    /**
     * Updates current power for a player, can exceed max power
     * @param powerDelta the change in default power
     * @return current power
     */
    public int updateCurrentPower(int powerDelta) {
        this.currentPower += currentPower;
        return this.currentPower;
    }

    /**
     * Retrieves default power of player
     * @return default power value
     */
    public int getDefaultPower() {
        return this.defaultPower;
    }

    /**
     * Retrieves current power of player
     * @return current power value
     */
    public int getCurrentPower() {
        return this.currentPower;
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
     * Updates player's max hand size for cards
     * @param sizeDelta the change in hand size
     * @return player's current max hand size
     */
    public int updateDefaultHandSize(int sizeDelta) {
        this.defaultHandSize += sizeDelta;
        return this.defaultHandSize;
    }

    /**
     * Updates player's current hand size for cards
     * @param sizeDelta the change in hand size
     * @return player's current hand size
     */
    public int updateCurrentHandSize(int sizeDelta) {
        this.currentHandSize += sizeDelta;
        return this.currentHandSize;
    }

    /**
     * Retrieve player's default hand size
     * @return player's default hand size
     */
    public int getDefaultHandSize() {
        return this.defaultHandSize;
    }

    /**
     * Retrieves player's max hand size
     * @return player's max hand size
     */
    public int getMaxHandSize() {
        return this.MAX_HAND_SIZE;
    }

    /**
     * Retrieve player's current hand size
     * @return player's current hand size
     */
    public int getCurrentHandSize() {
        return this.currentHandSize;
    }

    public float getPlayerXmovement() {
        float xMove = 0.0f;

        // player pressing A key (moving left)
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xMove = -2.0f;
        }
        // player pressing D key (moving right)
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xMove = 2.0f;
        }

        return xMove;
    }

    public float getPlayerYmovement() {
        float yMove = 0.0f;

        // player pressing W key (moving up)
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            yMove = -2.0f;
        }
        // player pressing S key (moving down)
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yMove = 2.0f;
        }

        return yMove;
    }

}
