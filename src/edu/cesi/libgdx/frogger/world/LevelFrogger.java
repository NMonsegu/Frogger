package edu.cesi.libgdx.frogger.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	private ArrayList<Barrel> barrelsList = new ArrayList<Barrel>();
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
	//private int velocityItem2;
	
	//private int velocityPlayer;
	
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
		//velocityItem2 = Constants.VELOCITY_ITEM_EASY_2;
		
		//velocityPlayer    = this.difficutyManager.getVelocityPlayer();
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
	
	public void resetLevel()
	{
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
	
	public int getPlayerTier(Player player)
	{
		if(player.getPosition().y < Constants.TIER_2_Y)
		{
			return 0;
		}
		else if(player.getPosition().y > Constants.TIER_2_Y && player.getPosition().y < Constants.TIER_3_Y)
		{
			return 1;
		}else 
		{
			return 2;
		}
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
	
	private void setRandomPositionFly()
	{
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
	
	int trap1 = 0;
	int trap2 = 0;
	int trap3 = 0;
	int trap4 = 0;
	
	private void createTrappedBarrels()
	{
		trap1 = getRandomInt(0,numberOfBarrels);
		trap2 = getRandomInt(0,numberOfBarrels);
		trap3 = getRandomInt(0,numberOfBarrels);
		trap4 = getRandomInt(0,numberOfBarrels);
		
		int i = 0;
		for (Barrel barrel : barrelsList) 
		{
			barrel.setTrap(false);
			if(i == trap1 || i == trap2 || i == trap3|| i == trap4)
			{
				barrel.setTrap(true);
			}else
			{
				barrel.setTrap(false);
			}
			i ++ ;
		}
	}
	
	private void createBarrels(){	
		for (int i = 0; i < numberOfBarrels; i++)
		{
			Barrel tmp = new Barrel();
			tmp.setPosition(barrelPosition[i].x,barrelPosition[i].y);
			barrelsList.add(tmp);
		}
		createTrappedBarrels();
	}
	
	boolean test = true;
	private void updateBarrels(float stateTime, SpriteBatch batch)
	{
		for (Barrel barrel : barrelsList) 
		{	
			if(!barrel.getTrap())
			{
				barrel.setStateTime(0.3f);
			}else
			{
				barrel.setStateTime(stateTime);
			}
			barrel.drawAnimation(batch);
			barrel.move(velocityItem);
		}
		
		if(mustResetBarrelTrap())
		{
			if(!test)
			{
				createTrappedBarrels();
				test = true;
				System.out.println("must");
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}else
		{
			test = false;
		}
	}
	
	private boolean isBarrelFullInWatter(Barrel barrel)
	{	
		if(barrel.getCurrentAnimation().getKeyFrameIndex(barrel.getStateTime()%3)== 4){
			System.out.println("barrel");
			System.out.println(barrel.getTrap());
			return true;
		}
		return false;
	}
	
	private boolean mustResetBarrelTrap(){
		//2.9942987f 
		for (Entity barrel : barrelsList)
		{
			if(barrel.getStateTime()%3 >= 2.934f )
			{
				return true;
			}
		}
		return false;
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
	
	
	
	/*Collisions*/
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
			
			if(this.simpleCollision(player, fireBallsList)) return true;
			if(this.simpleCollision(player, shurikensList))return true;
		}
		return false;
	}
	
	private boolean simpleCollision(Player player,ArrayList<Entity>  entityList ){
		for (Entity entity : entityList) 
		{
			if(player.getPosition().overlaps(entity.getPosition()))return true;
		}
		return false;
	}
	
	public boolean isCollideSecondTier(Player player)
	{
		return testCollisionAdvancedRectangle(player);
	}
	
	private boolean testCollisionAdvancedRectangle(Player player)
	{
		if(player.getPosition().y > Constants.TIER_2_Y && player.getPosition().y < Constants.TIER_3_Y)
		{

			boolean isCollide = false;
			player.isFloating = false;

			for (Entity bigBush : bigBushList) 
			{
				if(!isCollide)
				{
					if(player.getAdvancedCollisionRectangle().overlaps(bigBush.getPosition()))
					{
						isCollide = true;
						if(player.getAdvancedCollisionRectangle().x + player.getAdvancedCollisionRectangle().getHeight()/2 >= bigBush.getPosition().x &&  player.getAdvancedCollisionRectangle().y + player.getAdvancedCollisionRectangle().getWidth()/2 >= bigBush.getPosition().y)
						{					
							if(!player.moveLeft)
							{
								player.moveLeft(velocityItem);

							}else
							{
								player.moveLeft(velocityItem-(Constants.VELOCITY_PLAYER - velocityItem));
							}
							
							if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT)&& !Gdx.input.isKeyPressed(Keys.RIGHT))
							{
								if(!player.isFloating)
								{
									player.isFloating = true;
									if(!player.isMouving){
										//player.moveLeft(velocityItem);
									}							
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
							if(!player.moveLeft)
							{
								player.moveLeft(velocityItem);

							}else
							{
								player.moveLeft(velocityItem-(Constants.VELOCITY_PLAYER - velocityItem));
							}
							
							if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT)&& !Gdx.input.isKeyPressed(Keys.RIGHT))
							{
								if(!player.isFloating){
									player.isFloating = true;
									
									if(!player.isMouving){
										//player.moveLeft(velocityItem);
									}
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
							if(!player.moveLeft)
							{
								player.moveLeft(velocityItem);

							}else
							{
								player.moveLeft(velocityItem-(Constants.VELOCITY_PLAYER - velocityItem));
							}
							
							if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT)&& !Gdx.input.isKeyPressed(Keys.RIGHT))
							{
								if(!player.isFloating)
								{
									player.isFloating = true;
									if(!player.isMouving){
										//player.moveLeft(velocityItem);
									}
									return false;
								}
							}
						}
					}
				}
			}
			
			for (Barrel barrel : barrelsList) 
			{
				if(player.getAdvancedCollisionRectangle().overlaps(barrel.getPosition()))
				{
					if(player.getAdvancedCollisionRectangle().x + player.getAdvancedCollisionRectangle().getHeight()/2 >= barrel.getPosition().x &&  player.getAdvancedCollisionRectangle().y + player.getAdvancedCollisionRectangle().getWidth()/2 >= barrel.getPosition().y)
					{
						if(!player.moveRight)
						{
							player.moveRight(velocityItem);

						}else
						{
							player.moveRight(velocityItem - (Constants.VELOCITY_PLAYER - velocityItem));
							//player.moveLeft(velocityItem-(Constants.VELOCITY_PLAYER - velocityItem));
						}
						
						isCollide = true;
						if(!Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.LEFT)&& !Gdx.input.isKeyPressed(Keys.RIGHT))
						{
							if(!player.isFloating){
								player.isFloating = true;
							}
						}				
						if(isBarrelFullInWatter(barrel) && barrel.getTrap()){
							return true;
						}
					}
				}
			}	
			if(!isCollide)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isAtHome(Player player){
		
	 if(player.getPosition().y > Constants.TIER_3_Y)
		{
			for (Entity flag : flagsList) 
			{
				for (Entity winPlayer: winPlayerList)
				{
					if(player.getPosition().overlaps(winPlayer.getPosition())){
						System.out.println("COLLIDE FLY");
						this.applyCollisionEffectToPlayer(player);
						return false;
					}
				}
				
				if(player.getPosition().overlaps(flag.getPosition()) && !player.getPosition().overlaps(fly.getPosition()))
				{
					float tmp =  player.getPosition().x;
					float tmp2 = player.getPosition().y;
					this.applyCollisionEffectToPlayer(player);
					this.createWinPlayer(tmp, tmp2);
					return true;
				}else if (player.getPosition().overlaps(flag.getPosition()) && player.getPosition().overlaps(fly.getPosition()))
				{
					System.out.println("COLLIDE FLY");
					this.applyCollisionEffectToPlayer(player);
					return false;
				}
			}
			return false;
		}
		return false;
	}
	
}
