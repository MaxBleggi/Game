package com.mystudio.dungeon_adventure.helpers;

public class GameConstants {
    public static int SCREEN_WIDTH = 800;
    public static int SCREEN_HEIGHT = 600;

    /* used for doing things related to screen size
     * determines which assets and how to size elements
     * size based of screen width and values are as follows
     *   XS - Extra small: 0px - 765px
     *   SM - Small: 768px - 991px
     *   MD - Medium: 992px - 1199px
     *   LG - Large: 1200px - 1599px
     *   XL - Extra Large: 1600px+
     */
    public static final int XS_SCREEN = 765;
    public static final int SM_SCREEN = 991;
    public static final int MD_SCREEN = 1199;
    public static final int LG_SCREEN = 1599;

    public static final String INVENTORY_WINDOW_BACKGROUND = "inventory/inventory_background.png";

    public static final String TILED_DUNGEON_MAP = "tiled/test1.tmx";

    public static final String DUNGEON_COLLISION_LAYER = "WallsLayer";
    public static final String DUNGEON_WALL_LAYER = "WallsLayer";
    public static final String DUNGEON_GROUND_LAYER = "groundLayer";
}
