package edu.cesi.libgdx.frogger.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;

import edu.cesi.libgdx.frogger.model.strategy.Entity;
import edu.cesi.libgdx.frogger.model.strategy.Motionless;

public class AtHomeFrogger extends Entity
{	
	private Animation winplayerAnimation;
	
	public AtHomeFrogger(){
        bounds = new Rectangle();            
        bounds.x = 0;
        bounds.y = 0;                         
        bounds.width = 57;
        bounds.height = 76;
        stateTime = 0f;
         
        loadAnimation();

        currentAnimation = winplayerAnimation;
        this.mouvement = new Motionless();
	}
	
	private void loadAnimation()
	{
		winplayerAnimation = new Animation(0.1f,imageManager.getWinPlayerTextureRegion());
	}

}
