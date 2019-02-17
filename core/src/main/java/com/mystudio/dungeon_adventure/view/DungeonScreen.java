package com.mystudio.dungeon_adventure.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mystudio.dungeon_adventure.helpers.InputHandler;
import com.mystudio.dungeon_adventure.model.Player.PlayerBasicClass;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;
import org.mini2Dx.tiled.collisions.TiledCollisionMapper;

import java.io.IOException;

public class DungeonScreen extends BasicGameScreen {
    public static final int ID = 2;
    private DungeonTiledMap tiledMap;
    private InputHandler inputProcessor;
    private PlayerBasicClass player;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private final int TILE_SIZE = 16;
    private final int TILED_HEIGHT = 16*500;
    private final int TILED_WIDTH = 16*500;

    private byte[][] collisions;

    private Sprite sprite;
    private CollisionPoint point;

    @Override
    public void initialise(GameContainer gc) {
        // attempt to load the map
        try {
            this.tiledMap = new DungeonTiledMap(Gdx.files.internal("test1.tmx"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        this.player = new PlayerBasicClass("testName");

        this.collisions = TiledCollisionMapper.mapCollisionsByLayer(tiledMap, "WallsLayer");

        // get user input handler
        this.inputProcessor = new InputHandler();
        Gdx.input.setInputProcessor(inputProcessor);

        // aliase player sprite and cPoint
        this.point = this.player.getCollisionPoint();
        this.sprite = this.player.getPlayerSprite();

        this.point.set(TILE_SIZE*4,TILE_SIZE*4);
    }

    @Override
    public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta) {

        // check if player opened inventory
        if (this.player.isInventoryOPen()) {
            // navigate to inventory screen
            screenManager.enterGameScreen(DungeonScreen.ID, new FadeOutTransition(), new FadeInTransition());
        }

        
        //preUpdate() must be calculated before any changes are made to the CollisionPoint
        this.point.preUpdate();

        float yMove = this.player.getPlayerYmove();
        float xMove = this.player.getPlayerXmove();

        // check if next tile will be a collision
        int nextXTile = (int)(this.point.getX() + xMove) / TILE_SIZE;
        int nextYTile = (int)(this.point.getY() + yMove) / TILE_SIZE;

       if (collisions[nextXTile][nextYTile] == 0) {
            // Move the point by 4 pixels on the X and Y axis
            this.point.set(this.point.getX() + xMove, this.point.getY() + yMove);
       }
       else {
           // collision
       }

    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {
        //This method uses the lerp (linear interpolate) method from LibGDX
        //to interpolate between the previous and current positions
        //and set the render coordinates correctly
        point.interpolate(null, alpha);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.setBackgroundColor(Color.WHITE);
        int offsetX = 0;
        int offSetY = 0;

        // draw tiled map at 0,0
        int playerDistanceFromRightEdge = point.getRenderX() % WINDOW_WIDTH;
        int playerDistanceFromBottomEdge = point.getRenderY() % WINDOW_HEIGHT;

        offSetY = getOffsetY();
        offsetX = getOffsetX();

        int floorLayer = tiledMap.getLayerIndex("groundLayer");
        int wallsLayer = tiledMap.getLayerIndex("WallsLayer");



       // tiledMap.onLayerRendered(g, floor, point.getRenderX() + (-offsetX),point.getRenderY() + (-offSetY),1,1);

        // draw floor
        tiledMap.draw(g, -offsetX, -offSetY, floorLayer);

        // draw sprites between ground and raised layers
        g.drawSprite(sprite, point.getRenderX() + (-offsetX), point.getRenderY() + (-offSetY));

        // draw wall layer
        tiledMap.draw(g, -offsetX, -offSetY, wallsLayer);
    }

    @Override
    public int getId() {
        return ID;
    }


    public int getOffsetY() {
        int playerY = point.getRenderY();
        int offset = 0;

        if (playerY >= WINDOW_HEIGHT) {
            offset = WINDOW_HEIGHT;
        }
        if (playerY >= WINDOW_HEIGHT*2) {
            offset = WINDOW_HEIGHT*2;
        }
        if (playerY >= WINDOW_HEIGHT*3) {
            offset = WINDOW_HEIGHT*3;
        }

        return offset;
    }

    public int getOffsetX() {
        int playerX = point.getRenderX();
        int offset = 0;

        if (playerX >= WINDOW_WIDTH) {
            offset = WINDOW_WIDTH;
        }
        if (playerX >= WINDOW_WIDTH*2) {
            offset = WINDOW_WIDTH*2;
        }
        if (playerX >= WINDOW_WIDTH*3) {
            offset = WINDOW_WIDTH*3;
        }

        return offset;
    }
}
