package edu.cesi.libgdx.frogger.view.menu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.SceneLoader;

import edu.cesi.libgdx.frogger.controler.MainGame;
import edu.cesi.libgdx.frogger.data.RessourceManagerMenu;
import edu.cesi.libgdx.frogger.view.HighScoreScreen;
import edu.cesi.libgdx.frogger.view.SettingsScreen;
import edu.cesi.libgdx.frogger.view.game.FroggerScreen;


public class MenuStage extends Stage 
{
	//private final GameLauncher gameLauncher;
	
	public void dispose(){
		this.dispose();
	}
	
	Skin skin;
	
	TextButton buttonPlay;
	TextButton buttonSettings;
	TextButton buttonHighScore;
	
	public MenuStage(RessourceManagerMenu rm) 
	{
        super(new StretchViewport(rm.currentResolution.width, rm.currentResolution.height));

        //Creatinga a scene loader and passing it a resource manager of game stage
        SceneLoader sl = new SceneLoader(rm);
        sl.setResolution(rm.currentResolution.name);

        // loading UI scene

        sl.loadScene("MainScene");

        //initSceneLoader(rm);
        
        addActor(sl.getRoot());
        
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/immortal.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 40;
		BitmapFont grosTitres = generator.generateFont(parameter);
		generator.dispose();
		
		FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Remachine.ttf"));
		FreeTypeFontParameter parameter2 = new FreeTypeFontParameter();
		parameter2.size = 40;
		BitmapFont label = generator2.generateFont(parameter2);
		generator2.dispose();
		
		skin = new Skin();
		skin.addRegions(new TextureAtlas(Gdx.files.internal("skins/custom.atlas")));
		skin.add("bigTitle", grosTitres);
		skin.add("label", label);
		skin.load(Gdx.files.internal("skins/customskin.json"));

		buttonPlay = new TextButton("Play", skin,"default");
		buttonPlay.setSize(120,50);
		buttonPlay.setPosition(Gdx.graphics.getWidth() /2 - buttonPlay.getWidth()/2, Gdx.graphics.getHeight() / 1.3f );
		buttonPlay.pad(20);
		buttonPlay.pack();//sinon padding non pris en compte !!
		buttonPlay.setSize(120,50);
		
		buttonPlay.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
            	((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())
				.setScreen(new FroggerScreen(((MainGame)(com.badlogic.gdx.Game) Gdx.app.getApplicationListener())));
			}
		});
		this.addActor(buttonPlay);
		
		
		buttonSettings = new TextButton("High score", skin,"default");
		buttonSettings.setPosition(Gdx.graphics.getWidth() /2 - buttonPlay.getWidth()/2, Gdx.graphics.getHeight() /1.48f );
		buttonSettings.pad(20);
		buttonSettings.pack();//sinon padding non pris en compte !!
		buttonSettings.setSize(120,50);
		
		buttonSettings.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
            	((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())
				.setScreen(new HighScoreScreen());
			}
		});
		this.addActor(buttonSettings);;
		
		
		buttonHighScore = new TextButton("Settings", skin,"default");
		buttonHighScore.setPosition(Gdx.graphics.getWidth() /2 - buttonPlay.getWidth()/2, Gdx.graphics.getHeight() /1.7f );
		buttonHighScore.pad(20);
		buttonHighScore.pack();//sinon padding non pris en compte !!
		buttonHighScore.setSize(120,50);
		
		buttonHighScore.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
            	((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())
				.setScreen(new SettingsScreen());				
			}
		});
        
		this.addActor(buttonHighScore);;
        
    }
}
