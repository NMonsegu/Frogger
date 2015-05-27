package edu.cesi.libgdx.frogger.view.menu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.SceneLoader;

import edu.cesi.libgdx.frogger.controler.MainGame;
import edu.cesi.libgdx.frogger.resources.RessourceManagerMenu;
import edu.cesi.libgdx.frogger.utils.UIManager;
import edu.cesi.libgdx.frogger.view.SettingsScreen;
import edu.cesi.libgdx.frogger.view.game.FroggerScreen;
import edu.cesi.libgdx.frogger.view.highScore.HighScoreScreen;


public class MenuStage extends Stage 
{
	//private final GameLauncher gameLauncher;
	
	public void dispose(){
		this.dispose();
	}
	
	private TextButton buttonPlay;
	private TextButton buttonSettings;
	private TextButton buttonHighScore;
	
	private UIManager uiManager;
	
	public MenuStage(RessourceManagerMenu rm) 
	{
        super(new StretchViewport(rm.currentResolution.width, rm.currentResolution.height));

        //Creatinga a scene loader and passing it a resource manager of game stage
        SceneLoader sl = new SceneLoader(rm);
        sl.setResolution(rm.currentResolution.name);

        //loading UI scene
        sl.loadScene("MainScene");
        
        addActor(sl.getRoot());
        
        this.loadUI();
    }
	
	private void loadUI()
	{
		this.uiManager = new UIManager();
		
		this.buttonPlay = this.uiManager.createButton("Play");
		this.buttonPlay.setPosition(Gdx.graphics.getWidth() /2 - this.buttonPlay.getWidth()/2, Gdx.graphics.getHeight() / 1.3f );
		this.buttonPlay.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
            	((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())
				.setScreen(new FroggerScreen(((MainGame)(com.badlogic.gdx.Game) Gdx.app.getApplicationListener())));
			}
		});
		
		this.addActor(this.buttonPlay);
		buttonPlay.getY();
		this.buttonSettings = this.uiManager.createButton("Settings ");
		this.buttonSettings.setPosition(Gdx.graphics.getWidth() /2 - this.buttonPlay.getWidth()/2, buttonPlay.getY() - buttonPlay.getHeight() );
		this.buttonSettings.addListener(new ChangeListener() 
		{
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
            	((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())
				.setScreen(new SettingsScreen());	
			}
		});
		this.addActor(this.buttonSettings);
		
		this.buttonHighScore = this.uiManager.createButton("High score");
		this.buttonHighScore.setPosition(Gdx.graphics.getWidth() /2 - this.buttonPlay.getWidth()/2, buttonSettings.getY() - buttonSettings.getHeight() );
		this.buttonHighScore.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
            	((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())
				.setScreen(new HighScoreScreen());
			}
		});
        
		this.addActor(this.buttonHighScore);
	}
	
}
