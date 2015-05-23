package edu.cesi.libgdx.frogger.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;

import edu.cesi.libgdx.frogger.model.strategy.Entity;
import edu.cesi.libgdx.frogger.model.strategy.Motionless;

public class Flag extends Entity
{
	private Animation flagAnimation;
	
	public Flag(){
        bounds = new Rectangle();            
        bounds.x = 0;
        bounds.y = 0;                         
        bounds.width = 94;
        bounds.height = 128;
        stateTime = 0f;
         
        loadAnimation();

        currentAnimation = flagAnimation;
        this.mouvement = new Motionless();
	}
	
	
	private void loadAnimation(){
		flagAnimation = new Animation(0.1f,imageManager.getFlagTextureRegion());
	}

}
