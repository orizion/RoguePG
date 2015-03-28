package ch.games.roguepg.tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
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
        for (int i=0; i < map.getMaxXIndex(); i++) {
            for (int j = 0; j < map.getMaxYIndex(); j++) {
            int tile = map.getTileEnum(i, j);
                game.getBatch().draw((TextureRegion) map.getLayers().get(0).getObjects().get(tile).getProperties().get("texture"), map.indexToCoord(i), map.indexToCoord(j), 64.0f, 64.0f);
                /*TODO: Consider opacity/visibility when drawing */
            }
        }
    }

    @Override
    public void render(int[] layers) {
    }
}
