package edu.cesi.libgdx.frogger.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.cesi.libgdx.frogger.model.strategy.Entity;

/**Each level must implement this interface. It provide the minimal methods to create a level*/
public interface Level 
{
	/**
	 * Used to create elements 
	 * */
	public void createElements();
	
	/**
	 * Used to update elements
	 * @param stateTime current state time
	 * @param SpriteBatch OpenGL drawer
	 * */
	public void updateElements(float stateTime, SpriteBatch batch);
	
	/**
	 * Used to reset the current level. It must allow to launch a new party
	 * */
	public void resetLevel();
	
	/**
	 * Used to apply some effect when is selected entity is collide with something
	 * @param Entity usually the player
	 * */
	public void applyCollisionEffect(Entity entity);
}
