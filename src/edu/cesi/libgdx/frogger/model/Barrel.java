package edu.cesi.libgdx.frogger.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import edu.cesi.libgdx.frogger.model.strategy.Entity;
import edu.cesi.libgdx.frogger.model.strategy.LinearMovementsRight;

public class Barrel extends Entity
{
	private Animation barrelAnimationGutter;
	private boolean isTrap = false;

	public void setTrap(boolean isTrap) {
		this.isTrap = isTrap;
	}
	
	public boolean getTrap(){
		return this.isTrap;
	}

	public Barrel(){
        bounds = new Rectangle();            
        bounds.x = 0;
        bounds.y = 0;                         
        bounds.width = 52;
        bounds.height = 39;
        stateTime = 0f;
         
        loadAnimation();

        currentAnimation = barrelAnimationGutter;
        this.mouvement = new LinearMovementsRight();

	}
	
	private void loadAnimation()
	{
		TextureRegion[] tmp = imageManager.getBarrelTextureRegion();
		barrelAnimationGutter = new Animation(0.6f,tmp);
		
	}
}
