package edu.cesi.libgdx.frogger.utils;

import edu.cesi.libgdx.frogger.utils.enums.Levels;

public class DifficultyManager 
{
	
	
	/**
	 * This class handle the number of item displayed depending upon the current difficulty
	 * */
	
	private Levels level;
	
	
	public DifficultyManager(){
		
	}
	
	/**
	 * Constructor
	 * @param level {@link Levels} instance
	 */
	public DifficultyManager(Levels level)
	{
		this.level = level;
	}
	
	public int getNumberOfFireBallToDraw(){
		 int value;
		
		 switch (level) 
		 {
         case EASY:  
        	 value = Constants.NUMBER_OF_FIREBALL_EASY;
             break;
         case MEDIUM: 
        	 value = Constants.NUMBER_OF_FIREBALL_NORMAL;
             break;
         case HARD:  
        	 value = Constants.NUMBER_OF_FIREBALL_HARD;
             break;
         default: 
        	 value = Constants.NUMBER_OF_FIREBALL_NORMAL;
             break;
		 }
		 return value;
	}
	
	public int getNumberOfBarrelToDraw(){
		 int value;
			
		 switch (level) 
		 {
       case EASY:  
      	 value = Constants.NUMBER_OF_BARREL_EASY;
           break;
       case MEDIUM: 
      	 value = Constants.NUMBER_OF_BARREL_NORMAL;
           break;
       case HARD:  
      	 value = Constants.NUMBER_OF_BARREL_HARD;
           break;
       default: 
      	 value = Constants.NUMBER_OF_BARREL_NORMAL;
           break;
		 }
		 return value;
	}
	
	public int getNumberOfShurikenToDraw(){
		 int value;
			
		 switch (level) 
		 {
        case EASY:  
       	 value = Constants.NUMBER_OF_SHURIKEN_EASY;
            break;
        case MEDIUM: 
       	 value = Constants.NUMBER_OF_SHURIKEN_NORMAL;
            break;
        case HARD:  
       	 value = Constants.NUMBER_OF_SHURIKEN_HARD;
            break;
        default: 
       	 value = Constants.NUMBER_OF_SHURIKEN_NORMAL;
            break;
		 }
		 return value;
	}
	
	public int getVelocityItem(){
		 int value;
			
		 switch (level) 
		 {
       case EASY:  
      	 value = Constants.VELOCITY_ITEM_EASY;
           break;
       case MEDIUM: 
      	 value = Constants.VELOCITY_ITEM_NORMAL;
           break;
       case HARD:  
      	 value = Constants.VELOCITY_ITEM_HARD;
           break;
       default: 
      	 value = Constants.VELOCITY_ITEM_NORMAL;
           break;
		 }
		 return value;
	}
	
	public int getVelocityPlayer(){
		return Constants.VELOCITY_PLAYER;
	}
}
