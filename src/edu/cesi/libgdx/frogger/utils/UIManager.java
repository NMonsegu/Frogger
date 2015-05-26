package edu.cesi.libgdx.frogger.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;

/**
 * This class is used to create UI elements 
 * 
 * **/
public class UIManager 
{
	private Skin skin;
	private BitmapFont bigTitleFont;
	private BitmapFont labelsFont;

	public UIManager(){
		this.loadFontGenerators();
		this.useCustomSkin();
	}
	
	/**Load .ttf fonts*/
	private void loadFontGenerators()
	{
		try
		{
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/immortal.ttf"));
			FreeTypeFontParameter parameter = new FreeTypeFontParameter();
			parameter.size = 40;
			this.bigTitleFont = generator.generateFont(parameter);
			generator.dispose();
			
			FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Remachine.ttf"));
			FreeTypeFontParameter parameter2 = new FreeTypeFontParameter();
			parameter2.size = 40;
			this.labelsFont = generator2.generateFont(parameter2);
			generator2.dispose();
		}catch(NullPointerException ex)
		{
			System.err.println(ex + "Error loading custom font");
		}catch(Exception ex)
		{
			System.err.println(ex + "Error loading custom font");
		}
	}
	
	/**Use custom skin as default skin*/
	public void useCustomSkin()
	{
		this.skin = new Skin();
		this.skin.addRegions(new TextureAtlas(Gdx.files.internal("skins/custom.atlas")));
		this.skin.add("bigTitle", this.bigTitleFont);
		this.skin.add("label", this.labelsFont);
		this.skin.load(Gdx.files.internal("skins/customskin.json"));
	}
	/**return default libgdx skin*/
	public Skin getDefaultSkin()
	{
		Skin skin = new Skin();
		skin.addRegions(new TextureAtlas(Gdx.files.internal("defaultSkins/uiskin.atlas")));
		skin.load(Gdx.files.internal("defaultSkins/uiskin.json"));
		return skin;
	}
	
	public Skin getCustomSkin(){
		return this.skin;
	}
	
	public Label createLabelTitle( String text)
	{
		Label label = new Label(text, this.skin, "newtitle"); 
		label.setVisible(true);
		return label;
	}
	
	public Label createLabel(String text){
		Label label = new Label(text, this.skin); 
		label.setVisible(true);
		return label;
	}
	
	public Slider createSlider(float min, float max, float step)
	{
		Slider slider = new Slider(min, max, step, false, this.skin); 
		return slider;
	}
	
	public TextButton createButton(String textButton)
	{
		TextButton button = new TextButton(textButton, this.skin,"default");
		button.setPosition(Gdx.graphics.getWidth()/2-button.getWidth()/2, Gdx.graphics.getHeight() - 350 );
		button.pad(20);
		button.pack();
		button.setSize(120,50);
		return button ;
	}
	
	public List<String> createList(Array<String> listItems){
        List<String> listView = new List<String>(new Skin(Gdx.files.internal("defaultSkins/uiskin.json")));
        listView.setItems(listItems);
        return listView;
	}
	
}
