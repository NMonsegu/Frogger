package edu.cesi.libgdx.frogger.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;

import edu.cesi.libgdx.frogger.model.strategy.Entity;
import edu.cesi.libgdx.frogger.model.strategy.LinearMovementsLeft;

public class BigBush extends Entity
{
	private Animation bigBushAnimation;
	
	public BigBush(){
        bounds = new Rectangle();            
        bounds.x = 0;
        bounds.y = 0;                         
        bounds.width = 180;
        bounds.height = 30;
        stateTime = 0f;
         
        loadAnimation();

        currentAnimation = bigBushAnimation;
        this.mouvement = new LinearMovementsLeft();
	}
	
	private void loadAnimation(){

		bigBushAnimation = new Animation(0.6f,imageManager.getBigBushTextureRegion());
	}
}
