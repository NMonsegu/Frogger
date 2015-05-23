package edu.cesi.libgdx.frogger.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;

import edu.cesi.libgdx.frogger.model.strategy.Entity;
import edu.cesi.libgdx.frogger.model.strategy.LinearMovementsRight;

public class Barrel extends Entity
{

	private Animation barrelAnimation;
	

	public Barrel(){
		//stextureAtlas = new TextureAtlas(Gdx.files.internal(url));
        bounds = new Rectangle();            
        bounds.x = 0;
        bounds.y = 0;                         
        bounds.width = 52;
        bounds.height = 39;
        stateTime = 0f;
         
        loadAnimation();

        currentAnimation = barrelAnimation;
        //this.mouvement = new LinearMovementsLeft();
        this.mouvement = new LinearMovementsRight();

	}
	
	private void loadAnimation(){

		barrelAnimation = new Animation(0.6f,imageManager.getBarrelTextureRegion());
	}
}
