package edu.cesi.libgdx.frogger.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.cesi.libgdx.frogger.resources.MapManager;
import edu.cesi.libgdx.frogger.utils.UIManager;
import edu.cesi.libgdx.frogger.utils.enums.GameStates;

public class FroggerWorldRenderer 
{
	/**
	 * This class contain the {@link SpriteBatch} .
	 * It contain all the render behavior
	 * */
	
    private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
	private MapManager mapManager;
	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;
	private BitmapFont font; 
	private LevelFrogger level;
	private FroggerWorld world;
	private UIManager uiManager;	
	private Label labelScore;
	
	private BitmapFont timerBar;	
	private long progress = 0;
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	public FroggerWorldRenderer(FroggerWorld world)
	{
		  this.world = world;
		  this.uiManager = new UIManager();
		 /*Create the batch (Allow to draw)*/
		  this.batch = new SpriteBatch();
		  
		  this.font = new BitmapFont();
		  
		  /*Create the map*/
		  this.mapManager = new MapManager();
		  this.map = this.mapManager.getMap();
		  this.mapRenderer = new OrthogonalTiledMapRenderer(map);
		  
		  /*Create camera*/
		  this.camera = new OrthographicCamera();

		  /* Create a StretchViewport. It allow to handle screen with the logical size
		   * The items positions will be define depending upon the Viewport. 
		   * It allow to manage with differente resolution with a single and simple ItemPositionManager
		   * This object is linked to an OrthographicCamera
		   */
		  
		  this.viewport = new StretchViewport(1200,800,camera);
		  this.viewport.apply();
	      
		  this.camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
		  
		  this.mapRenderer.setView(camera);
		  this.camera.update();
		  
	      this.level = this.world.getLevel();
	      this.level.createElements();
	      
	      labelScore =  uiManager.createLabelTitle("Score");
	      labelScore.setPosition(970, 18);
	      
	      timerBar = new BitmapFont();
	}
	
	
	public void resize(int width , int height)
	{
	      this.viewport.update(width,height);
	      this.camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}
	
	public OrthographicCamera getCamera() 
	{
		return camera;
	}

	public Viewport getViewport() 
	{
		return viewport;
	}
	
	/**
	 * Convert byte to Ko Mo Go ...
	 * */
    private String getFormattedSize(long size) 
    {
        String[] suffixes = new String[] { "octets", "Ko", "Mo", "Go", "To" };
 
        double tmpSize = size;
        int i = 0;
 
        while (tmpSize >= 1024) {
            tmpSize /= 1024.0;
            i++;
        }
        // round 10^-2
        tmpSize *= 100;
        tmpSize = (int) (tmpSize + 0.5);
        tmpSize /= 100;
 
        return tmpSize + " " + suffixes[i];
    }

	/**
	 * Called by {@link FroggerWorld} at each cycle. It draw all graphics and manage the camera.
	 * @param stateTime : the current delta time
	 * */
	public void renderWorld(float stateTime)
	{	
		//Background color
	    Gdx.gl.glClearColor(0, 0, 0, 1);
	    //Clear screen
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	    //Update camera
	    this.camera.update();
		this.mapRenderer.setView(camera);
		//Render map
		this.mapRenderer.render();
		//Update label score
	    this.labelScore.setText("Score : " + world.getScore());
	    
	    //Start to draw
		this.batch.begin();
			//Draw score
		    labelScore.draw(batch, stateTime);
		    //Draw debug
		    this.font.draw(batch, Integer.toString(Gdx.graphics.getFramesPerSecond()), 600, 680);
		    this.font.draw(batch, getFormattedSize(Gdx.app.getJavaHeap()), 600, 150);
		    //Draw camera
		    this.batch.setProjectionMatrix(camera.combined);
		    
		    if(this.world.getGameState() != GameStates.PAUSE)
		    {
				this.level.updateElements(stateTime, this.batch);
		    }
		    this.world.getPlayer().drawAnimation(this.batch);
		    
		    //Draw label timer
	        this.progress = world.getTimer();
	        timerBar.draw(batch, "Timer : " + progress , 800, 80);       
		this.batch.end(); 
		
		//Check collision maybe move to worlds
		
		
		//Draw filled rectangle
		shapeRenderer.begin(ShapeType.Filled);	
	        shapeRenderer.setProjectionMatrix(camera.combined);
	        shapeRenderer.setColor(Color.YELLOW);
	        shapeRenderer.rect(800, 35, world.getTimer(), 20);
        shapeRenderer.end();
        
		//Draw empty rectangle
		shapeRenderer.begin(ShapeType.Line);	
	        shapeRenderer.setProjectionMatrix(camera.combined);
	        shapeRenderer.setColor(Color.YELLOW);
	        shapeRenderer.rect(800, 35, 60, 20);
	        
	        /*Debug*/
			shapeRenderer.rect(world.getPlayer().getAdvancedCollisionRectangle().getX(), world.getPlayer().getAdvancedCollisionRectangle().getY(), world.getPlayer().getAdvancedCollisionRectangle().getWidth(),world.getPlayer().getAdvancedCollisionRectangle().getHeight()); 
			shapeRenderer.rect(world.getPlayer().getBounds().getX(), world.getPlayer().getBounds().getY(), world.getPlayer().getBounds().getWidth(),world.getPlayer().getBounds().getHeight()); 
	    shapeRenderer.end();
	}
	
	/**
	 * Release resources
	 * */
	public void dispose()
	{
	    batch.dispose();
	    shapeRenderer.dispose();
		map.dispose();
		mapRenderer.dispose();
		font.dispose();
		timerBar.dispose();
		System.out.println("Frogger World renderer  dispose");
	}

}
