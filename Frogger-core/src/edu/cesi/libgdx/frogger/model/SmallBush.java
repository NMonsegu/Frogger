package edu.cesi.libgdx.frogger.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;

import edu.cesi.libgdx.frogger.model.strategy.Entity;
import edu.cesi.libgdx.frogger.model.strategy.LinearMovementsLeft;

public class SmallBush extends Entity
{
	private Animation smallBushAnimation;
	
	public SmallBush(){
        bounds = new Rectangle();            
        bounds.x = 0;
        bounds.y = 0;                         
        bounds.width = 60;
        bounds.height = 30;
        stateTime = 0f;
         
        loadAnimation();

        currentAnimation = smallBushAnimation;
        this.mouvement = new LinearMovementsLeft();
	}
	
	private void loadAnimation(){

		smallBushAnimation = new Animation(0.6f,imageManager.getSmallBushTextureRegion());
	}
}
