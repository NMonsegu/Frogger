package edu.cesi.libgdx.frogger.data;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import edu.cesi.libgdx.frogger.utils.Constants;

public class MapManager 
{
	private TiledMap map;
	
	public MapManager()
	{
		
	}
	
	public TiledMap getMap()
	{
/*		if(Gdx.graphics.getWidth() == 800 && Gdx.graphics.getHeight() == 480)
		{
			this.map = new TmxMapLoader().load(Constants.TMX_TILED_MAP_800x480);
		}
		else if(Gdx.graphics.getWidth() == 1200 && Gdx.graphics.getHeight() == 800)
		{
			this.map = new TmxMapLoader().load(Constants.TMX_TILED_MAP_1200x800);
		}
		else
		{
			this.map = new TmxMapLoader().load(Constants.TMX_TILED_MAP_1440x900);
		}
		//to remove
*/		this.map = new TmxMapLoader().load(Constants.TMX_TILED_MAP_1200x800);
		return this.map;
	}
	
	
}
