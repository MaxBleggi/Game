package com.mystudio.dungeon_adventure.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.dungeon_adventure.helpers.InputHandler;
import com.mystudio.dungeon_adventure.model.PlayerBaseClass;
import org.mini2Dx.core.collisions.QuadTree;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.tiled.TileLayer;
import org.mini2Dx.tiled.collisions.TiledCollisionMapper;

import java.io.IOException;

public class DungeonScreen extends BasicGameScreen {
    public static final int ID = 2;
    private DungeonTiledMap tiledMap;
    private InputHandler inputProcessor;


    private byte[][] collisions;

    private Sprite sprite;
    private CollisionPoint point;

    @Override
    public void initialise(GameContainer gc) {
        // attempt to load the map
        try {
            this.tiledMap = new DungeonTiledMap(Gdx.files.internal("test2.tmx"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        this.collisions = TiledCollisionMapper.mapCollisionsByLayer(tiledMap, "Walls");
      //  System.out.println(this.collisions.length);


        // get user input handler
        this.inputProcessor = new InputHandler();
        Gdx.input.setInputProcessor(inputProcessor);

        this.point = new CollisionPoint();


        this.sprite = new Sprite(new Texture(Gdx.files.internal("player1.png")));
        this.sprite.setSize(15,20);
        this.sprite.setPosition(16*3, 16*3);
        this.point.set(16*3,16*3);
    }

    @Override
    public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta) {
        //preUpdate() must be calculated before any changes are made to the CollisionPoint
        this.point.preUpdate();

        float xMove = 0;
        float yMove = 0;
        if (PlayerBaseClass.upMove) {
            yMove = -2f;
        }
        if (PlayerBaseClass.downMove) {
            yMove = 2f;
        }
        if (PlayerBaseClass.leftMove) {
            xMove = -2f;
        }
        if (PlayerBaseClass.rightMove) {
            xMove = 2f;
        }

        // check if next tile will be a collision
        int nextXTile = (int)(this.point.getX() + xMove) / 16;
        int nextYTile = (int)(this.point.getY() + yMove) / 16;
        System.out.println("tile x: " + nextXTile);
        System.out.println("tile y: " + nextYTile);

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


        // draw tiled map at 0,0
        tiledMap.draw(g, 0, 0);

        g.drawSprite(sprite, point.getRenderX(), point.getRenderY());

    }

    @Override
    public int getId() {
        return ID;
    }
}
