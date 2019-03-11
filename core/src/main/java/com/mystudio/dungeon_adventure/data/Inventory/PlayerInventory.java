package com.mystudio.dungeon_adventure.data.Inventory;

import com.mystudio.dungeon_adventure.helpers.*;

import java.io.Serializable;
import java.util.*;

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

    private Map<UUID, ItemBase> inventory;

    // used to interact with inventory window boxes. Spots 1 - 12
    private UUID[] inventoryBoxes;

    private int inventoryBoxCount;

    /**
     * initialize collection types
     */
    public PlayerInventory() {
        this.inventoryBoxCount = this.DEFAULT_INVENTORY_SIZE;

        this.inventory = new HashMap<UUID, ItemBase>(inventoryBoxCount);

        this.inventoryBoxes = new UUID[inventoryBoxCount];


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


    public ItemBase getItem(UUID itemID) {
        return this.inventory.get(itemID);
    }


    public UUID[] getInventoryBoxes() {
        return inventoryBoxes;
    }


    /**
     * Adds wearable item into player's inventory
     * @param item wearable item added
     * @return error/success value
     */
    public int addItemToInventory(ItemBase item, int inventorySlot) {
        UUID itemID = item.itemID;

        // check if inventory is full
        if (this.inventory.size() >= this.inventoryBoxCount) {
            System.out.println("Max inventory size reached");
            return ReturnValues.MAX_INVENTORY_SIZE;
        }

        // check if this item is not already in inventory
        if (!this.inventory.containsKey(itemID)) {

            // check if the slot is in the inventory and isn't full
            if (inventorySlot >= 0 && inventorySlot < 12 && this.inventoryBoxes[inventorySlot] == null) {
                // insert item
                this.inventory.put(itemID, item);
                this.inventoryBoxes[inventorySlot] = itemID;
            }
            else {
                System.out.println("ERROR: Either slot isn't in inventory [1-12] or slot is full already");
                return -1111;
            }
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
    public int removeItemFromInventory(ItemBase item) {
        // ensure item is in inventory
        UUID ID = item.itemID;

        if (this.inventory.containsKey(ID)) {
            System.out.println("ERROR: Attempted to remove item not in inventory");
            return ReturnValues.ITEM_NOT_IN_INVENTORY;
        }

        // remove the item from inventory
        this.inventory.remove(ID);

        // remove item from slot
        for (int i = 0; i < this.inventoryBoxCount; i++) {
            // find the slot
            if (ID.equals(this.inventoryBoxes[i])) {
                this.inventoryBoxes[i] = null;
            }
        }

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

            // make sure inventory isn't full
            int openSlot = -1;

            for (int i = 0; i < this.inventoryBoxCount; i++) {
                if (this.inventoryBoxes == null) {
                    openSlot = i;
                }
            }

            if (openSlot == -1) {
               System.out.println("ERROR: Inventory full");
               return ReturnValues.MAX_INVENTORY_SIZE;
            }

            // take the previous item and pop it back into the inventory
            ItemWearable oldItem = this.bodyParts.remove(wearableType);
            this.bodyParts.put(wearableType, newItem);


            return addItemToInventory(oldItem, openSlot);
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
        // if item is already in hand
        else {

            // make sure inventory isn't full
            int openSlot = -1;

            for (int i = 0; i < this.inventoryBoxCount; i++) {
                if (this.inventoryBoxes == null) {
                    openSlot = i;
                }
            }

            if (openSlot == -1) {
                System.out.println("ERROR: Inventory full");
                return ReturnValues.MAX_INVENTORY_SIZE;
            }


            // replace left hand item
            ItemActionable oldItem = this.leftHand;
            this.leftHand = newItem;

            // pop item into inventory, inventory may be full though
            return addItemToInventory(oldItem, openSlot);
        }
    }

    /**
     * Adds actionable item to player's hand. Places old item in inventory
     * @param newItem the new item to be added
     * @return success/failure, inventory may be full
     */
    public int addItemToRightHand(ItemActionable newItem) {
        // if right hand is empty
        if (this.rightHand == null) {
            // add it to hand
            this.rightHand = newItem;
            return 0;
        }
        // if hand is full
        else {

            // make sure inventory isn't full
            int openSlot = -1;

            for (int i = 0; i < this.inventoryBoxCount; i++) {
                if (this.inventoryBoxes == null) {
                    openSlot = i;
                }
            }

            if (openSlot == -1) {
                System.out.println("ERROR: Inventory full");
                return ReturnValues.MAX_INVENTORY_SIZE;
            }

            // replace left hand item
            ItemActionable oldItem = this.rightHand;
            this.rightHand = newItem;

            // pop item into inventory, inventory may be full though
            return addItemToInventory(oldItem, openSlot);
        }
    }


    /**
     * Retrieves current inventory size
     * @return size of inventory
     */
    public int getCurrentInventorySize() {
        return this.inventory.size();
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
