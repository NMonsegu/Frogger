package edu.cesi.libgdx.frogger.model.strategy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface RenderBehavior {
	/**
	 * Contain the draw method to display graphic content.
	 ***/
	public void draw(SpriteBatch batch,Rectangle bounds,Animation texture,float stateTime);
}