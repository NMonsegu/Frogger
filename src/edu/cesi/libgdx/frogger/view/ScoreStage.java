package edu.cesi.libgdx.frogger.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

import edu.cesi.libgdx.frogger.view.menu.MenuScreen;

public class ScoreStage extends Stage 
{

	private Skin skin;
	private Label titre;
	private TextButton mainMenu;
	
	public ScoreStage(String[] highScore){
		
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
		
		titre = new Label("High Score", skin, "newtitle"); 
		titre.setPosition(Gdx.graphics.getWidth()/2-titre.getWidth()/2, Gdx.graphics.getHeight() -100);
		this.addActor(titre);
		
		Array<String> listItems = new Array<String>(highScore);
		
        final List<String> listView = new List<String>(new Skin(Gdx.files.internal("defaultSkins/uiskin.json")));
        listView.setItems(listItems);
        
        listView.setPosition((Gdx.graphics.getWidth()/2-titre.getWidth()/2) + 80, Gdx.graphics.getHeight() /1.3f);
        this.addActor(listView);
        
        mainMenu = new TextButton("Menu", skin,"default");
        mainMenu.setPosition(Gdx.graphics.getWidth()/2.05f  - mainMenu.getWidth()/2f, Gdx.graphics.getHeight() /3f );
        mainMenu.pad(20);
        mainMenu.pack();
        mainMenu.setSize(120,50);
		
        mainMenu.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
		    	((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())
				.setScreen(new MenuScreen());
			}
		});
		this.addActor(mainMenu);
	}
}

