package edu.cesi.libgdx.frogger.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.cesi.libgdx.frogger.view.menu.MenuScreen;

public class PauseStage extends Stage
{	
	private Skin skin;		
	private TextButton buttonRestart;	
	private TextButton buttonMenu;	
	private Label title;
	
	public PauseStage(final FroggerScreen game){
		
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
		
        System.out.println(Gdx.graphics.getWidth());
        System.out.println(Gdx.graphics.getHeight());
		
		skin = new Skin();
		skin.addRegions(new TextureAtlas(Gdx.files.internal("skins/custom.atlas")));
		skin.add("bigTitle", grosTitres);
		skin.add("label", label);
		skin.load(Gdx.files.internal("skins/customskin.json"));
		
		System.out.println(Gdx.graphics.getWidth() + " width");
		System.out.println(Gdx.graphics.getHeight()+ " height");
		
		title = new Label("Pause", skin, "newtitle");  
		title.setPosition(Gdx.graphics.getWidth()/2 - title.getWidth()/2, Gdx.graphics.getHeight() /1.2f);
		this.addActor(title);
			
		buttonRestart = new TextButton("Restart", skin, "default");
		buttonRestart.setPosition(Gdx.graphics.getWidth()/2 - buttonRestart.getWidth()/2, Gdx.graphics.getHeight() /1.5f );
		buttonRestart.pad(20);
		buttonRestart.pack();
		buttonRestart.setSize(120,50);
		
		buttonRestart.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.getWorld().resetLevel();
			}
		});
		this.addActor(buttonRestart);
		
		buttonMenu = new TextButton("Menu", skin, "default");
		buttonMenu.setPosition(Gdx.graphics.getWidth()/2 - buttonRestart.getWidth()/2.2f, Gdx.graphics.getHeight() / 1.9f );
		buttonMenu.pad(20);
		buttonMenu.pack();
		buttonMenu.setSize(120,50);
		
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
