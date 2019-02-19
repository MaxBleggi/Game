package com.mystudio.dungeon_adventure.view.Inventory;

import com.mystudio.dungeon_adventure.helpers.Wearables;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

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
    // 1st row of boxes
    private InventoryBoxUI box1, box2, box3;
    // 2nd row of boxes
    private InventoryBoxUI box4, box5, box6;
    // 3rd row of boxes
    private InventoryBoxUI box7, box8, box9;
    // 4th row of boxes
    private InventoryBoxUI box10, box11, box12;

    // body parts
    private InventoryBodyPartUI head;
    private InventoryBodyPartUI torso;
    private InventoryBodyPartUI legs;
    private InventoryBodyPartUI feet;

    // hands
    private InventoryHandsUI leftHand;
    private InventoryHandsUI rightHand;
    /* - - - - - - - - - - - - - - - - - - - - - */

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
        // portrait
        this.portraitX = x;
        this.portraitY = y;

        // --- create lower section ---
        int lowerSectionY = y + this.portraitSize + this.upperSectionPadding;

        // create the boxes
        int incrementedX = x;
        this.box1 = new InventoryBoxUI(incrementedX, lowerSectionY, this.boxSize, this.boxSize);
        incrementedX += this.boxSize + this.boxPadding;
        this.box2 = new InventoryBoxUI(incrementedX, lowerSectionY, this.boxSize, this.boxSize);
        incrementedX += this.boxSize + this.boxPadding;
        this.box3 = new InventoryBoxUI(incrementedX, lowerSectionY, this.boxSize, this.boxSize);

        incrementedX = x;
        int adjY = lowerSectionY + this.boxSize + this.boxPadding;
        this.box4 = new InventoryBoxUI(incrementedX, adjY, this.boxSize, this.boxSize);
        incrementedX += this.boxSize + this.boxPadding;
        this.box5 = new InventoryBoxUI(incrementedX, adjY, this.boxSize, this.boxSize);
        incrementedX += this.boxSize + this.boxPadding;
        this.box6 = new InventoryBoxUI(incrementedX, adjY, this.boxSize, this.boxSize);

        incrementedX = x;
        adjY = lowerSectionY + this.boxSize*2 + this.boxPadding*2;
        this.box7 = new InventoryBoxUI(incrementedX, adjY, this.boxSize, this.boxSize);
        incrementedX += this.boxSize + this.boxPadding;
        this.box8 = new InventoryBoxUI(incrementedX, adjY, this.boxSize, this.boxSize);
        incrementedX += this.boxSize + this.boxPadding;
        this.box9 = new InventoryBoxUI(incrementedX, adjY, this.boxSize, this.boxSize);


        // left hand
        int handsY = lowerSectionY + this.boxSize + this.boxSize/2;
        incrementedX = x + this.boxSize*3 + boxPadding*2 + middlePadding;
        this.leftHand = new InventoryHandsUI(incrementedX, handsY, this.boxSize, this.boxSize,true);

        // create  the body parts
        incrementedX += this.leftHand.getWidth() + bodyPadding;
        adjY = lowerSectionY;
        this.head = new InventoryBodyPartUI(incrementedX, adjY, this.boxSize, this.boxSize, Wearables.HEAD);
        adjY += this.head.getHeight() + bodyPadding;
        this.torso = new InventoryBodyPartUI(incrementedX, adjY, this.boxSize, this.boxSize, Wearables.TORSO);
        adjY += this.torso.getHeight() + bodyPadding;
        this.legs = new InventoryBodyPartUI(incrementedX, adjY, this.boxSize, this.boxSize, Wearables.LEGS);
        adjY += this.legs.getHeight() + bodyPadding;
        this.feet = new InventoryBodyPartUI(incrementedX, adjY, this.boxSize, this.boxSize, Wearables.FEET);

        // right hand
        incrementedX += this.torso.getWidth() + bodyPadding;
        this.rightHand = new InventoryHandsUI(incrementedX, handsY, this.boxSize, this.boxSize,false);
    }

    public void drawOutlineToDebug(Graphics g) {

        g.drawRect(this.portraitX, this.portraitY, this.portraitSize, this.portraitSize);

        g.drawRect(this.box1.getX(), this.box1.getY(), this.box1.getWidth(), this.box1.getHeight());
        g.drawRect(this.box2.getX(), this.box2.getY(), this.box2.getWidth(), this.box2.getHeight());
        g.drawRect(this.box3.getX(), this.box3.getY(), this.box3.getWidth(), this.box3.getHeight());
        g.drawRect(this.box4.getX(), this.box4.getY(), this.box4.getWidth(), this.box4.getHeight());
        g.drawRect(this.box5.getX(), this.box5.getY(), this.box5.getWidth(), this.box5.getHeight());
        g.drawRect(this.box6.getX(), this.box6.getY(), this.box6.getWidth(), this.box6.getHeight());
        g.drawRect(this.box7.getX(), this.box7.getY(), this.box7.getWidth(), this.box7.getHeight());
        g.drawRect(this.box8.getX(), this.box8.getY(), this.box8.getWidth(), this.box8.getHeight());
        g.drawRect(this.box9.getX(), this.box9.getY(), this.box9.getWidth(), this.box9.getHeight());

        g.drawRect(this.leftHand.getX(), this.leftHand.getY(), this.leftHand.getWidth(), this.leftHand.getHeight());
        g.drawRect(this.head.getX(), this.head.getY(), this.head.getWidth(), this.head.getHeight());
        g.drawRect(this.torso.getX(), this.torso.getY(), this.torso.getWidth(), this.torso.getHeight());
        g.drawRect(this.legs.getX(), this.legs.getY(), this.legs.getWidth(), this.legs.getHeight());
        g.drawRect(this.feet.getX(), this.feet.getY(), this.feet.getWidth(), this.feet.getHeight());
        g.drawRect(this.rightHand.getX(), this.rightHand.getY(), this.rightHand.getWidth(), this.rightHand.getHeight());

    }
}
