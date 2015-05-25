package edu.cesi.libgdx.frogger.resources;

import com.badlogic.gdx.Gdx;
import com.uwsoft.editor.renderer.data.ProjectInfoVO;
import com.uwsoft.editor.renderer.data.ResolutionEntryVO;
import com.uwsoft.editor.renderer.resources.ResourceManager;

public class RessourceManagerMenu extends ResourceManager{
	
	
	/**
	 * This class load the menu assets manager it use another graphic library compatible with LibGdx to load them
	 * The different resolution for the main menu are managed here 
	 * */
	
	public ResolutionEntryVO	currentResolution;
	public float stageWidth;
	
   public void initPlatformerResources() {
        // Loading project configuration file that keeps list of available resolutions
	    loadProjectVO();
	
	    // figure out the best resolution from the list of available resolutions for the existing screen size
	    currentResolution = getBestResolutionMatch(getProjectVO());
	
	    // Game stage width should be dynamic based on screen size, so if you have wider screen, instead of stretching
	    // It will be just more level visible for player.
	    stageWidth = Gdx.graphics.getHeight()/currentResolution.height*Gdx.graphics.getWidth();
	
	    // Sets working resolution, so init all resources will load in to memory resources of that resolution/size only
	    setWorkingResolution(currentResolution.name);
	
	    // loads into memory all needed assets
	    initAllResources();
	    System.out.println("currentResolution.name" + currentResolution.name);
   }
	   
	    /**
	     * This method finds the closest match from the available texture resolutions generated by overlap2d
	     * that are closest in size to the current screen size. The comparison goes by height.
	     *
	     * @param projctInfoVo
	     * @return ResolutionEntryVO
	     */
	    public ResolutionEntryVO getBestResolutionMatch(ProjectInfoVO projctInfoVo) {
	    	
	        float deltaSize = Math.abs(projctInfoVo.originalResolution.height - Gdx.graphics.getHeight());
	        ResolutionEntryVO result = projctInfoVo.originalResolution;

	        for(int i = 0; i < projctInfoVo.resolutions.size(); i++) {
	            float newDeltaSize = Math.abs(projctInfoVo.resolutions.get(i).height - Gdx.graphics.getHeight());
	            System.out.println(newDeltaSize);
	            if(deltaSize > newDeltaSize) {
	                deltaSize = newDeltaSize;
	                result = projctInfoVo.resolutions.get(i);
	            }
	        }

	        return result;
	    }

}