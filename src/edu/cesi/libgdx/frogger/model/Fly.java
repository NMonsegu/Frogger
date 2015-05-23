package edu.cesi.libgdx.frogger.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;

import edu.cesi.libgdx.frogger.model.strategy.Entity;
import edu.cesi.libgdx.frogger.model.strategy.Motionless;

public class Fly extends Entity
{

	private Animation flyAnimation;
	
	public Fly()
	{
        bounds = new Rectangle();            
        bounds.x = 1200;
        bounds.y = 200;                         
        bounds.width = 58;
        bounds.height = 36;
        stateTime = 0f;
         
        loadAnimation();

        currentAnimation = flyAnimation;
        this.mouvement = new Motionless();
	}
	
	private void loadAnimation(){

		
		flyAnimation = new Animation(0.3f,imageManager.getFlyTextureRegion());
	}
}
