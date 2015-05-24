package edu.cesi.libgdx.frogger.utils;

public class Constants 
{
	/**
	 * Immutables datas.
	 */

	/* General */
	public static final String TITLE = "frogger";
	public static final String VERSION = "0.1";
	
	/*Game data*/
	public static final int DEFAULT_LIFE = 4;
	public static final int DEFAULT_TIMER_TIME = 60;
	public static final int NUMBER_OF_FLAG = 4;
	
	public static final int NUMBER_OF_HIGH_SCORE = 5;
	
	/*map related*/
	public static final int TIER_1_Y = 0;
	public static final int TIER_2_Y = 410;
	public static final int TIER_3_Y = 610;
	
	/*map*/
	public static final String TMX_TILED_MAP_1200x800  = "gameAsset/BasicMap1200x800.tmx";
	public static final String TMX_TILED_MAP_800x480   = "gameAsset/BasicMap800x480.tmx";
	public static final String TMX_TILED_MAP_1440x900  = "gameAsset/BasicMap1440x900.tmx";
	
	/*Textures*/
	public static final String flyTextureAtlas         = "gameAsset/fly.pack";
	public static final String heartTextureAtlas       = "gameAsset/hearts.pack";
	public static final String flagTextureAtlas        = "gameAsset/greenFlag.pack";
	public static final String fireballTextureAtlas    = "gameAsset/fireball.pack";
	public static final String barrelTextureAtlas      = "gameAsset/barrel.pack";
	public static final String shurikenTextureAtlas    = "gameAsset/shuriken.pack";
	public static final String winplayerTextureAtlas   = "gameAsset/winplayer.pack";
	public static final String bushsTextureAtlas       = "gameAsset/bushs.pack";
	public static final String playerTextureAtlas      = "playerAsset/player.pack";
	public static final String progressBarTextureAtlas = "progressBar/loading.pack";
	
	/*Velocity*/
	public static final int VELOCITY_ITEM_EASY    = 2;
	public static final int VELOCITY_ITEM_EASY_2  = 2;
	
	public static final int VELOCITY_ITEM_NORMAL  = 3;
	public static final int VELOCITY_ITEM_HARD    = 4;

	public static final int VELOCITY_PLAYER       = 4;
	
	public static final int   DEFAULT_POSITION_SMALLBUSH_X  = 0;
	public static final int   DEFAULT_POSITION_MEDIUMBUSH_X  = 0;
	public static final int   DEFAULT_POSITION_BIGBUSH_X  = 0;
	
	public static final int   DEFAULT_POSITION_SHURIKEN_X = 0;
	public static final int   DEFAULT_POSITION_FIREBALL_X = 1200;
	
	public static final int NUMBER_OF_FIREBALL_EASY   = 3;
	public static final int NUMBER_OF_SHURIKEN_EASY   = 3;
	public static final int NUMBER_OF_BARREL_EASY     = 8;
	
	public static final int NUMBER_OF_FIREBALL_NORMAL = 8;
	public static final int NUMBER_OF_SHURIKEN_NORMAL = 8;
	public static final int NUMBER_OF_BARREL_NORMAL   = 8;
	
	public static final int NUMBER_OF_FIREBALL_HARD   = 12;
	public static final int NUMBER_OF_SHURIKEN_HARD   = 12;
	public static final int NUMBER_OF_BARREL_HARD     = 13;
	
	public static final int NUMBER_OF_BIG_BUSH        = 3;
	public static final int NUMBER_OF_MEDIUM_BUSH     = 4;
	public static final int NUMBER_OF_SMALL_BUSH      = 6;
	
	public static final int[] POSITION_FLAG_X =  {200, 500, 800, 1100, 1500};
	public static final int POSITION_FLAG_Y   =  630;

}
