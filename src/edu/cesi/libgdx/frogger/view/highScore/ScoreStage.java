package edu.cesi.libgdx.frogger.view.highScore;

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

import edu.cesi.libgdx.frogger.utils.UIManager;
import edu.cesi.libgdx.frogger.view.menu.MenuScreen;

public class ScoreStage extends Stage 
{

	private Skin skin;
	private Label titre;
	private TextButton mainMenu;
	private UIManager uiManager;
	
	public ScoreStage(String[] highScore){
		this.loadUI();
		Array<String> listItems = new Array<String>(highScore);
        final List<String> listView = new List<String>(new Skin(Gdx.files.internal("defaultSkins/uiskin.json")));
        listView.setItems(listItems);
        listView.setPosition((Gdx.graphics.getWidth()/2-titre.getWidth()/2) + 80, Gdx.graphics.getHeight() /1.3f);
        this.addActor(listView);
	}
	
	private void loadUI()
	{
		this.uiManager = new UIManager();
		
		titre = uiManager.createLabelTitle("High Score");
		titre.setPosition(Gdx.graphics.getWidth()/2-titre.getWidth()/2, Gdx.graphics.getHeight() -100);
		this.addActor(titre);
		
		mainMenu = uiManager.createButton("Menu");
        mainMenu.setPosition(Gdx.graphics.getWidth()/2.05f  - mainMenu.getWidth()/2f, Gdx.graphics.getHeight() /3f );
		
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

