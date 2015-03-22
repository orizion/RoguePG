package ch.games.roguepg.tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ch.games.roguepg.game.RoguePG;


public class RogueMapRenderer implements MapRenderer{
    private final RogueMap map;
    private final RoguePG game;
    
    public RogueMapRenderer(RogueMap map, RoguePG game){
        this.map = map;
        this.game = game;
    }
    
    @Override
    public void setView(OrthographicCamera camera) {
    }

    @Override
    public void setView(Matrix4 projectionMatrix, float viewboundsX, float viewboundsY, float viewboundsWidth, float viewboundsHeight) {    
    }

    @Override
    public void render() {
    
        for (MapObject object : this.map.getLayers().get(0).getObjects()) {
            /* For testing purposes. Later it will draw all tiles as laid out in tileIndices */
            this.game.getBatch().draw((TextureRegion) object.getProperties().get("texture"), 0.0f, 0.0f, 64.0f, 64.0f);
        }
    }

    @Override
    public void render(int[] layers) {
    }
}
