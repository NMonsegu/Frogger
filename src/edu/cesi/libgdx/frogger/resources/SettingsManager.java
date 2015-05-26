package edu.cesi.libgdx.frogger.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import edu.cesi.libgdx.frogger.utils.Constants;


public class SettingsManager 
{
	private SettingsManager()
	{
		this.prefs = Gdx.app.getPreferences("gdf");
	}
	
	private static class SettingsHolder
	{
		private final static SettingsManager instance = new SettingsManager();
	}
	
	public static SettingsManager getInstance()
	{
		return SettingsHolder.instance;
	}
	
	private boolean currentStateSound;
	private String  currentStateLevel;
	private String  currentStateResolution;

	private Preferences prefs;
	private String[] highScore = new String[Constants.NUMBER_OF_HIGH_SCORE];

	
	
	public void setHighScore(String[] highscore)
	{
		for(int i = 0; i < highscore.length; i ++)
		{
			this.prefs.putString("highscores" + this.currentStateLevel  + i , highscore[i]);
		}
	}
	
	//String[] testData = new String[] {"toto-100","tito-150","tata-99","zaza-200","lolo-50"};
	
	public String[] getHighScore(String difficulty)
	{
		for(int i = 0; i < highScore.length; i ++)
		{
			highScore[i] = this.prefs.getString("highscores" + difficulty + i,i + "nodata - 0");
		}
		return highScore;
	}
		
	public boolean isSoundEnable(){
		this.currentStateSound = this.prefs.getBoolean("sound",false);
		return this.currentStateSound;
	}
	
	public String getResolution(){
		this.currentStateResolution = this.prefs.getString("currentResolution","1200x800");
		return this.currentStateResolution;
	}
	
	public String getLevel(){
		this.currentStateLevel = this.prefs.getString("level","EASY");
		return this.currentStateLevel;
	}
	
	public void setSoundEnable(boolean isSoundEnable){
		this.prefs.putBoolean("sound", isSoundEnable);
		this.currentStateSound = isSoundEnable;
	}
	
	public void setResolution(String resolution){
		this.prefs.putString("currentResolution", resolution);
		this.currentStateResolution = resolution;
	}
	
	public void setLevel(String level){
		this.prefs.putString("level", level);
		this.currentStateLevel = level;
	}
	
	public void saveModifications(){
		this.prefs.flush();
	}
	
	public boolean isCurrentStateSound() {
		return currentStateSound;
	}

	public String getCurrentStateLevel() {
		return currentStateLevel;
	}

	public String getCurrentStateResolution() {
		return currentStateResolution;
	}
}
