package edu.cesi.libgdx.frogger.view.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.cesi.libgdx.frogger.controler.MainGame;
import edu.cesi.libgdx.frogger.data.SettingsManager;
import edu.cesi.libgdx.frogger.model.Score;
import edu.cesi.libgdx.frogger.utils.enums.GameStates;
import edu.cesi.libgdx.frogger.view.ScoreStage;

public class EndLevelScreen implements Screen
{
	
	private SettingsManager settingsManager;
	private String difficulty;
	private ScoreStage stage;
	//private GameStates state;
	private Skin skin;
	private Label title;
	private TextButton btnRetry;
	//private TextButton btnHome;
	private int[] scoreCompare;
	private Label scoreLabel;
	private final Map<String, Integer> map ;
	
	private SpriteBatch batch;
	
	private TextButton btnSaveScore;
	private TextField pseudo;

	boolean limitSaveScore;
	Score myScore;
	private Texture background;
	String[] StringScore;
	
	public EndLevelScreen(GameStates state, Score score)
	{		
		this.map = new HashMap<String, Integer>();
		this.myScore = score;
		this.batch = new SpriteBatch();
		this.background = new Texture(Gdx.files.internal("settingsScreen/settingsBackground1200x800.jpg"));
		
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
		
		
		settingsManager = SettingsManager.getInstance();
		difficulty = settingsManager.getLevel();
        
		 StringScore = settingsManager.getHighScoreNew(difficulty);		
		
		System.out.println(StringScore.toString());
		
		scoreCompare = new int[StringScore.length];
		for (int i =0; i < StringScore.length; i++)
		{			
			String[] tmp = StringScore[i].split("-");
			map.put(tmp[0], Integer.parseInt(tmp[1]));	
			
			scoreCompare[i] = Integer.parseInt(tmp[1]);
		}
		sortValueMap();
		
		
		//String[] a= Arrays.toString(scoreCompare).split("[\\[\\]]")[1].split(", ");

		this.stage = new ScoreStage(StringScore);
		
		
		int z = -1;
		for (int i = 0; i < scoreCompare.length; i++)
		{
			if(scoreCompare[i] < score.getScore())
			{
				z = i;
			} 
		}
		
		if(z != -1)
		{
			scoreLabel = new Label("NEW SCORE ! : " + score.getScore() , skin, "newtitle"); 
			scoreLabel.setPosition(Gdx.graphics.getWidth()/3f, Gdx.graphics.getHeight() /1.25f);
			stage.addActor(scoreLabel);
			
			pseudo = new TextField("    pseudo",skin);
			pseudo.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight() /1.9f);
			pseudo.pack();
			pseudo.setVisible(true);
			stage.addActor(pseudo);
			
			
			btnSaveScore = new TextButton("Save score", skin,"default");
			btnSaveScore.setPosition(Gdx.graphics.getWidth()/2.6f, Gdx.graphics.getHeight() /1.9f);
			btnSaveScore.pad(20);
			btnSaveScore.pack();
			btnSaveScore.setSize(120,50);
			
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
			
			//sortValueMap();
		}
		
		Gdx.input.setInputProcessor(stage);
		
		//this.background = new Texture(Gdx.files.internal("settingsScreen/settingsBackground1200x800.jpg")); 
		
		if(state == GameStates.WIN)
		{
			title = new Label("YOU WIN ! ", skin, "newtitle"); 
			title.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight() -700);
			stage.addActor(title);
		}
		else if (state == GameStates.GAMEOVER)
		{
			title = new Label("GAME OVER ! ", skin, "newtitle"); 
			title.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight() -700);
			stage.addActor(title);
		}
		else
		{
			System.out.println("Error");
		}
		
		btnRetry = new TextButton("Try again", skin,"default");
		btnRetry.setPosition(Gdx.graphics.getWidth()/2 -btnRetry.getWidth()/2 , Gdx.graphics.getHeight() /2.5f );
		btnRetry.pad(20);
		btnRetry.pack();
		btnRetry.setSize(120,50);
		
		btnRetry.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
            	((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())
				.setScreen(new FroggerScreen((MainGame)((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())));
			}
		});
		stage.addActor(btnRetry);		
	}
	
	private void saveScore()
	{
		map.put(myScore.getName(), myScore.getScore());
		sortValueMap();
		
		Object[] keys = map.keySet().toArray();
		Object[] values = map.values().toArray();
		
		String[] scoreToSave = new String[StringScore.length];

		for (int i =0; i < StringScore.length; i ++)
		{
			String tmp = ((String) keys[i]).replaceAll(" ", "");
			
			scoreToSave[i] = tmp + "-" + values[i];
		}


	   
	   settingsManager.setHighScoreNew(scoreToSave);
	   settingsManager.saveModifications();

	}
	
	
	public void sortValueMap(){
		  final List<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>(map.entrySet());
		 
		   Collections.sort(entries, new Comparator<Entry<String, Integer>>() 
		  {
			    public int compare(final Entry<String, Integer> e1, final Entry<String, Integer> e2) 
			    {
			      return e2.getValue().compareTo(e1.getValue());
			    }
		  });
		 
		  for (final Entry<String, Integer> entry : entries) {
		    System.out.println(entry.getKey() + " " + entry.getValue());
		  }
	}
	
	@Override
	public void show() {	}

	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.end();
		
		stage.act();
		stage.draw();
		
	}

	
	
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
