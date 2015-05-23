package edu.cesi.libgdx.frogger.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import edu.cesi.libgdx.frogger.controler.MainGame;
import edu.cesi.libgdx.frogger.world.FroggerWorld;
import edu.cesi.libgdx.frogger.world.FroggerWorldRenderer;

public class FroggerScreen implements Screen
{
	private MainGame mainGame;
    private float stateTime;     

	private FroggerWorld world;
	private FroggerWorldRenderer worldRenderer;
	
    public FroggerScreen(MainGame mainGame)
    {
      super();
      this.mainGame = mainGame;
      this.stateTime = 0;
	  this.world = new FroggerWorld(this.mainGame, this);
	  this.worldRenderer = new FroggerWorldRenderer(this.world);
    }
	   
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) 
	{
	    Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		this.stateTime += Gdx.graphics.getDeltaTime();
		
		if(!this.world.updateWorld())
		{
			this.worldRenderer.renderWorld(this.stateTime);
			this.world.updatePauseGame();
		}else
		{
			this.worldRenderer.renderWorld(this.stateTime);
		}
	}
	
	public FroggerWorld getWorld(){
		return this.world;
	}

	@Override
	public void resize(int width, int height) 
	{
		this.worldRenderer.resize(width, height);
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
		// TODO Auto-generated method stub
		
	}
	
	public float getStateTime() {
		return stateTime;
	}
}
