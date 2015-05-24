package edu.cesi.libgdx.frogger.view.menu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import edu.cesi.libgdx.frogger.data.RessourceManagerMenu;

	public class MenuScreen implements Screen
	{
		
		private RessourceManagerMenu rm;
		private InputMultiplexer inputMultiplexer;
	    private MenuStage menuStage;
	    
	    
	    public MenuScreen(){
			
	        rm = new RessourceManagerMenu();
	        rm.initPlatformerResources();

	        inputMultiplexer = new InputMultiplexer();
	        menuStage = new MenuStage(rm);
	        inputMultiplexer.addProcessor(menuStage);
	        Gdx.input.setInputProcessor(inputMultiplexer);
	        
		}
		
		@Override
		public void show() {
			// TODO Auto-generated method stub
		}

		@Override
		public void render(float delta) {
			Gdx.gl.glClearColor(0f, 0f, 0f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        menuStage.act();
	        menuStage.draw();
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
			  //rm.dispose();
		      //menuStage.dispose();
		      System.out.println("MenuScreen dispose");
		}

	}


