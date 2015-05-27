package edu.cesi.libgdx.frogger.view.game;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.cesi.libgdx.frogger.controler.MainGame;
import edu.cesi.libgdx.frogger.model.Score;
import edu.cesi.libgdx.frogger.resources.SettingsManager;
import edu.cesi.libgdx.frogger.utils.UIManager;
import edu.cesi.libgdx.frogger.utils.ValueComparator;
import edu.cesi.libgdx.frogger.utils.enums.GameStates;
import edu.cesi.libgdx.frogger.view.highScore.ScoreStage;

public class EndLevelScreen implements Screen
{
	
	private SettingsManager settingsManager;
	private String difficulty;
	private ScoreStage stage;
	private Skin skin;
	private Label titleWinLoose;
	private TextButton btnRetry;
	private int[] scoreCompare;
	private Label scoreLabel;
	private Map<String, Integer> map ;
	private SpriteBatch batch;
	private TextButton btnSaveScore;
	private TextField pseudo;
	private boolean limitSaveScore;
	private Score myScore;
	private Texture background;
	private String[] StringScore;
	
	private UIManager uiManager;
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private ValueComparator comparator;
	
	public EndLevelScreen(GameStates state, Score score)
	{		
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(1200,800,camera);
		this.viewport.apply();
		this.camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
		this.camera.update();
		
		comparator = new ValueComparator();
		
		this.map = new HashMap<String, Integer>();
		
		this.myScore = score;
		this.batch = new SpriteBatch();
		this.background = new Texture(Gdx.files.internal("settingsScreen/settingsBackground1200x800.jpg"));
		uiManager = new UIManager();
		
		
		
		
		settingsManager = SettingsManager.getInstance();
		difficulty = settingsManager.getLevel();
		StringScore = settingsManager.getHighScore(difficulty);		
				
		scoreCompare = new int[StringScore.length];
		for (int i =0; i < StringScore.length; i++)
		{			
			String[] tmp = StringScore[i].split(" - ");
			map.put(tmp[0], Integer.parseInt(tmp[1]));	
			scoreCompare[i] = Integer.parseInt(tmp[1]);
		}
		//map = comparator.sortValueMap(map);
		this.stage = new ScoreStage(StringScore);

		
		int z = -1;
		for (int i = 0; i < scoreCompare.length; i++)
		{
			if(scoreCompare[i] < score.getScore())
			{
				z = i;
			} 
		}
		
		

		if(state == GameStates.WIN)
		{
			titleWinLoose = uiManager.createLabelTitle("YOU WIN !");
			titleWinLoose.setPosition(Gdx.graphics.getWidth()/2 -titleWinLoose.getWidth() /2, stage.getTitle().getY() - stage.getTitle().getHeight());
			stage.addActor(titleWinLoose);
			System.out.println("WIN");
	
		}
		else if (state == GameStates.GAMEOVER)
		{
			titleWinLoose = uiManager.createLabelTitle("GAME OVER !");
			titleWinLoose.setPosition(Gdx.graphics.getWidth()/2 -titleWinLoose.getWidth()/2, stage.getTitle().getY() - stage.getTitle().getHeight());
			stage.addActor(titleWinLoose);
			System.out.println("loooose");
		}
		else
		{
			System.out.println("Error");
		}
		
		
		if(z != -1)
		{
			createHighScoreUI(score);
		}
		
		
	
		
		btnRetry = uiManager.createButton("Try again");
		btnRetry.setPosition(Gdx.graphics.getWidth()/2 -btnRetry.getWidth()/2 , stage.getMainMenu().getY() - stage.getMainMenu().getHeight());
		btnRetry.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
            	((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())
				.setScreen(new FroggerScreen((MainGame)((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())));
			}
		});
		stage.addActor(btnRetry);
		
		Gdx.input.setInputProcessor(stage);
	}
	
	
	private void createHighScoreUI(Score score)
	{
		scoreLabel = uiManager.createLabelTitle("NEW SCORE ! : " + score.getScore());
		scoreLabel.setPosition(Gdx.graphics.getWidth()/2 -scoreLabel.getWidth()/2, titleWinLoose.getY() - titleWinLoose.getHeight());
		stage.addActor(scoreLabel);
		
		pseudo = new TextField("    pseudo",uiManager.getCustomSkin());
		pseudo.setPosition(Gdx.graphics.getWidth()/1.9f, stage.getMainMenu().getY() + stage.getMainMenu().getHeight());
		pseudo.pack();
		pseudo.setVisible(true);
		stage.addActor(pseudo);
		
		btnSaveScore = uiManager.createButton("Save score");
		btnSaveScore.pad(5, 5, 5, 5);
		btnSaveScore.setPosition(Gdx.graphics.getWidth()/2.6f, stage.getMainMenu().getY() + stage.getMainMenu().getHeight());
		
		limitSaveScore = false;
		
		btnSaveScore.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(!limitSaveScore)
				{
					myScore.setName(pseudo.getText());
					saveScore();
					limitSaveScore = true;
				}
			}
		});
		stage.addActor(btnSaveScore);
	}
	
	private void saveScore()
	{
		map.put(myScore.getName(), myScore.getScore());
		Object[] sortedScore = comparator.sortValueMap(map);
				
		String[] scoreToSave = new String[StringScore.length];
		
		for (int i =0; i < StringScore.length; i ++)
		{
			String tmp = ""+ sortedScore[i]; 
			String tmp1 = tmp.replaceAll(" ", "");
			String tmp2 = tmp1.replaceAll("/^[a-zA-Z]+$/", "");
			String tmp3 = tmp2.replaceAll("=", " - ");
			
			scoreToSave[i] = ""+ tmp3;

		}
	   settingsManager.setHighScore(scoreToSave);
	   settingsManager.saveModifications();
	}
	
	
	@Override
	public void show() {	}

	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.camera.update();	

		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.draw(background, 0, 0);
		batch.end();
		
		stage.act();
		stage.draw();
		
	}
	


	@Override
	public void resize(int width, int height) {
	      this.viewport.update(width,height);
	      this.camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		background.dispose();
		batch.dispose();
		System.out.println("End screen dispose");
		
	}



}
