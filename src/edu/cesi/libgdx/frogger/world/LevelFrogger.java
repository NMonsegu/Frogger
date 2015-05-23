package edu.cesi.libgdx.frogger.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import edu.cesi.libgdx.frogger.model.AtHomeFrogger;
import edu.cesi.libgdx.frogger.model.Barrel;
import edu.cesi.libgdx.frogger.model.BigBush;
import edu.cesi.libgdx.frogger.model.FireBall;
import edu.cesi.libgdx.frogger.model.Flag;
import edu.cesi.libgdx.frogger.model.Fly;
import edu.cesi.libgdx.frogger.model.Life;
import edu.cesi.libgdx.frogger.model.MediumBush;
import edu.cesi.libgdx.frogger.model.Player;
import edu.cesi.libgdx.frogger.model.Shuriken;
import edu.cesi.libgdx.frogger.model.SmallBush;
import edu.cesi.libgdx.frogger.model.strategy.Entity;
import edu.cesi.libgdx.frogger.utils.Constants;
import edu.cesi.libgdx.frogger.utils.DifficultyManager;
import edu.cesi.libgdx.frogger.utils.ItemPositionManager;

public class LevelFrogger 
{
	/**
	 * This class manage the models behaviors. It create all then update them.
	 * 
	 * */
	
	private ArrayList<Entity> fireBallsList = new ArrayList<Entity>() ;
	private ArrayList<Entity> shurikensList = new ArrayList<Entity>();
	private ArrayList<Entity> flagsList = new ArrayList<Entity>();
	private ArrayList<Entity> barrelsList = new ArrayList<Entity>();
	private ArrayList<Entity> winPlayerList = new ArrayList<Entity>();
	private ArrayList<Entity> heartList = new ArrayList<Entity>();
	
	private ArrayList<Entity> bigBushList = new ArrayList<Entity>();
	private ArrayList<Entity> mediumBushList = new ArrayList<Entity>();
	private ArrayList<Entity> smallBushList = new ArrayList<Entity>();
	
	private Entity fly ;
	
	private ItemPositionManager ipm;
	
	private DifficultyManager difficutyManager;
	
	private int numberOfFireBalls;
	private int numberOfShurikens;
	private int numberOfBarrels;
	
	private int velocityItem;
	private int velocityPlayer;
	
	private Vector2[] fireballPosition;
	private Vector2[] shurikenPosition;
	private Vector2[] barrelPosition;
	private Vector2[] bigBushsPosition;
	private Vector2[] mediumBushsPosition;
	private Vector2[] smallBushsPosition;
	
	private Vector2[] heartsPosition;
	private Vector2[] flagsPosition;
	
	
	public LevelFrogger(DifficultyManager difficutyManager){
		this.difficutyManager = difficutyManager;
		ipm = new ItemPositionManager();
		
		fireballPosition = ipm.getPositionFireBalls();
		shurikenPosition = ipm.getPositionShurikens();
		barrelPosition   = ipm.getPositionBarrels();
		bigBushsPosition = ipm.getPositionBigBushs();
		mediumBushsPosition = ipm.getPositionMediumBushs();
		smallBushsPosition = ipm.getPositionSmallBushs();
		
		heartsPosition     = ipm.getPositionHeart();
		flagsPosition      = ipm.getPositionFlags();
		
		numberOfFireBalls = this.difficutyManager.getNumberOfFireBallToDraw();
		numberOfShurikens = this.difficutyManager.getNumberOfShurikenToDraw();
		numberOfBarrels   = this.difficutyManager.getNumberOfBarrelToDraw();
		velocityItem      = this.difficutyManager.getVelocityItem();
		velocityPlayer    = this.difficutyManager.getVelocityPlayer();
	}

	public void createElements()
	{
		this.createFlags();
		this.createFireBalls();
		this.createShurikens();
		
		this.createBarrels();
		this.createBigBush();
		this.createMediumBush();
		this.createSmallBush();

		this.createHeart();
		this.createFly();
	}
	
	public void resetLevel(){
		this.createHeart();
		this.winPlayerList = new ArrayList<Entity>();
	}
	
	/**
	 * Update the game elements
	 * 
	 * @param stateTime : current delta time
	 * @param batch : {@link FroggerWorldRenderer} SpriteBatch
	 * */
	public void updateElements(float stateTime, SpriteBatch batch)
	{
		this.updateFireBalls(stateTime,batch);
		this.updateShurikens(stateTime,batch);
		this.updateFlag(stateTime,batch);
		this.updateBarrels(stateTime,batch);
		this.updateWinPlayer(stateTime,batch);
		this.updateHeart(stateTime,batch);
		this.updateFly(stateTime,batch);
		
		this.updateBigBush( stateTime, batch);
		this.updateMediumBush(stateTime, batch);
		this.updateSmallBush( stateTime, batch);
	}
	
	public boolean isCollide(Player player)
	{
		if(player.getPosition().y < Constants.TIER_2_Y)
		{
			if(this.isCollideFistTier(player)) return true;
			
		}
		else if(player.getPosition().y > Constants.TIER_2_Y && player.getPosition().y < Constants.TIER_3_Y)
		{
			//if(this.isCollideSecondTier(player)) return true;
		}
			
		return false;
	}
	
	
	/**Check it the player is dead or has win
	 * @return boolean
	 * */
	public boolean isGameFinish(Player player){
		if(player.getLife() == 0 || winPlayerList.size() == Constants.NUMBER_OF_FLAG)
		{
			return true;
		}
		return false;
	}
	

	
	
	/*Create elements*/
	
	/* Elements creation */
	
	private int getRandomInt(int min, int max){
		java.util.Random rand = new java.util.Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	private long tmp =System.currentTimeMillis()/1000;
	private long timeRef = tmp;
	
	private void setRandomPositionFly(){
		int position = getRandomInt(0,Constants.NUMBER_OF_FLAG -1);
		fly.setPosition(flagsPosition[position].x, flagsPosition[position].y);
	}
	
	
	/*Create elements*/
	
	/*Static elements*/
	private void createFly(){
		fly = new Fly();
		this.setRandomPositionFly();
	}
	
	private void createHeart(){
		for (int i = 0; i < Constants.DEFAULT_LIFE; i++){
			Entity tmp = new Life();
			tmp.setPosition(heartsPosition[i].x,heartsPosition[i].y );
			heartList.add(tmp);
		}
	}
	
	private void createFlags(){
		for (int i = 0; i < Constants.NUMBER_OF_FLAG; i++){
			Entity tmp = new Flag();
			tmp.setPosition(flagsPosition[i].x,flagsPosition[i].y);
			flagsList.add(tmp);
		}
	}
	
	/*Dynamics elements*/
	private void createFireBalls()
	{
		
		for (int i = 0; i < numberOfFireBalls; i++){
			Entity tmp = new FireBall();
			System.out.println(i);
			//tmp.setPosition(Constants.INITIAL_POSITION_FIREBALL_X[i],Constants.INITIAL_POSITION_FIREBALL_Y[i]);
			tmp.setPosition(fireballPosition[i].x,fireballPosition[i].y);
			fireBallsList.add(tmp);
		}
	}
	
	private void createBarrels(){
		for (int i = 0; i < numberOfBarrels; i++){
			Entity tmp = new Barrel();
			tmp.setPosition(barrelPosition[i].x,barrelPosition[i].y);
			barrelsList.add(tmp);
		}
	}
	
	private void createShurikens(){
		for (int i = 0; i < numberOfShurikens; i++){
			Entity tmp = new Shuriken();
			tmp.setPosition(shurikenPosition[i].x,shurikenPosition[i].y);
			shurikensList.add(tmp);
		}
	}
	
	private void createBigBush(){
		for (int i = 0; i < Constants.NUMBER_OF_BIG_BUSH; i++){
			Entity tmp = new BigBush();
			tmp.setPosition(bigBushsPosition[i].x,bigBushsPosition[i].y);
			bigBushList.add(tmp);
		}
	}
	
	private void createMediumBush(){
		for (int i = 0; i < Constants.NUMBER_OF_MEDIUM_BUSH; i++){
			Entity tmp = new MediumBush();
			tmp.setPosition(mediumBushsPosition[i].x,mediumBushsPosition[i].y);
			mediumBushList.add(tmp);
		}
	}
	
	private void createSmallBush(){
		for (int i = 0; i < Constants.NUMBER_OF_SMALL_BUSH; i++){
			Entity tmp = new SmallBush();
			tmp.setPosition(smallBushsPosition[i].x,smallBushsPosition[i].y);
			smallBushList.add(tmp);
		}
	}
	


	private void createWinPlayer(float x, float y)
	{
		Entity winplayer = new AtHomeFrogger();
		winplayer.setPosition(x,y);
		winPlayerList.add(winplayer);
	}
	
	/* Update elements */
	
	private void updateFireBalls(float stateTime, SpriteBatch batch)
	{
		for (Entity fireBall : fireBallsList) 
		{
			fireBall.setStateTime(stateTime);
			fireBall.drawAnimation(batch);
			fireBall.move(velocityItem);
		}
	}
	
	private void updateShurikens(float stateTime, SpriteBatch batch)
	{
		for (Entity shuriken : shurikensList) 
		{
			shuriken.setStateTime(stateTime);
			shuriken.drawAnimation(batch);
			shuriken.move(velocityItem);
		}
	}
	
	private void updateFlag(float stateTime, SpriteBatch batch)
	{
		for (Entity flag : flagsList) 
		{
			flag.setStateTime(stateTime);
			flag.drawAnimation(batch);
			flag.move(velocityItem);
		}
	}
		
	private void updateBarrels(float stateTime, SpriteBatch batch)
	{
		for (Entity barrel : barrelsList) 
		{
			barrel.setStateTime(stateTime);
			barrel.drawAnimation(batch);
			barrel.move(velocityItem);
		}
	}
	
	private void updateBigBush(float stateTime, SpriteBatch batch)
	{
		for (Entity bush : bigBushList) 
		{
			bush.setStateTime(stateTime);
			bush.drawAnimation(batch);
			bush.move(velocityItem);
		}
	}
	
	private void updateMediumBush(float stateTime, SpriteBatch batch)
	{
		for (Entity bush : mediumBushList) 
		{
			bush.setStateTime(stateTime);
			bush.drawAnimation(batch);
			bush.move(velocityItem);
		}
	}
	
	private void updateSmallBush(float stateTime, SpriteBatch batch)
	{
		for (Entity bush : smallBushList) 
		{
			bush.setStateTime(stateTime);
			bush.drawAnimation(batch);
			bush.move(velocityItem);
		}
	}
	
	private void updateHeart(float stateTime, SpriteBatch batch){
		for (Entity heart : heartList) 
		{
			heart.setStateTime(stateTime);
			heart.drawAnimation(batch);
			heart.move(velocityItem);
		}
	}

	private void updateWinPlayer(float stateTime, SpriteBatch batch)
	{
		try{
			for (Entity winplayer : winPlayerList) 
			{
				winplayer.setStateTime(stateTime);
				winplayer.drawAnimation(batch);
				winplayer.move(0);
			}
		}catch (Exception e){
			System.out.println(e);
		}
	}
	
	//TODO CHANGE IT TO LIBGDX INTERNAL CURRENT TIME ( CROSS PLATEFORMS ) 
	private void updateFly(float stateTime, SpriteBatch batch){
		long time =  System.currentTimeMillis()/1000;
		
		fly.setStateTime(stateTime);
		fly.drawAnimation(batch);
		fly.move(0);
		
		if(time >= this.timeRef +3)
		{
			this.timeRef = time;
			setRandomPositionFly();
    		fly.setStateTime(stateTime);
    		fly.drawAnimation(batch);
    		fly.move(0);
		}
	}
	
	
	public void applyCollisionEffectToPlayer(Player player){
		player.setLife(player.getLife()-1);
		player.setPosition(50, 50);
		heartList.remove(0);
	}
	
	
	
	public boolean isCollideFistTier(Player player)
	{
		if(player.getPosition().y < Constants.TIER_2_Y)
		{
			player.setWidthBounds(60);
			player.setHeightBounds(70);
			for (Entity fireball : fireBallsList) 
			{
				if(player.getPosition().overlaps(fireball.getPosition())){
					//this.applyCollisionEffectToPlayer(player);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isCollideSecondTier(Player player)
	{
		/*if(player.getPosition().y > Constants.TIER_2_Y && player.getPosition().y < Constants.TIER_3_Y)
		{
			player.setWidthBounds(32);
			player.setHeightBounds(32);
			boolean isCollide = false;
			boolean isFloating = false;
					
			for (Entity bigBush : bigBushList) 
			{
				if(!isCollide)
				{
					
					if(player.getPosition().overlaps(bigBush.getPosition())){
						
						//if (player.getPosition().x - player.getHeight()/4 > bigBush.getPosition().x)
						//{
						//	System.out.println("ok");
						//}
						isCollide = true;
						
						if(player.getPosition().x + player.getHeight()/2 >= bigBush.getPosition().x &&  player.getPosition().y + player.getWidht()/2 >= bigBush.getPosition().y)
						{					
							
							if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT)&& !Gdx.input.isKeyPressed(Keys.RIGHT))
							{
								if(!isFloating)
								{
									player.isFloating = true;
	
									player.moveLeft(velocityItem);									
									return false;
								}
							}	
						} 
					}
				}
			}
			
			for (Entity mediumBush : mediumBushList) 
			{
				if(!isCollide)
				{
					if(player.getPosition().overlaps(mediumBush.getPosition())){
						isCollide = true;
						testCollisionIntersection(player,mediumBush);
						if(player.getPosition().x + player.getHeight()/2 >= mediumBush.getPosition().x &&  player.getPosition().y + player.getWidht()/2 >= mediumBush.getPosition().y)
						{
							if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT)&& !Gdx.input.isKeyPressed(Keys.RIGHT))
							{
								if(!isFloating){
									isFloating = true;
									//player.setPosition(player.getPosition().x,mediumBush.getPosition().y);
									player.moveLeft(velocityItem);
									return false;
								}
								
							}
						}
					}
				}
			}
			
			for (Entity smallBush : smallBushList) 
			{
				if(!isCollide)
				{
					if(player.getPosition().overlaps(smallBush.getPosition())){
						isCollide = true;
						if(player.getPosition().x + player.getHeight()/2 >= smallBush.getPosition().x &&  player.getPosition().y + player.getWidht()/2 >= smallBush.getPosition().y)
						{
							if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT)&& !Gdx.input.isKeyPressed(Keys.RIGHT))
							{
								if(!isFloating){
									isFloating = true;
									player.moveLeft(velocityItem);
									return false;
								}
	
							}				
						}
					}
				}
			}
			
			for (Entity barrel : barrelsList) 
			{
				if(player.getPosition().overlaps(barrel.getPosition())){
					
					if(player.getPosition().x + player.getHeight()/2 >= barrel.getPosition().x &&  player.getPosition().y + player.getWidht()/2 >= barrel.getPosition().y)
					{
						isCollide = true;
						if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT)&& !Gdx.input.isKeyPressed(Keys.RIGHT))
						{
							if(!isFloating){
								//player.setPosition(barrel.getPosition().x, barrel.getPosition().y);
								player.moveRight(velocityItem);
								isFloating = true;
								//return false;
							}
						}				
						if(barrel.getStateTime() % 3 >= 2.7){
							return true;
						}
					}
				}
			}
			
			if(!isCollide)
			{
				System.out.println("NO COLIDE");
				return true;
			}
		}
		return false; */
		return testCollisionAdvancedRectangle(player);
	}
	
	private boolean testCollisionAdvancedRectangle(Player player)
	{
		if(player.getPosition().y > Constants.TIER_2_Y && player.getPosition().y < Constants.TIER_3_Y)
		{

			boolean isCollide = false;
			boolean isFloating = false;

			for (Entity bigBush : bigBushList) 
			{
				if(!isCollide)
				{
					
					if(player.getAdvancedCollisionRectangle().overlaps(bigBush.getPosition())){
						
						isCollide = true;
						
						if(player.getAdvancedCollisionRectangle().x + player.getAdvancedCollisionRectangle().getHeight()/2 >= bigBush.getPosition().x &&  player.getAdvancedCollisionRectangle().y + player.getAdvancedCollisionRectangle().getWidth()/2 >= bigBush.getPosition().y)
						{					
							
							if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT)&& !Gdx.input.isKeyPressed(Keys.RIGHT))
							{
								if(!isFloating)
								{
									player.isFloating = true;
	
									player.moveLeft(velocityItem);									
									return false;
								}
							}	
						}
					}
				}
			}
			
			
			for (Entity smallBush : smallBushList) 
			{
				if(!isCollide)
				{
					if(player.getAdvancedCollisionRectangle().overlaps(smallBush.getPosition())){
						isCollide = true;
						if(player.getAdvancedCollisionRectangle().x + player.getAdvancedCollisionRectangle().getHeight()/2 >= smallBush.getPosition().x &&  player.getAdvancedCollisionRectangle().y + player.getAdvancedCollisionRectangle().getWidth()/2 >= smallBush.getPosition().y)
						{
							if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT)&& !Gdx.input.isKeyPressed(Keys.RIGHT))
							{
								if(!isFloating){
									isFloating = true;
									player.moveLeft(velocityItem);
									return false;
								}
	
							}				
						}
					}
				}
			}
			
			
			for (Entity mediumBush : mediumBushList) 
			{
				if(!isCollide)
				{
					
					if(player.getAdvancedCollisionRectangle().overlaps(mediumBush.getPosition()))
					{
						isCollide = true;
						if(player.getAdvancedCollisionRectangle().x + player.getAdvancedCollisionRectangle().height/2 >= mediumBush.getPosition().x &&  player.getAdvancedCollisionRectangle().y + player.getAdvancedCollisionRectangle().getWidth()/2 >= mediumBush.getPosition().y)
						{
							if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT)&& !Gdx.input.isKeyPressed(Keys.RIGHT))
							{
								if(!isFloating)
								{
									isFloating = true;
									//player.setPosition(player.getPosition().x,mediumBush.getPosition().y);
									player.moveLeft(velocityItem);
									return false;
								}
								
							}
						}
				}
			}
			
			
			for (Entity barrel : barrelsList) 
			{
				if(player.getAdvancedCollisionRectangle().overlaps(barrel.getPosition())){
					if(player.getAdvancedCollisionRectangle().x + player.getAdvancedCollisionRectangle().getHeight()/2 >= barrel.getPosition().x &&  player.getAdvancedCollisionRectangle().y + player.getAdvancedCollisionRectangle().getWidth()/2 >= barrel.getPosition().y)
					{
						isCollide = true;
						if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT)&& !Gdx.input.isKeyPressed(Keys.RIGHT))
						{
							if(!isFloating){
								//player.setPosition(barrel.getPosition().x, barrel.getPosition().y);
								player.moveRight(velocityItem);
								isFloating = true;
								//return false;
							}
						}				
						if(barrel.getStateTime() % 3 >= 2.7){
							return true;
						}
					}
					}
				}
			}
			
			if(!isCollide)
			{
				System.out.println("NO COLIDE");
				return true;
			}
		}
		return false;
	}
	
	private void testCollisionIntersection(Entity player, Entity obstacle){
		Rectangle intersection = new Rectangle();
		Intersector.intersectRectangles(player.getPosition(), obstacle.getPosition(), intersection); 
		
		if(intersection.x > player.getPosition().x)                                  
			System.out.println("Intersects with right side");
		if(intersection.y > player.getPosition().y)                                  
			System.out.println("Intersects with top side");
		if(intersection.x + intersection.width < player.getPosition().x + player.getPosition().width)  
			System.out.println("Intersects with left side ");
		if(intersection.y + intersection.height < player.getPosition().y + player.getPosition().height){
			System.out.println("Intersects with bottom side");
		}
	}
	
	public boolean isAtHome(Player player){
		
	 if(player.getPosition().y > Constants.TIER_3_Y)
		{
			for (Entity flag : flagsList) 
			{
				if(player.getPosition().overlaps(flag.getPosition())){
					float tmp =  player.getPosition().x;
					float tmp2 = player.getPosition().y;
					this.applyCollisionEffectToPlayer(player);
					this.createWinPlayer(tmp, tmp2);
					return true;
				}
			}
			return false;
		}
		return false;
	}
	
}
