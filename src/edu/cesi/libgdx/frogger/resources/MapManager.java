package edu.cesi.libgdx.frogger.resources;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import edu.cesi.libgdx.frogger.utils.Constants;

public class MapManager 
{
	private TiledMap map;
	
	public MapManager()
	{
		
	}
	
	/**
	 * Load and return the map
	 * */
	public TiledMap getMap()
	{
		try{
			this.map = new TmxMapLoader().load(Constants.TMX_TILED_MAP_1200x800);
			return this.map;
		}catch (NullPointerException ex)
		{
			System.err.println(ex + "No map found ");
		}catch(Exception ex)
		{
			System.err.println(ex + "Error loading map ");
		}
		return null;
	}
	
	
}
