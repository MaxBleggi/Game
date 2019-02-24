package com.mystudio.dungeon_adventure.view.Inventory;

import com.mystudio.dungeon_adventure.helpers.Wearables;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.util.ArrayList;

public class InventoryWindowUI {

    /* - - -  Edit window dimensions with these  - - - */
    private final int boxPadding = 10;
    private final int middlePadding = 20;
    private final int upperSectionPadding = 20;
    private final int bodyPadding = 10;
    private final int numberofBoxRows = 4;
    private final int numberOfBoxColumns = 3;

    /* - - - - - - - - - - - - - - - - - - - - - - - - */

    // attributes of the window
    // screen refers to the user's screen
    // window refers to the UI being created
    private int screenWidth;
    private int screenHeight;
    private int windowWidth;
    private int windowHeight;
    private int boxSize;

    /* - - -  The top section of the window - - - */
    // player's portrait
    private int portraitX;
    private int portraitY;
    private int portraitSize;
    public Sprite portrait;

    // player's name label
    private int nameX;
    private int nameY;

    // player's health status
    private int healthX;
    private int healthY;
    /* - - - - - - - - - - - - - - - - - - - - - */

    /* - - -  The bottom section of the window - - - */
    private ArrayList<InventoryBoxUI> boxes;

    // body parts
    private InventoryBodyPartUI head;
    private InventoryBodyPartUI torso;
    private InventoryBodyPartUI legs;
    private InventoryBodyPartUI feet;

    // hands
    private InventoryHandsUI leftHand;
    private InventoryHandsUI rightHand;
    /* - - - - - - - - - - - - - - - - - - - - - */

    // box IDs
    private static final int INVENTORY0 = 0;
    private static final int INVENTORY1 = 1;
    private static final int INVENTORY2 = 2;
    private static final int INVENTORY3 = 3;
    private static final int INVENTORY4 = 4;
    private static final int INVENTORY5 = 5;
    private static final int INVENTORY6 = 6;
    private static final int INVENTORY7 = 7;
    private static final int INVENTORY8 = 8;
    private static final int INVENTORY9 = 9;
    private static final int INVENTORY10 = 10;
    private static final int INVENTORY11 = 11;
    private static final int INVENTORY12 = 12;
    private static final int HEAD = 13;
    private static final int TORSO = 14;
    private static final int LEGS = 15;
    private static final int FEET = 16;
    private static final int L_HAND = 17;
    private static final int R_HAND = 18;

    public InventoryWindowUI(int screenWidth, int screenHeight, int boxSize, int portraitSize) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.boxSize = boxSize;
        this.portraitSize = portraitSize;

        // width = box - padding - box - padding - box - middle - hand - body - hand
        this.windowWidth = this.boxSize * this.numberOfBoxColumns + this.boxPadding
                * (this.numberOfBoxColumns - 1) + this.middlePadding + this.boxSize*3;

        // height = portrait - upperPadding - box - padding - box - padding - box padding - box
        this.windowHeight = this.portraitSize +  this.boxSize * this.numberOfBoxColumns
                + boxPadding * (this.numberOfBoxColumns - 1) + this.upperSectionPadding;
    }

    /**
     * Required after initialization of object. Calculates the coord's for each element
     */
    public void generatePositioning() {
        // center window onto screen
        int x = (screenWidth / 2) - (this.windowWidth / 2);
        int y = (screenHeight / 2) - (this.windowHeight / 2);

        // --- create upper section ---
        this.portraitX = x;
        this.portraitY = y;
        this.nameX = this.boxSize;
        this.nameY = this.boxSize;
        this.healthX = this.boxSize;
        this.healthY = this.boxSize;

        // --- create lower section ---

        // create the boxes

        // create 4 row of 3 boxes
        int adjY = y + this.portraitSize + this.upperSectionPadding;
        int adjX = x;

        this.boxes = new ArrayList<InventoryBoxUI>(12);

        // create 4 row of 3 boxes
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                this.boxes.add(new InventoryBoxUI(adjX, adjY, this.boxSize, this.boxSize));
                adjX += this.boxSize + this.boxPadding;
            }
            adjX = x;
            adjY += this.boxSize + this.boxPadding;
        }

        // left hand
        int handsY = y + this.portraitSize + this.upperSectionPadding + this.boxSize + this.boxSize/2;
        adjX = x + this.boxSize*3 + boxPadding*2 + middlePadding;
        this.leftHand = new InventoryHandsUI(adjX, handsY, this.boxSize, this.boxSize,true);

        // create  the body parts
        adjX += this.leftHand.getWidth() + bodyPadding;
        adjY = y + this.portraitSize + this.upperSectionPadding;
        this.head = new InventoryBodyPartUI(adjX, adjY, this.boxSize, this.boxSize, Wearables.HEAD);
        adjY += this.head.getHeight() + bodyPadding;
        this.torso = new InventoryBodyPartUI(adjX, adjY, this.boxSize, this.boxSize, Wearables.TORSO);
        adjY += this.torso.getHeight() + bodyPadding;
        this.legs = new InventoryBodyPartUI(adjX, adjY, this.boxSize, this.boxSize, Wearables.LEGS);
        adjY += this.legs.getHeight() + bodyPadding;
        this.feet = new InventoryBodyPartUI(adjX, adjY, this.boxSize, this.boxSize, Wearables.FEET);

        // right hand
        adjX += this.torso.getWidth() + bodyPadding;
        this.rightHand = new InventoryHandsUI(adjX, handsY, this.boxSize, this.boxSize,false);
    }

    /**
     * Determines if the user pressed on any area inside an inventory box
     * @return -1 if not inside any box, box's ID otherwise
     */
    public int checkForPress(int x, int y) {

        // return false if it's not even inside the window
        if (x < this.portraitX || x > this.portraitX + this.windowWidth) {
            return -1;
        }
        else if (y < this.portraitY || y > this.portraitY + this.windowHeight) {
            return -1;
        }

        if (x < this.boxes.get(2).getX()) {
            // check left side of window

            // the only clickable areas are on the bottom section
            if (y > this.portraitY + this.portraitSize) {

                // check every box
                if (this.boxes.get(0).isInClickedOn(x,y)) {
                    System.out.println("inventory0");
                    return INVENTORY0;
                }
                if (this.boxes.get(1).isInClickedOn(x,y)) {
                    System.out.println("inventory1");
                    return INVENTORY1;
                }
                if (this.boxes.get(2).isInClickedOn(x,y)) {
                    System.out.println("inventory2");
                    return INVENTORY2;
                }
                if (this.boxes.get(3).isInClickedOn(x,y)) {
                    System.out.println("inventory3");
                    return INVENTORY3;
                }
                if (this.boxes.get(4).isInClickedOn(x,y)) {
                    System.out.println("inventory4");
                    return INVENTORY4;
                }
                if (this.boxes.get(5).isInClickedOn(x,y)) {
                    System.out.println("inventory5");
                    return INVENTORY5;
                }
                if (this.boxes.get(6).isInClickedOn(x,y)) {
                    System.out.println("inventory6");
                    return INVENTORY6;
                }
                if (this.boxes.get(7).isInClickedOn(x,y)) {
                    System.out.println("inventory7");
                    return INVENTORY7;
                }
                if (this.boxes.get(8).isInClickedOn(x,y)) {
                    System.out.println("inventory8");
                    return INVENTORY8;
                }
                if (this.boxes.get(9).isInClickedOn(x,y)) {
                    System.out.println("inventory9");
                    return INVENTORY9;
                }
                if (this.boxes.get(10).isInClickedOn(x,y)) {
                    System.out.println("inventory10");
                    return INVENTORY10;
                }
                if (this.boxes.get(11).isInClickedOn(x,y)) {
                    System.out.println("inventory11");
                    return INVENTORY11;
                }
                if (this.boxes.get(12).isInClickedOn(x,y)) {
                    System.out.println("inventory12");
                    return INVENTORY12;
                }
            }
        }
        else {
            // check right side of window

            // the only clickable areas are on the bottom section
            if (y > this.portraitY + this.portraitSize) {
                if (this.rightHand.isInClickedOn(x,y)) {
                    System.out.println("r hand");
                    return R_HAND;
                }
                if (this.leftHand.isInClickedOn(x,y)) {
                    System.out.println("l hand");
                    return L_HAND;
                }
                if (this.head.isInClickedOn(x,y)) {
                    System.out.println("head");
                    return HEAD;
                }
                if (this.torso.isInClickedOn(x,y)) {
                    System.out.println("torso");
                    return TORSO;
                }
                if (this.legs.isInClickedOn(x,y)) {
                    System.out.println("legs");
                    return LEGS;
                }
                if (this.feet.isInClickedOn(x,y)) {
                    System.out.println("feet");
                    return FEET;
                }
            }
        }

        return -1;
    }

    public void drawOutlineToDebug(Graphics g) {

        g.drawRect(this.portraitX, this.portraitY, this.portraitSize, this.portraitSize);

        for (int i = 0; i < 12; i++) {
            InventoryBoxUI tmp = this.boxes.get(i);
            g.drawRect(tmp.getX(), tmp.getY(), tmp.getWidth(), tmp.getHeight());
        }

        g.drawRect(this.leftHand.getX(), this.leftHand.getY(), this.leftHand.getWidth(), this.leftHand.getHeight());
        g.drawRect(this.head.getX(), this.head.getY(), this.head.getWidth(), this.head.getHeight());
        g.drawRect(this.torso.getX(), this.torso.getY(), this.torso.getWidth(), this.torso.getHeight());
        g.drawRect(this.legs.getX(), this.legs.getY(), this.legs.getWidth(), this.legs.getHeight());
        g.drawRect(this.feet.getX(), this.feet.getY(), this.feet.getWidth(), this.feet.getHeight());
        g.drawRect(this.rightHand.getX(), this.rightHand.getY(), this.rightHand.getWidth(), this.rightHand.getHeight());

    }
}
