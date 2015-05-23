package edu.cesi.libgdx.frogger.data;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import edu.cesi.libgdx.frogger.utils.Constants;

public class ImagesManager  
{
	
	
	//TODO CHANGER CETTE CLASSE POUR UTILISER UN TEXTURE PACKER ET OPTIMISER LE JEU POUR LES TERMINAUX MOBILES
	
	private ImagesManager(){
	}
	
	private static class ImageManagerHolder{
		private final static ImagesManager instance = new ImagesManager();
	}
	
	public static ImagesManager getInstance(){
		return ImageManagerHolder.instance;
	}
	
	private AssetManager manager = new AssetManager();

	private TextureRegion[] fireballTextureRegion    = new TextureRegion[3];
	private TextureRegion[] heartTextureRegion       = new TextureRegion[1];
	private TextureRegion[] flagTextureRegion        = new TextureRegion[1];
	private TextureRegion[] flyTextureRegion         = new TextureRegion[5];
	private TextureRegion[] barrelTextureRegion      = new TextureRegion[5];
	private TextureRegion[] shurikenTextureRegion    = new TextureRegion[3];
	private TextureRegion[] winPlayerTextureRegion   = new TextureRegion[1];
	private TextureRegion[] playerWalkTextureRegion  = new TextureRegion[3];
	private TextureRegion[] playerBackTextureRegion  = new TextureRegion[3];
	private TextureRegion[] playerRightTextureRegion = new TextureRegion[3];
	private TextureRegion[] playerLeftTextureRegion  = new TextureRegion[3];
	
	private TextureRegion[] bigBushsTextureRegion    = new TextureRegion[1];
	private TextureRegion[] medimBushsTextureRegion  = new TextureRegion[1];
	private TextureRegion[] smallBushsTextureRegion  = new TextureRegion[1];
	
	private Array<AtlasRegion> progressBar = new Array<TextureAtlas.AtlasRegion>();

	public  void loadTextureAtlas()
	{
		manager.load(Constants.flyTextureAtlas,      TextureAtlas.class);
		manager.load(Constants.heartTextureAtlas,    TextureAtlas.class);
		manager.load(Constants.flagTextureAtlas,     TextureAtlas.class);
		manager.load(Constants.fireballTextureAtlas, TextureAtlas.class);
		manager.load(Constants.barrelTextureAtlas,   TextureAtlas.class);
		manager.load(Constants.shurikenTextureAtlas, TextureAtlas.class);
		manager.load(Constants.winplayerTextureAtlas,TextureAtlas.class);
		manager.load(Constants.playerTextureAtlas,   TextureAtlas.class);
		manager.load(Constants.bushsTextureAtlas,    TextureAtlas.class);
		manager.load(Constants.progressBarTextureAtlas, TextureAtlas.class);
	}
	
	public  void loadTextureRegion()
	{	
		//Block until assets are loaded
		manager.finishLoading();
		
		fireballTextureRegion[0]    = manager.get(Constants.fireballTextureAtlas,TextureAtlas.class).findRegion("fireball1");
		fireballTextureRegion[1]    = manager.get(Constants.fireballTextureAtlas,TextureAtlas.class).findRegion("fireball2");
		fireballTextureRegion[2]    = manager.get(Constants.fireballTextureAtlas,TextureAtlas.class).findRegion("fireball3");
		
		heartTextureRegion[0]       = manager.get(Constants.heartTextureAtlas,TextureAtlas.class).findRegion("heart1");
		
		flagTextureRegion[0]        = manager.get(Constants.flagTextureAtlas,TextureAtlas.class).findRegion("flag");
		
		flyTextureRegion[0]         = manager.get(Constants.flyTextureAtlas,TextureAtlas.class).findRegion("fly1");
		flyTextureRegion[1]         = manager.get(Constants.flyTextureAtlas,TextureAtlas.class).findRegion("fly2");
		flyTextureRegion[2]         = manager.get(Constants.flyTextureAtlas,TextureAtlas.class).findRegion("fly3");
		flyTextureRegion[3]         = manager.get(Constants.flyTextureAtlas,TextureAtlas.class).findRegion("fly4");
		flyTextureRegion[4]         = manager.get(Constants.flyTextureAtlas,TextureAtlas.class).findRegion("fly5");
		
		barrelTextureRegion[0]      = manager.get(Constants.barrelTextureAtlas,TextureAtlas.class).findRegion("barrel1");
		barrelTextureRegion[1]      = manager.get(Constants.barrelTextureAtlas,TextureAtlas.class).findRegion("barrel2");
		barrelTextureRegion[2]      = manager.get(Constants.barrelTextureAtlas,TextureAtlas.class).findRegion("barrel3");
		barrelTextureRegion[3]      = manager.get(Constants.barrelTextureAtlas,TextureAtlas.class).findRegion("barrel4");
		barrelTextureRegion[4]      = manager.get(Constants.barrelTextureAtlas,TextureAtlas.class).findRegion("barrel5");
		
		shurikenTextureRegion[0]    = manager.get(Constants.shurikenTextureAtlas,TextureAtlas.class).findRegion("shuriken1");
		shurikenTextureRegion[1]    = manager.get(Constants.shurikenTextureAtlas,TextureAtlas.class).findRegion("shuriken2");
		shurikenTextureRegion[2]    = manager.get(Constants.shurikenTextureAtlas,TextureAtlas.class).findRegion("shuriken3");
		
		winPlayerTextureRegion[0]   = manager.get(Constants.winplayerTextureAtlas,TextureAtlas.class).findRegion("winplayer1");

		playerBackTextureRegion[0]  = manager.get(Constants.playerTextureAtlas,TextureAtlas.class).findRegion("back1");
		playerBackTextureRegion[1]  = manager.get(Constants.playerTextureAtlas,TextureAtlas.class).findRegion("back2");
		playerBackTextureRegion[2]  = manager.get(Constants.playerTextureAtlas,TextureAtlas.class).findRegion("back3");
		
		playerWalkTextureRegion[0]  = manager.get(Constants.playerTextureAtlas,TextureAtlas.class).findRegion("front1");
		playerWalkTextureRegion[1]  = manager.get(Constants.playerTextureAtlas,TextureAtlas.class).findRegion("front2");
		playerWalkTextureRegion[2]  = manager.get(Constants.playerTextureAtlas,TextureAtlas.class).findRegion("front3");
		
		playerRightTextureRegion[0] = manager.get(Constants.playerTextureAtlas,TextureAtlas.class).findRegion("right1");
		playerRightTextureRegion[1] = manager.get(Constants.playerTextureAtlas,TextureAtlas.class).findRegion("right2");
		playerRightTextureRegion[2] = manager.get(Constants.playerTextureAtlas,TextureAtlas.class).findRegion("right3");
		
		playerLeftTextureRegion[0]  = manager.get(Constants.playerTextureAtlas,TextureAtlas.class).findRegion("left1");
		playerLeftTextureRegion[1]  = manager.get(Constants.playerTextureAtlas,TextureAtlas.class).findRegion("left2");
		playerLeftTextureRegion[2]  = manager.get(Constants.playerTextureAtlas,TextureAtlas.class).findRegion("left3");
		
		bigBushsTextureRegion[0]    = manager.get(Constants.bushsTextureAtlas,TextureAtlas.class).findRegion("bigBush");
		smallBushsTextureRegion[0]    = manager.get(Constants.bushsTextureAtlas,TextureAtlas.class).findRegion("smallBush");
		medimBushsTextureRegion[0]    = manager.get(Constants.bushsTextureAtlas,TextureAtlas.class).findRegion("mediumBush");
		
		progressBar = manager.get(Constants.progressBarTextureAtlas,TextureAtlas.class).findRegions("loading-bar-anim");
		
		
	}
	
	public  void dispose(){
		disposeTextureAtlas();
	}
	
	private  void disposeTextureAtlas(){
		manager.unload(Constants.flyTextureAtlas);
		manager.unload(Constants.heartTextureAtlas);
		manager.unload(Constants.flagTextureAtlas);
		manager.unload(Constants.fireballTextureAtlas);
		manager.unload(Constants.barrelTextureAtlas);
		manager.unload(Constants.shurikenTextureAtlas);
		manager.unload(Constants.winplayerTextureAtlas);
		manager.unload(Constants.playerTextureAtlas);
		manager.unload(Constants.progressBarTextureAtlas);
	}

	public Array<AtlasRegion> getProgressBarTextureAtlas(){
		return progressBar;
	}
	
	public TextureRegion[] getFireballTextureRegion() {
		return fireballTextureRegion;
	}

	public TextureRegion[] getHeartTextureRegion() {
		return heartTextureRegion;
	}

	public TextureRegion[] getFlagTextureRegion() {
		return flagTextureRegion;
	}

	public TextureRegion[] getFlyTextureRegion() {
		return flyTextureRegion;
	}

	public TextureRegion[] getBarrelTextureRegion() {
		return barrelTextureRegion;
	}

	public TextureRegion[] getShurikenTextureRegion() {
		return shurikenTextureRegion;
	}

	public TextureRegion[] getWinPlayerTextureRegion() {
		return winPlayerTextureRegion;
	}

	public TextureRegion[] getPlayerWalkTextureRegion() {
		return playerWalkTextureRegion;
	}

	public TextureRegion[] getPlayerBackTextureRegion() {
		return playerBackTextureRegion;
	}

	public TextureRegion[] getPlayerRightTextureRegion() {
		return playerRightTextureRegion;
	}

	public TextureRegion[] getPlayerLeftTextureRegion() {
		return playerLeftTextureRegion;
	}

	public TextureRegion[] getBigBushTextureRegion() {
		return bigBushsTextureRegion;
	}
	
	public TextureRegion[] getMediumBushTextureRegion() {
		return medimBushsTextureRegion;
	}
	
	public TextureRegion[] getSmallBushTextureRegion() {
		return smallBushsTextureRegion;
	}
	

	
	
}
