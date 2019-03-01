package com.mystudio.dungeon_adventure.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.dungeon_adventure.helpers.GameConstants;
import com.mystudio.dungeon_adventure.helpers.SaveState;
import com.mystudio.dungeon_adventure.data.Player.PlayerBasicClass;
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
    private PlayerBasicClass player;

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
            this.tiledMap = new DungeonTiledMap(Gdx.files.internal(GameConstants.TILED_DUNGEON_MAP));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // get player info
        this.player = (PlayerBasicClass) SaveState.loadObject(SaveState.PLAYER_SAVE_STATE);



        this.collisions = TiledCollisionMapper.mapCollisionsByLayer(tiledMap, GameConstants.DUNGEON_COLLISION_LAYER);

        this.point = new CollisionPoint();

        try {
            this.sprite = new Sprite(new Texture(Gdx.files.internal(this.player.getTextureFile())));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        sprite.setSize(15,20);
        this.point.set(TILE_SIZE*4,TILE_SIZE*4);
    }

    @Override
    public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta) {
        // open inventory if player pressed I
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            // navigate to inventory screen
            screenManager.enterGameScreen(InventoryScreen.ID, new FadeOutTransition(), new FadeInTransition());
        }
        
        //preUpdate() must be calculated before any changes are made to the CollisionPoint
        this.point.preUpdate();

        // calculate the next coordinates for player to move
        float yMove = this.player.getPlayerYmovement();
        float xMove = this.player.getPlayerXmovement();
        int nextXTile = (int)(this.point.getX() + xMove) / TILE_SIZE;
        int nextYTile = (int)(this.point.getY() + yMove) / TILE_SIZE;

        // only move if it's not colliding with anything
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
        int playerDistanceFromRightEdge = point.getRenderX() % GameConstants.SCREEN_WIDTH;
        int playerDistanceFromBottomEdge = point.getRenderY() % GameConstants.SCREEN_HEIGHT;

        offSetY = getOffsetY();
        offsetX = getOffsetX();

        int floorLayer = tiledMap.getLayerIndex(GameConstants.DUNGEON_GROUND_LAYER);
        int wallsLayer = tiledMap.getLayerIndex(GameConstants.DUNGEON_WALL_LAYER);



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

        if (playerY >= GameConstants.SCREEN_HEIGHT) {
            offset = GameConstants.SCREEN_HEIGHT;
        }
        if (playerY >= GameConstants.SCREEN_HEIGHT*2) {
            offset = GameConstants.SCREEN_HEIGHT*2;
        }
        if (playerY >= GameConstants.SCREEN_HEIGHT*3) {
            offset = GameConstants.SCREEN_HEIGHT*3;
        }

        return offset;
    }

    public int getOffsetX() {
        int playerX = point.getRenderX();
        int offset = 0;

        if (playerX >= GameConstants.SCREEN_WIDTH) {
            offset = GameConstants.SCREEN_WIDTH;
        }
        if (playerX >= GameConstants.SCREEN_WIDTH*2) {
            offset = GameConstants.SCREEN_WIDTH*2;
        }
        if (playerX >= GameConstants.SCREEN_WIDTH*3) {
            offset = GameConstants.SCREEN_WIDTH*3;
        }

        return offset;
    }
}
