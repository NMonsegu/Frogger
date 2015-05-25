package edu.cesi.libgdx.frogger.model.strategy;

import com.badlogic.gdx.math.Rectangle;

import edu.cesi.libgdx.frogger.utils.Constants;

public class LinearMovementsRight implements MovementBehavior{

	
	/**
	 * Contain the basic Linear movement right behavior it move the given rectangle depending upon the velocity
	 *
	 *@param bounds :
	 *@param velocity : 
	 ***/
	@Override
	public void move(Rectangle bounds,int velocity) {
		bounds.x += velocity ;
		if(bounds.x >= 1200 + bounds.width)
		{
			bounds.x = Constants.DEFAULT_POSITION_SHURIKEN_X;
		}
	}

}
