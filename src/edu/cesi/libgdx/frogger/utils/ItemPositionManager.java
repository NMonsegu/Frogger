package edu.cesi.libgdx.frogger.utils;

import com.badlogic.gdx.math.Vector2;

public class ItemPositionManager 
{
	public ItemPositionManager()
	{
		loadData();
	}

	private Vector2[] positionFireballs;
	private Vector2[] positionShurikens;
	private Vector2[] positionBarrels;
	private Vector2[] positionBigBushs;
	private Vector2[] positionMediumBushs;
	private Vector2[] positionSmallBushs;
	private Vector2[] positionFlags;
	private Vector2[] positionHearts;

	
	private void loadData()
	{
		loadPositionFireBalls_1200x800();
		loadPositionBarrels_1200x800();
		loadPositionShurikens_1200x800();
		loadPositionFlags_1200x800();
		loadPositionHearts_1200x800();
		loadPositionBigBushs_1200x800();
		loadPositionMediumBushs_1200x800();
		loadPositionSmallBushs_1200x800();
	}
	
	private void loadPositionFireBalls_1200x800()
	{
		try{
			positionFireballs = new Vector2[Constants.NUMBER_OF_FIREBALL_HARD];
			
			positionFireballs[0] = new Vector2(1000  , 135);   //y = 140   -Collone 1
			positionFireballs[1] = new Vector2(1200  , 350);   //y = 350   -Collone 5 
			positionFireballs[2] = new Vector2(200   , 240);   //y = 240   -Collone 3
			
			positionFireballs[3] = new Vector2(400   , 135);
			positionFireballs[4] = new Vector2(600   , 350);
			positionFireballs[5] = new Vector2(800   , 240);  
			
			positionFireballs[6] = new Vector2(1050  , 135);
			positionFireballs[7] = new Vector2(1100  , 350);
			positionFireballs[8] = new Vector2(1250  , 240);
			
			positionFireballs[9] = new Vector2(500  , 135);
			positionFireballs[10] = new Vector2(700  , 350);
			positionFireballs[11] = new Vector2(900  , 240);
		}catch(NullPointerException ex){
			System.err.println(ex + "Error loading fireballs position");
		}catch(Exception ex){
			System.err.println(ex + "Error loading fireballs position");
		}

	}
	
	private void loadPositionShurikens_1200x800()
	{
		try
		{
			positionShurikens = new Vector2[Constants.NUMBER_OF_FIREBALL_HARD];
			
			positionShurikens[0] = new Vector2(1000 , 180);  //y = 180   -Collone 2
			positionShurikens[1] = new Vector2(1200 , 180);
			positionShurikens[2] = new Vector2(200 , 260);   //y = 260   -Collone 4
			
			positionShurikens[3] = new Vector2(400 , 180);
			positionShurikens[4] = new Vector2(600 , 260);
			positionShurikens[5] = new Vector2(800 , 260);  
			
			positionShurikens[6] = new Vector2(1050 , 180);
			positionShurikens[7] = new Vector2(1100 , 180);
			positionShurikens[8] = new Vector2(1250 , 260);
			
			positionShurikens[9] = new Vector2(1100 , 180);
			positionShurikens[10] = new Vector2(950 , 180);
			positionShurikens[11] = new Vector2(730 , 260);
		}catch(NullPointerException ex)
		{
			System.err.println(ex + "Error loading shurikens position");
		}catch(Exception ex)
		{
			System.err.println(ex + "Error loading shurikens position");
		}
	}
	
	
	
	private void loadPositionBarrels_1200x800()
	{
		try
		{
			positionBarrels = new Vector2[Constants.NUMBER_OF_BARREL_HARD];
			
			positionBarrels[0] = new Vector2(1000 , 540); //y = 420  Collonne 1 
			positionBarrels[1] = new Vector2(1200 , 540);
			
			positionBarrels[2] = new Vector2(200 , 540);  //y = 570  Collonne 2
			positionBarrels[3] = new Vector2(400 , 460);
			
			positionBarrels[4] = new Vector2(600 , 460);
			positionBarrels[5] = new Vector2(800 , 460);    
			
			positionBarrels[6] = new Vector2(1050 , 460);
			positionBarrels[7] = new Vector2(1100 , 460);
			
			positionBarrels[8] = new Vector2(1250 , 460);
			
			
			positionBarrels[9] = new Vector2(30 , 540);
			positionBarrels[10] = new Vector2(700 , 460);
			
			positionBarrels[11] = new Vector2(1250 , 460);
			
			positionBarrels[12] = new Vector2(500 , 540);
		}catch(NullPointerException ex)
		{
			System.err.println(ex + "Error loading barrels position");
		}catch(Exception ex)
		{
			System.err.println(ex + "Error loading barrels position");
		}

	}
	
	private void loadPositionBigBushs_1200x800()
	{
		try
		{
			positionBigBushs = new Vector2[Constants.NUMBER_OF_FIREBALL_HARD];
			
			positionBigBushs[0] = new Vector2(100 , 580); // y = 630  last collonne
			positionBigBushs[1] = new Vector2(600 , 580);
			positionBigBushs[2] = new Vector2(900 , 580);
			
			positionBigBushs[3] = new Vector2(490 , 580);
			positionBigBushs[4] = new Vector2(530 , 530);
			positionBigBushs[5] = new Vector2(570 , 575);  
		}catch(NullPointerException ex)
		{
			System.err.println(ex + "Error loading big buch position");
		}catch(Exception ex)
		{
			System.err.println(ex + "Error loading big buch position");
		}
	}
	
	private void loadPositionMediumBushs_1200x800()
	{
		try
		{
			positionMediumBushs = new Vector2[Constants.NUMBER_OF_FIREBALL_HARD];
			
			positionMediumBushs[0] = new Vector2(200  , 420);
			positionMediumBushs[1] = new Vector2(500 , 420);
			positionMediumBushs[2] = new Vector2(900 , 420);
			positionMediumBushs[3] = new Vector2(330 , 420);
			positionMediumBushs[4] = new Vector2(530 , 420);
			positionMediumBushs[5] = new Vector2(282f , 420); 
		}catch(NullPointerException ex)
		{
			System.err.println(ex + "Error loading medium buch position");
		}catch(Exception ex)
		{
			System.err.println(ex + "Error loading medium buch position");
		}
	}
	
	private void loadPositionSmallBushs_1200x800()
	{
		try
		{
			positionSmallBushs = new Vector2[Constants.NUMBER_OF_FIREBALL_HARD];
			
			positionSmallBushs[0] = new Vector2(100  , 500);
			positionSmallBushs[1] = new Vector2(250 , 500);
			positionSmallBushs[2] = new Vector2(400 , 500);
			positionSmallBushs[3] = new Vector2(550 , 500);
			positionSmallBushs[4] = new Vector2(700 , 500);
			positionSmallBushs[5] = new Vector2(850 , 500); 
			
		}catch(NullPointerException ex)
		{
			System.err.println(ex + "Error loading small buch position");
		}catch(Exception ex)
		{
			System.err.println(ex + "Error loading small buch position");
		}
    
	}
	
	private void loadPositionFlags_1200x800()
	{
		try
		{
			positionFlags = new Vector2[Constants.NUMBER_OF_FIREBALL_HARD];
			
			positionFlags[0] = new Vector2(100  , 625  );
			positionFlags[1] = new Vector2(400 , 625  );
			positionFlags[2] = new Vector2(700 , 625);
			positionFlags[3] = new Vector2(1000 , 625);
			positionFlags[4] = new Vector2(1500 , 625); 
		}catch(NullPointerException ex)
		{
			System.err.println(ex + "Error loading flags position");
		}catch(Exception ex)
		{
			System.err.println(ex + "Error loading flags position");
		}
	}
	
	private void loadPositionHearts_1200x800()
	{
		try
		{
			positionHearts = new Vector2[Constants.NUMBER_OF_FIREBALL_HARD];
			
			positionHearts[0] = new Vector2(1000 , 770);
			positionHearts[1] = new Vector2(1030 , 770);
			positionHearts[2] = new Vector2(1060 , 770);
			positionHearts[3] = new Vector2(1090 , 770);
		}catch(NullPointerException ex)
		{
			System.err.println(ex + "Error loading hearts position");
		}catch(Exception ex)
		{
			System.err.println(ex + "Error loading hearts position");
		}
	}
	
	public Vector2[] getPositionFireBalls(){
		return this.positionFireballs;
	}
	
	public Vector2[] getPositionShurikens(){
		return this.positionShurikens;
	}
	
	public Vector2[] getPositionBarrels(){
		return this.positionBarrels;
	}
	
	public Vector2[] getPositionBigBushs(){
		return this.positionBigBushs;
	}
	
	public Vector2[] getPositionMediumBushs(){
		return this.positionMediumBushs;
	}
	
	public Vector2[] getPositionSmallBushs(){
		return this.positionSmallBushs;
	}
	
	public Vector2[] getPositionHeart(){
		return this.positionHearts;
	}
	
	public Vector2[] getPositionFlags(){
			return this.positionFlags;
	}
	
	
}
