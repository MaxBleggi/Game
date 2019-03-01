package com.mystudio.dungeon_adventure.data.Inventory;

import com.mystudio.dungeon_adventure.helpers.ItemTypes;
import com.mystudio.dungeon_adventure.helpers.ReturnValues;
import com.mystudio.dungeon_adventure.helpers.Wearables;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * The player's inventory
 * @author Maximilian Bleggi
 */
public class PlayerInventory implements Serializable {

    private final int DEFAULT_INVENTORY_SIZE = 12;

    // player's body parts, <WearableType, Item>
    private Map<Wearables, ItemWearable> bodyParts;
    private ItemActionable leftHand;
    private ItemActionable rightHand;

    // different types of items possible in inventory, <itemID, Item>
    private Map<Integer, ItemActionable> actionableItems;
    private Map<Integer, ItemWearable> wearableItems;
    private Map<Integer, ItemBase> genericItems;

    // this is the variable used to glue all the different maps together
    // use this to find which data type the object ID is
    private Map<Integer, ItemTypes> itemTypes;

    private int maxInventorySize;

    /**
     * initialize collection types
     */
    public PlayerInventory() {
        this.actionableItems = new HashMap<Integer, ItemActionable>(0);
        this.wearableItems = new HashMap<Integer, ItemWearable>(0);
        this.itemTypes = new HashMap<Integer, ItemTypes>(0);
        this.maxInventorySize = this.DEFAULT_INVENTORY_SIZE;

        // set player's body parts to null initially
        this.bodyParts = new EnumMap<Wearables, ItemWearable>(Wearables.class);

        for (Wearables type: Wearables.values()) {
            this.bodyParts.put(type, null);
        }

        this.leftHand = null;
        this.rightHand = null;
    }

    // TODO figure out how to getInventoryItem()

    /**
     * Retrieves item on player's body part
     * @return ItemWearable
     */
    public ItemWearable getBodyPart(Wearables bodyPart) {
        return this.bodyParts.get(bodyPart);
    }

    /**
     * Retrieves item in player's left hand
     * @return ItemActionable
     */
    public ItemActionable getLeftHand() {
        return this.leftHand;
    }

    /**
     * Retrieves item in player's right hand
     * @return ItemActionable
     */
    public ItemActionable getRightHand() {
        return this.rightHand;
    }

    /**
     * Adds wearable item into player's inventory
     * @param item wearable item added
     * @return error/success value
     */
    public int addItemToInventory(ItemWearable item) {
        int itemID = item.itemID;

        // check if inventory is full
        if (this.itemTypes.size() >= this.maxInventorySize) {
            System.out.println("Max inventory size reached");
            return ReturnValues.MAX_INVENTORY_SIZE;
        }

        // ensure this item is not already in inventory
        if (this.itemTypes.containsKey(itemID)) {

            // insert item
            this.itemTypes.put(itemID, ItemTypes.Wearable);
            this.wearableItems.put(itemID, item);
        }
        else {
            System.out.println("WARNING: Tried to place item in inventory that already existed");
            return ReturnValues.ITEM_IN_INVENTORY_EXISTS;
        }

        return 0;
    }

    /**
     * Adds actionable item into player's inventory
     * @param item actionable item added
     * @return error/success value
     */
    public int addItemToInventory(ItemActionable item) {
        int itemID = item.itemID;

        // check if inventory is full
        if (this.itemTypes.size() >= this.maxInventorySize) {
            System.out.println("Max inventory size reached");
            return ReturnValues.MAX_INVENTORY_SIZE;
        }

        // ensure this item is not already in inventory
        if (this.itemTypes.containsKey(itemID)) {
            this.itemTypes.put(itemID, ItemTypes.Actionable);
            this.actionableItems.put(itemID, item);
        }
        else {
            System.out.println("ERROR: Tried to place item in inventory that already existed");
            return ReturnValues.ITEM_IN_INVENTORY_EXISTS;
        }

        return 0;
    }

    /**
     * Adds generic item into player's inventory
     * @param item generic item added
     * @return error/success value
     */
    public int addItemToInventory(ItemBase item) {
        int itemID = item.itemID;

        // check if inventory is full
        if (this.itemTypes.size() >= this.maxInventorySize) {
            System.out.println("Max inventory size reached");
            return ReturnValues.MAX_INVENTORY_SIZE;
        }

        // ensure this item is not already in inventory
        if (this.itemTypes.containsKey(itemID)) {
            this.itemTypes.put(itemID, ItemTypes.Generic);
            this.genericItems.put(itemID, item);
        }
        else {
            System.out.println("ERROR: Tried to place item in inventory that already existed");
            return ReturnValues.ITEM_IN_INVENTORY_EXISTS;
        }

        return 0;
    }


    /**
     * Removes item from player's inventory
     * @param item item to be removed
     * @return error/success value
     */
    public int removeItemFromInventory(ItemActionable item) {
        // ensure item is in inventory
        int ID = item.itemID;

        if (this.itemTypes.containsKey(ID)) {
            System.out.println("ERROR: Attempted to remove item not in inventory");
            return ReturnValues.ITEM_NOT_IN_INVENTORY;
        }

        // remove the item from inventory
        this.itemTypes.remove(ID);
        this.actionableItems.remove(ID);
        return 0;
    }

    /**
     * Removes item from player's inventory
     * @param item item to be removed
     * @return error/success value
     */
    public int removeItemFromInventory(ItemWearable item) {
        // ensure item is in inventory
        int ID = item.itemID;

        if (this.itemTypes.containsKey(ID)) {
            System.out.println("ERROR: Attempted to remove item not in inventory");
            return ReturnValues.ITEM_NOT_IN_INVENTORY;
        }

        // remove the item from inventory
        this.itemTypes.remove(ID);
        this.wearableItems.remove(ID);
        return 0;
    }

    /**
     * Removes item from player's inventory
     * @param item item to be removed
     * @return error/success value
     */
    public int removeItemFromInventory(ItemBase item) {
        // ensure item is in inventory
        int ID = item.itemID;

        if (this.itemTypes.containsKey(ID)) {
            System.out.println("ERROR: Attempted to remove item not in inventory");
            return ReturnValues.ITEM_NOT_IN_INVENTORY;
        }

        // remove the item from inventory
        this.itemTypes.remove(ID);
        this.genericItems.remove(ID);
        return 0;
    }


    /**
     * Adds a wearable item to the player's body. Places old item in inventory
     * @param wearableType the type of the wearable
     * @param newItem the new item being dawned
     * @return success/failure, inventory may be full
     */
    public int addItemToBody(Wearables wearableType, ItemWearable newItem) {
        // if that body part is empty
        if (this.bodyParts.get(wearableType) == null) {
            // set it to new item

            this.bodyParts.put(wearableType, newItem);

        }
        // if an item is there already
        else {
            // take the previous item and pop it back into the inventory
            ItemWearable oldItem = this.bodyParts.remove(wearableType);
            this.bodyParts.put(wearableType, newItem);

            // inventory may be full
            return addItemToInventory(oldItem);
        }

        return 0;
    }

    /**
     * Adds actionable item to player's hand. Places old item in inventory
     * @param newItem the new item to be added
     * @return success/failure, inventory may be full
     */
    public int addItemToLeftHand(ItemActionable newItem) {
        // if left hand is empty
        if (this.leftHand == null) {
            // add it to hand
            this.leftHand = newItem;
            return 0;
        }
        else {
            // replace left hand item
            ItemActionable oldItem = this.leftHand;
            this.leftHand = newItem;

            // pop item into inventory, inventory may be full though
            return addItemToInventory(oldItem);
        }
    }

    /**
     * Adds actionable item to player's hand. Places old item in inventory
     * @param newItem the new item to be added
     * @return success/failure, inventory may be full
     */
    public int addItemToRightHand(ItemActionable newItem) {
        // if left hand is empty
        if (this.rightHand == null) {
            // add it to hand
            this.rightHand = newItem;
            return 0;
        }
        else {
            // replace left hand item
            ItemActionable oldItem = this.rightHand;
            this.rightHand = newItem;

            // pop item into inventory, inventory may be full though
            return addItemToInventory(oldItem);
        }
    }

    /**
     * Retrieves max inventory size
     * @return max size of inventory
     */
    public int getMaxInventorySize() {
        return this.maxInventorySize;
    }

    /**
     * Retrieves current inventory size
     * @return size of inventory
     */
    public int getCurrentInventorySize() {
        return this.itemTypes.size();
    }

    /**
     * Retrieves item on specified body part
     * @param bodyPart the body part desired
     * @return the item on specified body part
     */
    public ItemWearable getItemOnBody(Wearables bodyPart) {
        return this.bodyParts.get(bodyPart);
    }
}
