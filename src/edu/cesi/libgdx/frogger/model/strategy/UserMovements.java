package edu.cesi.libgdx.frogger.model.strategy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;

public class UserMovements implements MovementBehavior
{	
	/**
	 * This MovementBehavior implementation manage the Users movement logic.
	 * */
	
   @Override
   public void move(Rectangle bounds, int velocity) 
   {
	     if(Gdx.input.isKeyPressed(Keys.LEFT)) moveLeft(bounds, velocity);
	     if(Gdx.input.isKeyPressed(Keys.RIGHT)) moveRight(bounds, velocity);
	     if(Gdx.input.isKeyPressed(Keys.UP)) moveTop(bounds, velocity);
	     if(Gdx.input.isKeyPressed(Keys.DOWN)) moveBot(bounds, velocity);
	     controlOutMap(bounds);
    }
	     
	 public void moveRight(Rectangle bounds, int velocity)
	 {  
		//System.out.println("moveRight" + velocity);
	     bounds.x += velocity ;
	 }
	  
	 public void moveLeft(Rectangle bounds, int velocity){
		 //System.out.println("moveLeft" + velocity);
	     bounds.x -= velocity ;
	    }
	 
	 public void moveTop(Rectangle bounds, int velocity){
		 bounds.y += velocity ;
	 }
	 
	 public void moveBot(Rectangle bounds, int velocity){
		 bounds.y -= velocity ;
	 }
	  
	 /**
	  * This  class check if the given rectangle is out of map or not and limit the player movement if out.
	  * @param bounds : rectangle to check
	  * */
	 public void controlOutMap(Rectangle bounds){ 
	      
	      if(bounds.x < 0) bounds.x = 0;
	     if(bounds.x > 1200 - 64) bounds.x = 1200 - 64;
	     
	      if(bounds.y < 0) bounds.y= 0;
	     if(bounds.y > 800 - 64) bounds.y = 800 - 64;
	 }


}