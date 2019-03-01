package com.mystudio.dungeon_adventure.model.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.dungeon_adventure.model.Cards.CardDeck;
import com.mystudio.dungeon_adventure.model.Inventory.PlayerInventory;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Sprite;

import java.io.Serializable;

public class PlayerBasicClass extends PlayerBaseClass implements Serializable {

    private static final int BASIC_CLASS_DEFAULT_HP = 100;
    private static final int BASIC_CLASS_DEFAULT_HAND_SIZE = 100;
    private static final int BASIC_CLASS_DEFAULT_POWER = 3;
    private String playerTexture = "player.png";

    /**
     * Initialize the player
     */
    public PlayerBasicClass(String name) {
        super.name = name;
        super.currentHP = BASIC_CLASS_DEFAULT_HP;
        super.maxHP = BASIC_CLASS_DEFAULT_HP;
        super.currentHandSize = 0;
        super.defaultHandSize = BASIC_CLASS_DEFAULT_HAND_SIZE;
        super.currentPower = BASIC_CLASS_DEFAULT_POWER;
        super.defaultPower = BASIC_CLASS_DEFAULT_POWER;

        super.inventory = new PlayerInventory();
        super.cardDeck = new CardDeck();
    }

    public String getTextureFile() {
        return this.playerTexture;
    }

    public void print() {
        System.out.println("Player:");
        System.out.println("\tname " + super.name);
        System.out.println("\thp " + super.currentHP);
    }
}
