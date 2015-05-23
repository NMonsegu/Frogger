package edu.cesi.libgdx.frogger.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;

import edu.cesi.libgdx.frogger.model.strategy.Entity;
import edu.cesi.libgdx.frogger.model.strategy.Motionless;

public class Life extends Entity
{
	private Animation heartAnimation;
	
	public Life(){
		//textureAtlas = new TextureAtlas(Gdx.files.internal(url));
        bounds = new Rectangle();            
        bounds.x = 0;
        bounds.y = 0;                         
        bounds.width = 21;
        bounds.height = 21;
        stateTime = 0f;
         
        loadAnimation();

        currentAnimation = heartAnimation;
        this.mouvement = new Motionless();
	}
	
	
	private void loadAnimation(){
		heartAnimation = new Animation(0.1f,imageManager.getHeartTextureRegion());
	}
	
}
