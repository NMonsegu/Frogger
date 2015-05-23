package edu.cesi.libgdx.frogger.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;

import edu.cesi.libgdx.frogger.model.strategy.Entity;
import edu.cesi.libgdx.frogger.model.strategy.LinearMovementsRight;

public class Shuriken extends Entity
{
	private Animation shurikenAnimation;
	
	public Shuriken(){
        bounds = new Rectangle();            
        bounds.x = 1200;
        bounds.y = 200;                         
        bounds.width = 58;
        bounds.height = 59;
        stateTime = 0f;
        
        loadAnimation();

        currentAnimation = shurikenAnimation;
        this.mouvement = new LinearMovementsRight();
	}
	
	private void loadAnimation(){
		shurikenAnimation = new Animation(0.5f/10,imageManager.getShurikenTextureRegion());
	}
}

