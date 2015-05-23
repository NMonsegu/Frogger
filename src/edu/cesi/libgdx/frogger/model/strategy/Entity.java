package edu.cesi.libgdx.frogger.model.strategy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import edu.cesi.libgdx.frogger.data.ImagesManager;

public abstract class Entity {
    
	
	 	protected Animation currentAnimation;

		protected Rectangle bounds;
	    protected float stateTime;
	    protected ImagesManager imageManager = ImagesManager.getInstance();
	 
	    public Rectangle getBounds() {
			return bounds;
		}

		public void setBounds(Rectangle bounds) {
			this.bounds = bounds;
		}
		
		public void setWidthBounds(int bound){
			this.bounds.width = bound;
		}
		
		public void setHeightBounds(int bound){
			this.bounds.height = bound;
		}

		protected MovementBehavior mouvement = new LinearMovementsLeft();
		
	    RenderBehavior render = new BasicRender();
	    
	    public float getStateTime() {
			return stateTime;
		}

		public void setStateTime(float stateTime) {
			this.stateTime = stateTime;
		}

		public void move(int velocity){
	        mouvement.move(bounds, velocity);//anticipation de l'étape prochaine
	    }
	    
	    public void drawAnimation(SpriteBatch batch){
	    	render.draw(batch,bounds,currentAnimation, stateTime);
	    }
	     
	    public void update(){
	    	
	    }
	    
	    public float getWidht(){
	    	return bounds.width;
	    }
	    
	    public float getHeight(){
	    	return bounds.height;
	    }
	 	     
	    public Rectangle getPosition(){
	    	return this.bounds;
	    }
	    
	    public void setPosition(float x, float y){
	    	this.bounds.x = x;
	    	this.bounds.y = y;
	    }
	    
	    
	    public void setMovement(MovementBehavior newMovement){this.mouvement = newMovement;}
	  
	    public void setRender(RenderBehavior newRender){this.render = newRender;}
	   
	    public Animation getCurrentAnimation() {return currentAnimation;}

		public void setCurrentAnimation(Animation currentAnimation) {this.currentAnimation = currentAnimation;}
}