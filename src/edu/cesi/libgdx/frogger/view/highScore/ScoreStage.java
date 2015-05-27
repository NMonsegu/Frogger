package edu.cesi.libgdx.frogger.view.highScore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

import edu.cesi.libgdx.frogger.utils.UIManager;
import edu.cesi.libgdx.frogger.view.menu.MenuScreen;

public class ScoreStage extends Stage 
{
	private List<String> listView;

	public List<String> getListView(){
		return this.listView;
	}
	private Label title;
	public Label getTitle() {
		return title;
	}

	public TextButton getMainMenu() {
		return mainMenu;
	}

	private TextButton mainMenu;
	private UIManager uiManager;
	
	public ScoreStage(String[] highScore){
		this.loadUI(highScore);
	}
	
	private void loadUI(String[] highScore)
	{
		this.uiManager = new UIManager();
		
		this.title = uiManager.createLabelTitle("High Score");
		title.setPosition(Gdx.graphics.getWidth()/2-title.getWidth()/2, Gdx.graphics.getHeight()- title.getHeight());
		this.addActor(title);
		
		Array<String> listItems = new Array<String>(highScore);
		this.listView = uiManager.createList(listItems);
        this. listView.setPosition((Gdx.graphics.getWidth()/2-title.getWidth()/2) + 80, Gdx.graphics.getHeight() /1.3f);
        this.addActor(this.listView);
		
		mainMenu = uiManager.createButton("Menu");
        mainMenu.setPosition(Gdx.graphics.getWidth()/2.05f  - mainMenu.getWidth()/2f, Gdx.graphics.getHeight() /3f - this.mainMenu.getHeight());
		
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

