package edu.cesi.libgdx.frogger.model.strategy;

import com.badlogic.gdx.math.Rectangle;

public interface MovementBehavior {
	
	/**
	 * Contain the general movement interface.
	 ***/
	
	
	 public void move(Rectangle bounds,int velocity);
}