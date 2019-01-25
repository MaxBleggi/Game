package com.mystudio.dungeon_adventure.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The player's Deck of Cards.
 * @author Maximilian Bleggi
 */
public class CardDeck {

    // maps of the three card objects
    private Map<Integer, AttackCard> attackStack;
    private Map<Integer, SkillCard> skillStack;
    private Map<Integer, PowerCard> powerStack;

    // arrayList of card ID's
    // this will be the "deck". Cards will be drawn by ID from the 3 maps by this
    private ArrayList<Integer> cardDeck;

    /**
     * initialize collection types
     */
    public CardDeck() {
        this.attackStack = new HashMap<Integer, AttackCard>();
        this.skillStack = new HashMap<Integer, SkillCard>();
        this.powerStack = new HashMap<Integer, PowerCard>();
        this.cardDeck = new ArrayList<Integer>();
    }

    // TODO: shuffle deck

    // TODO: draw cards, etc...
}
