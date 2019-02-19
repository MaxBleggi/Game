package com.mystudio.dungeon_adventure.view.Inventory;

import com.mystudio.dungeon_adventure.helpers.Wearables;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

public class InventoryWindowUI {
    public Sprite portrait;
    public Sprite healthBar;
    public String nameLabel;

    // 1st row of boxes
    public InventoryBoxUI box1, box2, box3;
    // 2nd rox
    public InventoryBoxUI box4, box5, box6;
    // 3rd rox
    public InventoryBoxUI box7, box8, box9;

    public InventoryBodyPartUI head;
    public InventoryBodyPartUI torso;
    public InventoryBodyPartUI legs;
    public InventoryBodyPartUI feet;

    public InventoryHandsUI leftHand;
    public InventoryHandsUI rightHand;

    private int screenWidth;
    private int screenHeight;
    private int boxPadding;
    private int middlePadding;

  //  private int windowX;
  //  private int windowY;

    private int boxHeight;
    private int boxWidth;

    // player portrait
    private int portraitX;
    private int portraitY;
    private int portraitW;
    private int portraitH;

    private int upperSectionPadding;

    private int nameX;
    private int nameY;

    public InventoryWindowUI(int screenWidth, int screenHeight, int boxWidth, int boxHeight, int boxPadding, int middlePadding) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.boxPadding = boxPadding;
        this.middlePadding = middlePadding;
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;


        this.portraitW = boxWidth*2;
        this.portraitH = boxHeight*2;
        this.upperSectionPadding = 20;

        // find total width of inventory window
        // width = box - padding - box - padding - box - middle - hand - body(twice the size of a box) - hand
        int w = boxWidth*3 + boxPadding*2 + middlePadding + boxWidth + boxWidth*2+ boxWidth;
        int h = this.portraitH +  boxHeight*4 + boxPadding*3 + this.upperSectionPadding;

        // center window onto screen
        int x = (screenWidth / 2) - (w / 2);
        int y = (screenHeight / 2) - (h / 2);


        // create upper section
        this.portraitX = x;
        this.portraitY = y;

        int lowerSectionY = y + this.portraitH + this.upperSectionPadding;

        // create the boxes relative to the inventory window
        int incrementedX = x;
        this.box1 = new InventoryBoxUI(incrementedX, lowerSectionY, boxWidth, boxHeight);
        incrementedX += boxWidth + boxPadding;
        this.box2 = new InventoryBoxUI(incrementedX, lowerSectionY, boxWidth, boxHeight);
        incrementedX += boxWidth + boxPadding;
        this.box3 = new InventoryBoxUI(incrementedX, lowerSectionY, boxWidth, boxHeight);

        incrementedX = x;
        int adjY = lowerSectionY + boxHeight+boxPadding;
        this.box4 = new InventoryBoxUI(incrementedX, adjY, boxWidth, boxHeight);
        incrementedX += boxWidth + boxPadding;
        this.box5 = new InventoryBoxUI(incrementedX, adjY, boxWidth, boxHeight);
        incrementedX += boxWidth + boxPadding;
        this.box6 = new InventoryBoxUI(incrementedX, adjY, boxWidth, boxHeight);

        incrementedX = x;
        adjY = lowerSectionY + boxHeight+boxPadding + boxHeight + boxPadding;
        this.box7 = new InventoryBoxUI(incrementedX, adjY, boxWidth, boxHeight);
        incrementedX += boxWidth + boxPadding;
        this.box8 = new InventoryBoxUI(incrementedX, adjY, boxWidth, boxHeight);
        incrementedX += boxWidth + boxPadding;
        this.box9 = new InventoryBoxUI(incrementedX, adjY, boxWidth, boxHeight);


        // left hand
        int handsY = lowerSectionY + boxHeight + boxHeight/2;
        incrementedX = x + boxWidth*3 + boxPadding*2 + middlePadding;
        this.leftHand = new InventoryHandsUI(incrementedX, handsY, boxWidth, boxHeight,true);

        int bodyPadding = 10;

        // create  the body parts
        incrementedX += this.leftHand.getWidth() + bodyPadding;
        adjY = lowerSectionY;
        this.head = new InventoryBodyPartUI(incrementedX, adjY, boxWidth, boxHeight, Wearables.HEAD);
        adjY += this.head.getHeight() + bodyPadding;
        this.torso = new InventoryBodyPartUI(incrementedX, adjY, boxWidth, boxHeight, Wearables.TORSO);
        adjY += this.torso.getHeight() + bodyPadding;
        this.legs = new InventoryBodyPartUI(incrementedX, adjY, boxWidth, boxHeight, Wearables.LEGS);
        adjY += this.legs.getHeight() + bodyPadding;
        this.feet = new InventoryBodyPartUI(incrementedX, adjY, boxWidth, boxHeight, Wearables.FEET);

        incrementedX += this.torso.getWidth() + bodyPadding;
        this.rightHand = new InventoryHandsUI(incrementedX, handsY, boxWidth, boxHeight,false);
    }



    public void draw(Graphics g) {

        g.drawRect(this.portraitX, this.portraitY, this.portraitW, this.portraitH);

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
