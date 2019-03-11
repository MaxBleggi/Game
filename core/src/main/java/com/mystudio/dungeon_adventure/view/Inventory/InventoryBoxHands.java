package com.mystudio.dungeon_adventure.view.Inventory;


public class InventoryBoxHands extends InventoryBoxGeneric {

    private boolean isLeftHand;

    public InventoryBoxHands(int x, int y, int width, int height, boolean isLeftHand) {
        super.x = x;
        super.y = y;
        super.width = width;
        super.height = height;
        super.sprite = null;
        super.itemID = null;

        this.isLeftHand = isLeftHand;
    }

    /**
     * Reports the hand of the box
     * @return true is left hand, false if right hand
     */
    public boolean isLeftHand() {
        return this.isLeftHand;
    }
}
