package com.mystudio.dungeon_adventure.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.tiled.TileLayer;
import org.mini2Dx.tiled.TiledMap;

import java.io.IOException;

public class DungeonTiledMap extends TiledMap {

    public DungeonTiledMap(FileHandle fileHandle) throws IOException {
        super(fileHandle);
    }

    @Override
    protected void onLayerRendered(Graphics g, TileLayer layer, int startTileX, int startTileY, int widthInTiles,
                                   int heightInTiles) {

        if (layer.getName().compareTo("COLLISIONS") == 0) {
            // TODO: render sprites between layers here

        }
    }


}
