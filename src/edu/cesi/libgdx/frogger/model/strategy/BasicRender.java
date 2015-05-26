package edu.cesi.libgdx.frogger.model.strategy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class BasicRender implements RenderBehavior {
	
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	/**
	 *  This is a RenderBehavior implementation draw an item and the associated animation
	 *  
	 *  @param batch : The current graphic content (SpriteBatch)
	 *  @param bounds : A rectangle to move
	 *  @param animation : the animation to draw
	 *  @param stateTime : The current delta time
	 *  */
	
	@Override
	public void draw(SpriteBatch batch, Rectangle bounds, Animation animation, float stateTime) {
		
		batch.draw(animation.getKeyFrame(stateTime,true), bounds.x,bounds.y);
	}
}