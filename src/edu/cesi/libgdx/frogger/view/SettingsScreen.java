package edu.cesi.libgdx.frogger.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.cesi.libgdx.frogger.data.SettingsManager;
import edu.cesi.libgdx.frogger.view.menu.MenuScreen;

public class SettingsScreen implements Screen
{

	private Skin skin;
	private Label titre;
	private Label difficulty;
	private Label sound;
	private Label resolution;
	
	private Slider sliderLevel;
	private Slider sliderSound;
	private Slider sliderResolution;
	
	private TextButton buttonSave;	
	private TextButton buttonPrevious;
	private SpriteBatch batch;
	private Texture background;
	private Stage stage;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private SettingsManager settingsManager;
	
	public SettingsScreen(){
		this.batch = new SpriteBatch();
		
		this.settingsManager = SettingsManager.getInstance();
		
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(1200,800,camera);
		this.viewport.apply();
	      
		this.camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
		this.camera.update();
		createElements();

}
	
	private void createElements(){
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
		
		this.stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin();
		skin.addRegions(new TextureAtlas(Gdx.files.internal("skins/custom.atlas")));
		skin.add("bigTitle", grosTitres);
		skin.add("label", label);
		skin.load(Gdx.files.internal("skins/customskin.json"));
		
		this.background = new Texture(Gdx.files.internal("settingsScreen/image.jpg")); 
		
		
		titre = new Label("Settings", skin, "newtitle");  //création d'un  label avec font «  titletext » et color red 
		titre.setPosition(Gdx.graphics.getWidth()/2-titre.getWidth()/2, Gdx.graphics.getHeight() -100);
		stage.addActor(titre);

		
		difficulty = new Label("1", skin); 
		difficulty.setPosition(Gdx.graphics.getWidth()/2-titre.getWidth()/2, Gdx.graphics.getHeight() -150);
		difficulty.setVisible(true);
		stage.addActor(difficulty);
		
		
		sliderLevel = new Slider(0,2,1,false,skin); 
		sliderLevel.setPosition(Gdx.graphics.getWidth()/2-titre.getWidth()/2, Gdx.graphics.getHeight() -180);
		sliderLevel.setVisible(true);
		stage.addActor(sliderLevel);
		
		
		sound = new Label("Sound : ON", skin); 
		sound.setPosition(Gdx.graphics.getWidth()/2-titre.getWidth()/2, Gdx.graphics.getHeight() -210);
		sound.setVisible(true);
		stage.addActor(sound);
		
		sliderSound = new Slider(0,1,1,false,skin); 
		sliderSound.setPosition(Gdx.graphics.getWidth()/2-titre.getWidth()/2, Gdx.graphics.getHeight() -230);
		sliderSound.setVisible(true);
		stage.addActor(sliderSound);
		
		resolution = new Label("Resolution : Default", skin); 
		resolution.setPosition(Gdx.graphics.getWidth()/2-titre.getWidth()/2, Gdx.graphics.getHeight() -260);
		resolution.setVisible(true);
		stage.addActor(resolution);
		
		sliderResolution = new Slider(0,2,1,false,skin); 
		sliderResolution.setPosition(Gdx.graphics.getWidth()/2-titre.getWidth()/2, Gdx.graphics.getHeight() -280);
		sliderResolution.setVisible(true);
		stage.addActor(sliderResolution);

		buttonSave = new TextButton("Save", skin,"default");
		buttonSave.setPosition(Gdx.graphics.getWidth()/2-titre.getWidth()/2, Gdx.graphics.getHeight() - 350 );
		buttonSave.pad(20);
		buttonSave.pack();//sinon padding non pris en compte !!
		buttonSave.setSize(120,50);
		
		buttonSave.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				setDifficulty();
				setSound();
				setResolution();

			}
		});
		stage.addActor(buttonSave);
		
		buttonPrevious = new TextButton("Previous", skin,"default");
		
		buttonPrevious.setPosition(Gdx.graphics.getWidth()/2-titre.getWidth()/2, Gdx.graphics.getHeight() - 400 );
		buttonPrevious.pad(20);
		buttonPrevious.pack();//sinon padding non pris en compte !!
		buttonPrevious.setSize(120,50);
		
		buttonPrevious.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
            	((com.badlogic.gdx.Game) Gdx.app.getApplicationListener())
				.setScreen(new MenuScreen());
			}
		});
		stage.addActor(buttonPrevious);
		
		getLabelDifficulty();
		getLabelResolution();
		getLabelSound();
	}
	
	@Override
	public void render(float delta) {
		
		this.changeLabelDifficulty();
		
		this.changeLabelSound();
		
		this.changeLabelResolution();

		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		this.camera.update();	
		
		batch.begin();
			batch.setProjectionMatrix(camera.combined);
			batch.draw(background, 0, 0);
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	private void changeLabelDifficulty(){
		int valueDifficulty = (int)sliderLevel.getValue();
		String labelDifficulty;
		
		if(valueDifficulty == 0)
		{
			labelDifficulty = "Difficulty : EASY";
		}
		else if(valueDifficulty == 1)
		{
			labelDifficulty = "Difficulty : NORMAL";
		}else
		{
			labelDifficulty = "Difficulty : HARD";
		}
		difficulty.setText(labelDifficulty);
	}
	
	private void changeLabelResolution()
	{		
		int valueResolution = (int)sliderResolution.getValue();
		String labelResolution;
		
		if(valueResolution == 0)
		{
			labelResolution = "Resolution : 800x480";
		}
		else if(valueResolution == 1)
		{
			labelResolution = "Resolution : 1200x800";
		}else
		{
			labelResolution = "Resolution : 1440x900";
		}
		resolution.setText(labelResolution);
	}
	
	private void changeLabelSound(){
		int valueSound = (int)sliderSound.getValue();
		String labelsound;
		
		if(valueSound ==1)
		{
			labelsound = "Sound : ON";
		}else{
			labelsound = "Sound : OFF";
		}
		sound.setText(labelsound);
	}
	
	private void getLabelDifficulty()
	{
		String labelDifficulty = this.settingsManager.getLevel();
		System.out.println(labelDifficulty + "Label settingsScreen");
		
		if(new String("EASY").equals(labelDifficulty))
		{
			this.sliderLevel.setValue(0);
		}
		else if(new String("MEDIUM").equals(labelDifficulty))
		{
			this.sliderLevel.setValue(1);
		}else
		{
			this.sliderLevel.setValue(2);
		}
		this.changeLabelDifficulty();
	}
	
	private void getLabelResolution()
	{
		String labelResolution = this.settingsManager.getResolution();
		
		if(new String("800x480").equals(labelResolution))
		{
			this.sliderResolution.setValue(0);
		}
		else if(new String("1200x800").equals(labelResolution))
		{
			this.sliderResolution.setValue(1);
		}else
		{
			this.sliderResolution.setValue(2);
		}
		this.changeLabelResolution();
	}
	
	private void getLabelSound()
	{
		boolean isSoundEnable = this.settingsManager.isSoundEnable();
		int myInt = (isSoundEnable) ? 1 : 0;
		sliderSound.setValue((float)myInt);
		changeLabelSound();
	}
	
	private void setDifficulty(){
		int valueDifficulty = (int)sliderLevel.getValue();
		
		if(valueDifficulty == 0)
		{
			this.settingsManager.setLevel("EASY");
		}
		else if(valueDifficulty == 1)
		{
			this.settingsManager.setLevel("MEDIUM");
		}else
		{
			this.settingsManager.setLevel("HARD");
			
		}
		settingsManager.saveModifications();

	}
	
	private void setResolution(){
		int valueResolution = (int)sliderResolution.getValue();
		
		if(valueResolution == 0)
		{
			this.settingsManager.setResolution("800x480");
			Gdx.graphics.setDisplayMode(800, 480, false);
			this.resize(800, 480);
		}
		else if(valueResolution == 1)
		{
			this.settingsManager.setResolution("1200x800");
			Gdx.graphics.setDisplayMode(1200, 800, false);
			this.resize(1200, 800);

		}else
		{
			this.settingsManager.setResolution("1440x900");
			Gdx.graphics.setDisplayMode(1440, 900, false);
			this.resize(1440, 900);
		}
		settingsManager.saveModifications();
		this.createElements();
	}
	
	private void setSound(){
		int valueSound = (int)sliderSound.getValue();
		boolean isSoundEnable = (valueSound != 0);
		this.settingsManager.setSoundEnable(isSoundEnable);
		settingsManager.saveModifications();
	}
	
	@Override
	public void resize(int width, int height) {
	      this.viewport.update(width,height);
	      this.camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
