package edu.cesi.libgdx.frogger.model;

import com.badlogic.gdx.math.Vector2;

public class Score 
{
	
	private int score = 0;
	private Vector2 lastMaxPosition;
	private String name;

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Score(){
		this.lastMaxPosition = new Vector2(0,0);
	}
	
	public Score(float x, float y)
	{
		this.lastMaxPosition = new Vector2(0,0);
		this.lastMaxPosition.x = y;
		this.lastMaxPosition.y = y;
	}
	
	
	public Vector2 getLastMaxPosition() {
		return lastMaxPosition;
	}

	public void setLastMaxPosition(Vector2 lastMaxPosition) {
		this.lastMaxPosition = lastMaxPosition;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	
	public void increaseScore(float x, float y){
		if(this.lastMaxPosition.y < y){
			this.score += 1;
			this.lastMaxPosition.y = y;
		}
	}

	public void resetScore(){
		this.score = 0;
		this.lastMaxPosition.x = 0;
		this.lastMaxPosition.y = 0;
	}

}
