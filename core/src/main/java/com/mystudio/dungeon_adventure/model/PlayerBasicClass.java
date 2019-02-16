package com.mystudio.dungeon_adventure.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.graphics.Sprite;

public class PlayerBasicClass extends PlayerBaseClass {

    private static final int BASIC_CLASS_DEFAULT_HP = 100;
    private static final int BASIC_CLASS_DEFAULT_HAND_SIZE = 100;
    private static final int BASIC_CLASS_DEFAULT_POWER = 3;
    private static final String PLAYER_TEXTURE = "player1.png";

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

        super.playerSprite = new Sprite(new Texture(Gdx.files.internal(PLAYER_TEXTURE)));
        super.cPoint = new CollisionPoint();
        super.playerSprite.setSize(15,20);

        super.inventory = new PlayerInventory();
        super.cardDeck = new CardDeck();
    }
}
