package com.mystudio.dungeon_adventure.model.Cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The player's Deck of Cards.
 * @author Maximilian Bleggi
 */
public class CardDeck implements Serializable {

    // maps of the three card objects, <cardID, Card>
    private Map<Integer, CardAttack> attackStack;
    private Map<Integer, CardSkill> skillStack;
    private Map<Integer, CardPower> powerStack;

    // arrayList of card ID's
    // this will be the "deck". Cards will be drawn by ID from the 3 maps by this
    private ArrayList<Integer> cardDeck;

    /**
     * initialize collection types
     */
    public CardDeck() {
        this.attackStack = new HashMap<Integer, CardAttack>();
        this.skillStack = new HashMap<Integer, CardSkill>();
        this.powerStack = new HashMap<Integer, CardPower>();
        this.cardDeck = new ArrayList<Integer>();
    }

    // TODO: shuffle deck

    // TODO: draw cards, etc...
}
