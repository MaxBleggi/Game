package com.mystudio.dungeon_adventure.view.Inventory;

import com.mystudio.dungeon_adventure.helpers.Wearables;
import com.mystudio.dungeon_adventure.model.Player.PlayerBasicClass;
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
    private final int numberOfBoxes = numberofBoxRows * numberOfBoxColumns;

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
                * (this.numberOfBoxColumns - 1) + this.middlePadding + this.boxSize*3 + this.bodyPadding*2;

        // height = portrait - upperPadding - box - padding - box - padding - box padding - box
        this.windowHeight = this.portraitSize +  this.boxSize * this.numberofBoxRows
                + boxPadding * (this.numberofBoxRows - 1) + this.upperSectionPadding;

        generatePositioning();
    }

    public void setSpritesFromModel(PlayerBasicClass player) {
        //player.inventory;
    }

    public void setBoxSprite(int boxID, int itemID, String spritePath) {

        System.out.println(spritePath + " ID:" + boxID);
        if (boxID >= 0 && boxID <= 11) {
            this.boxes.get(boxID).placeItemIfEmpty(itemID, spritePath);
        }
        else if (boxID <= 18){
            switch (boxID) {
                case HEAD:
                    this.head.placeItemIfEmpty(itemID, spritePath);
                    break;
                case TORSO:
                    this.torso.placeItemIfEmpty(itemID, spritePath);
                    break;
                case LEGS:
                    this.legs.placeItemIfEmpty(itemID, spritePath);
                    break;
                case FEET:
                    this.feet.placeItemIfEmpty(itemID, spritePath);
                    break;
                case L_HAND:
                    this.leftHand.placeItemIfEmpty(itemID, spritePath);
                    break;
                case R_HAND:
                    this.rightHand.placeItemIfEmpty(itemID, spritePath);
                    break;
            }
         }
    }

    public void drawInventorySprites(Graphics g) {
        for (InventoryBoxUI box : this.boxes) {
            // for every box that has an item
            if (box.hasItem()) {
                g.drawSprite(box.getSprite(), box.getX(), box.getY());
            }
        }

        // draw body parts if they have items
        if (this.head.hasItem()) {
            g.drawSprite(this.head.getSprite(), this.head.getX(), this.head.getY());
        }
        if (this.torso.hasItem()) {
            g.drawSprite(this.torso.getSprite(), this.torso.getX(), this.torso.getY());
        }
        if (this.legs.hasItem()) {
            g.drawSprite(this.legs.getSprite(), this.legs.getX(), this.legs.getY());
        }
        if (this.feet.hasItem()) {
            g.drawSprite(this.feet.getSprite(), this.feet.getX(), this.feet.getY());
        }
        if (this.leftHand.hasItem()) {
            g.drawSprite(this.leftHand.getSprite(), this.leftHand.getX(), this.leftHand.getY());
        }
        if (this.rightHand.hasItem()) {
            g.drawSprite(this.rightHand.getSprite(), this.rightHand.getX(), this.rightHand.getY());
        }
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

        this.boxes = new ArrayList<InventoryBoxUI>(this.numberOfBoxes);

        // create 4 row of 3 boxes
        for (int i = 0; i < this.numberofBoxRows; i++) {
            for (int j = 0; j < this.numberOfBoxColumns; j++) {
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
            System.out.println("out of window width");
            return -1;
        }
        else if (y < this.portraitY || y > this.portraitY + this.windowHeight) {
            System.out.println("out of window height");
            return -1;
        }

      //  if (x < this.boxes.get(2).getX()) {
            // check left side of window

            // the only clickable areas are on the bottom section
            if (y >= this.portraitY + this.portraitSize) {

                // check every box
                if (this.boxes.get(0).isClickedOn(x,y)) {
                    return INVENTORY0;
                }
                else if (this.boxes.get(1).isClickedOn(x,y)) {
                    return INVENTORY1;
                }
                else if (this.boxes.get(2).isClickedOn(x,y)) {
                    return INVENTORY2;
                }
                else if (this.boxes.get(3).isClickedOn(x,y)) {
                    return INVENTORY3;
                }
                else if (this.boxes.get(4).isClickedOn(x,y)) {
                    return INVENTORY4;
                }
                else if (this.boxes.get(5).isClickedOn(x,y)) {
                    return INVENTORY5;
                }
                else if (this.boxes.get(6).isClickedOn(x,y)) {
                    return INVENTORY6;
                }
                else if (this.boxes.get(7).isClickedOn(x,y)) {
                    return INVENTORY7;
                }
                else if (this.boxes.get(8).isClickedOn(x,y)) {
                    return INVENTORY8;
                }
                else if (this.boxes.get(9).isClickedOn(x,y)) {
                    return INVENTORY9;
                }
                else if (this.boxes.get(10).isClickedOn(x,y)) {
                    return INVENTORY10;
                }
                else if (this.boxes.get(11).isClickedOn(x,y)) {
                    return INVENTORY11;
                }
                else if (this.rightHand.isClickedOn(x,y)) {
                    return R_HAND;
                }
                else if (this.leftHand.isClickedOn(x,y)) {
                    return L_HAND;
                }
                else if (this.head.isClickedOn(x,y)) {
                    return HEAD;
                }
                else if (this.torso.isClickedOn(x,y)) {
                    return TORSO;
                }
                else if (this.legs.isClickedOn(x,y)) {
                    return LEGS;
                }
                else if (this.feet.isClickedOn(x,y)) {
                    return FEET;
           }
       // }
       /* else {
            // check right side of window

            // the only clickable areas are on the bottom section
            if (y > this.portraitY + this.portraitSize) {

                if (this.rightHand.isClickedOn(x,y)) {
                    System.out.println("r hand");
                    return R_HAND;
                }
                else if (this.leftHand.isClickedOn(x,y)) {
                    System.out.println("l hand");
                    return L_HAND;
                }
                else if (this.head.isClickedOn(x,y)) {
                    System.out.println("head");
                    return HEAD;
                }
                else if (this.torso.isClickedOn(x,y)) {
                    System.out.println("torso");
                    return TORSO;
                }
                else if (this.legs.isClickedOn(x,y)) {
                    System.out.println("legs");
                    return LEGS;
                }
                else if (this.feet.isClickedOn(x,y)) {
                    System.out.println("feet");
                    return FEET;
                }
            }*/
        }

        return -1;
    }

    public int getX() {
        // portrait start on the top left of the window
        return this.portraitX;
    }

    public int getY() {
        // portrait start on the top left of the window
        return this.portraitY;
    }

    public int getWidth() {
        return this.windowWidth;
    }

    public int getHeight() {
        return this.windowHeight;
    }


    public void drawOutlineToDebug(Graphics g) {

        g.drawRect(this.portraitX, this.portraitY, this.portraitSize, this.portraitSize);

        for (InventoryBoxUI box : this.boxes) {
            g.drawRect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
        }

        g.drawRect(this.leftHand.getX(), this.leftHand.getY(), this.leftHand.getWidth(), this.leftHand.getHeight());
        g.drawRect(this.head.getX(), this.head.getY(), this.head.getWidth(), this.head.getHeight());
        g.drawRect(this.torso.getX(), this.torso.getY(), this.torso.getWidth(), this.torso.getHeight());
        g.drawRect(this.legs.getX(), this.legs.getY(), this.legs.getWidth(), this.legs.getHeight());
        g.drawRect(this.feet.getX(), this.feet.getY(), this.feet.getWidth(), this.feet.getHeight());
        g.drawRect(this.rightHand.getX(), this.rightHand.getY(), this.rightHand.getWidth(), this.rightHand.getHeight());

    }
}
