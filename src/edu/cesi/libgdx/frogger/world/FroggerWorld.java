package edu.cesi.libgdx.frogger.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import edu.cesi.libgdx.frogger.controler.MainGame;
import edu.cesi.libgdx.frogger.model.PartyTimer;
import edu.cesi.libgdx.frogger.model.Player;
import edu.cesi.libgdx.frogger.model.Score;
import edu.cesi.libgdx.frogger.utils.DifficultyManager;
import edu.cesi.libgdx.frogger.utils.enums.GameStates;
import edu.cesi.libgdx.frogger.utils.enums.Levels;
import edu.cesi.libgdx.frogger.view.game.EndLevelScreen;
import edu.cesi.libgdx.frogger.view.game.FroggerScreen;
import edu.cesi.libgdx.frogger.view.game.PauseStage;

public class FroggerWorld 
{
	/** This class containt basic world (player, score ...) 
	 * It control the {@link GameStates} and the non-graphic game logic.
	 * */

	private Player player;
	private Score score;
	private PartyTimer timer;
	private PauseStage pauseStage;
	private InputMultiplexer inputMultiplexer;
	private GameStates gamestate;
	private Levels difficulty;
	private LevelFrogger level;
	private DifficultyManager difficultyManager;
	
	private MainGame mainGame;
	private boolean soundState;
	private FroggerScreen froggerScreen;
	private int valueTimer = 60;
		
	public FroggerWorld(MainGame mainGame,FroggerScreen froggerScreen)
	{
		/*Dependency*/
		this.mainGame = mainGame;
		this.froggerScreen = froggerScreen;
		
		/*Get parameters*/
		this.difficulty = Levels.valueOf(this.mainGame.settingsManager.getCurrentStateLevel());
		this.soundState = this.mainGame.settingsManager.isCurrentStateSound();
				
		this.difficultyManager = new DifficultyManager(difficulty);
	    
		/*World items*/
        this.player = new Player();   
		this.score = new Score(player.getPosition().x,player.getPosition().y);
		this.timer = new PartyTimer();
		this.timer.launchTimer(valueTimer);
		
		this.gamestate  = GameStates.INGAME;

		this.level = new LevelFrogger(this.getDifficultyManager());
		
		/* Pause menu */
		this.pauseStage = new PauseStage(this.froggerScreen);
		inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(pauseStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        
        this.playMusic();
	}

	/**
	 * Update the world depending on the current game stage (Paused/InGame)
	 * */
	public boolean updateWorld()
	{
		this.updateGameState();
		
		if(gamestate == GameStates.INGAME)
		{
			this.updateInGame();
			return true;
		}
		else if(gamestate == GameStates.PAUSE)
		{
			updatePauseGame();
			return false;
		}
		return false;
	}
	
	/** Play the pause menu*/
	public void updatePauseGame()
	{
		pauseStage.act();
		pauseStage.draw();
	}
	
	/**Control the keys pressed to switch between game/pause */
	private void updateGameState()
	{
		if(gamestate == GameStates.PAUSE)
		{
			if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			{
				gamestate = GameStates.INGAME;
				this.timer.launchTimer(valueTimer - (valueTimer -tmpTimer)); 
				this.playMusic();
			}
		}
		else if (gamestate == GameStates.INGAME)
		{
			if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			{
				gamestate = GameStates.PAUSE;
				this.pauseMusic();
			}
		}
	}
	
	private long tmpTimer;
	private boolean playTimer = false;
	
	/**Update the non graphic game logic*/
	private void updateInGame()
	{
		//Set collision rectangle to player
		player.updateAdvancedCollisionRectangle();
		
		//Play sound if player is moving and sound enable
        if(player.isMouving)
        {
    		if(soundState)
    		{
    			mainGame.audioManager.playMusic(mainGame.audioManager.getMove());
    		}
        }else
        {
        	mainGame.audioManager.stopMusic(mainGame.audioManager.getMove());
        }
		//Update player
		player.update();
		
		//Update timer
		this.timer.updateTimers();
		tmpTimer = this.timer.getTimer();
		
		if(tmpTimer <= 3){
			if(!playTimer){
				this.playTimerEnd();
				this.playTimer = true;
			}
		}
		int tier = level.getPlayerTier(player);
		
		//Collision management
		if(tier == 0 || tier == 2)
		{
			player.move(difficultyManager.getVelocityPlayer());
	        boolean is = level.isCollideFistTier(player);
	        if(is)
	        {
	        	this.playHit();
	        	level.applyCollisionEffect(player);
	        }
		}else if(tier ==1)
		{
			player.move(difficultyManager.getVelocityPlayer());
			
	        boolean isColideSecondTier = level.isCollideSecondTier(player);
	        if(isColideSecondTier)
	        {
	        	this.playHit();
	        	level.applyCollisionEffect(player);
	        }
		}
		
		//Increase score
        score.increaseScore(player.getPosition().x, player.getPosition().y);
        
        //Check if player is at home
        if(level.isAtHome(player))
        {
        	score.setScore(score.getScore() + 50);//Bonus
        	score.setLastMaxPosition(new Vector2(50,50));
        }
        
        //Check if timer == 0
		if(tmpTimer <= 0)
		{
			this.gamestate = GameStates.GAMEOVER;
			this.playTimeout();
		 	((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())
			.setScreen(new EndLevelScreen(gamestate,score));
        	return;
		} 
        //Check if player is dead
        if(player.die())
        {	
        	level.resetLevel();
        	this.gamestate = GameStates.GAMEOVER;
        	this.playGameOver();
        	this.stopMusics();
        	((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())
			.setScreen(new EndLevelScreen(gamestate,score));
        	return;
        }
        //Check if player has win
        if(level.isGameFinish(player))
        {
        	this.gamestate = GameStates.WIN;
        	this.playWin();
        	this.stopMusics();
        	((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())
			.setScreen(new EndLevelScreen(gamestate,score));
        	return;
        }
	}
	
	private void stopMusics()
	{
		mainGame.audioManager.stopMusic(mainGame.audioManager.getAmbianceMusic());
		mainGame.audioManager.stopMusic(mainGame.audioManager.getMove());
	}

	/**
	 * play ambiance music if sound is enable
	 * */
	private void playMusic()
	{
		if(soundState)
		{
			mainGame.audioManager.playMusic(mainGame.audioManager.getAmbianceMusic());
		}
	}
	
	private void pauseMusic()
	{
		if(soundState)
		{
			mainGame.audioManager.pauseMusic(mainGame.audioManager.getAmbianceMusic());
		}
	}
	
	/**
	 * Play hit song if sound is enable
	 * */
	private void playHit()
	{
		if(soundState)
		{
			mainGame.audioManager.playSound(mainGame.audioManager.getHit());
		}
	}
	
	/**
	 * Play gameover song if sound is enable
	 * */
	private void playGameOver(){
		if(soundState)
		{
			mainGame.audioManager.playSound(mainGame.audioManager.getGameOver());
		}
	}
	
	/**
	 * Play timer song if sound is enable
	 * */
	private void playTimeout(){
		if(soundState)
		{
			mainGame.audioManager.playSound(mainGame.audioManager.getTimeout());
		}
	}
	
	/**
	 * Play end timer song if sound is enable
	 * */
	private void playTimerEnd(){
		if(soundState)
		{
			mainGame.audioManager.playSound(mainGame.audioManager.getTimerEnd());
		}
	}
	
	/**
	 * Play win song if sound is enable
	 * */
	private void playWin(){
		if(soundState)
		{
			mainGame.audioManager.playSound(mainGame.audioManager.getWin());
		}
	}
	
	/**
	 * Reset the level (Score, timer, position)
	 * */
	public void resetLevel()
	{
    	score.resetScore();	
    	level.resetLevel();
    	timer.resetTimer();
    	player.setPosition(50, 50);
    	this.gamestate = GameStates.INGAME;
	}

	public void dispose(){
		pauseStage.dispose();
		System.out.println("froggerWorld dispose");
	}
	
	//******************************************** Get - Set *********************************************//
	
	public Player getPlayer() {
		return player;
	}
	
	public GameStates getGameState(){
		return this.gamestate;
	}
	
	public int getScore()
	{
		return this.score.getScore();
	}
	
	public long getTimer()
	{
		return this.timer.getTimer();
	}

	public FroggerScreen getFroggerScreen()
	{
		return froggerScreen;
	}
	
	public PauseStage getPauseStage() 
	{
		return pauseStage;
	}

	public LevelFrogger getLevel() 
	{
		return level;
	}

	public DifficultyManager getDifficultyManager() 
	{
		return difficultyManager;
	}
}
