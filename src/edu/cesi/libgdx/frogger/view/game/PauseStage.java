package edu.cesi.libgdx.frogger.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.cesi.libgdx.frogger.utils.UIManager;
import edu.cesi.libgdx.frogger.view.menu.MenuScreen;

public class PauseStage extends Stage
{	
	private TextButton buttonRestart;	
	private TextButton buttonMenu;	
	private Label title;
	
	private UIManager uiManager;

	
	public PauseStage(final FroggerScreen game){
		this.loadUI(game);
	}
	
	private void loadUI(final FroggerScreen game)
	{
		this.uiManager = new UIManager();
		
		title = uiManager.createLabelTitle("Pause");
		title.setPosition(Gdx.graphics.getWidth()/2 - title.getWidth()/2, Gdx.graphics.getHeight() /1.2f);
		this.addActor(title);
		
		buttonRestart = uiManager.createButton("Restart");
		buttonRestart.setPosition(Gdx.graphics.getWidth()/2 - buttonRestart.getWidth()/2, Gdx.graphics.getHeight() /1.5f );
		buttonRestart.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.getWorld().resetLevel();
			}
		});
		this.addActor(buttonRestart);
		
		buttonMenu = uiManager.createButton("Menu");
		buttonMenu.setPosition(Gdx.graphics.getWidth()/2 - buttonRestart.getWidth()/2.2f, Gdx.graphics.getHeight() / 1.9f );
		buttonMenu.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
            	((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())
				.setScreen(new MenuScreen());
			}
		});
		this.addActor(buttonMenu);
	
	}
}