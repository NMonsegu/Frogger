package edu.cesi.libgdx.frogger.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;

import edu.cesi.libgdx.frogger.model.strategy.Entity;
import edu.cesi.libgdx.frogger.model.strategy.LinearMovementsLeft;

public class FireBall extends Entity
{
	private Animation fireBallAnimation;
	
	public FireBall(){
        bounds = new Rectangle();            
        bounds.x = 1200;
        bounds.y = 200;                         
        bounds.width = 30;
        bounds.height = 27;
        stateTime = 0f;
         
        loadAnimation();

        currentAnimation = fireBallAnimation;
        this.mouvement = new LinearMovementsLeft();
	}
	
	
	private void loadAnimation(){

		fireBallAnimation = new Animation(0.5f,imageManager.getFireballTextureRegion());
	}

}
