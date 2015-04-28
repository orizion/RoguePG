package ch.games.roguepg.tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ch.games.roguepg.game.RoguePG;
import com.badlogic.gdx.maps.MapObject;


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
        for (int i=0; i < map.getTileIndices().length; i++) {
            for (int j = 0; j < map.getTileIndices()[0].length; j++) {
                /* Check all ints in tileIndeces, and print textures by comparing to TileType enum. */
                int current = map.getTileIndices()[i][j];

                for (MapObject tile : map.getLayers().get(0).getObjects()) {
                    if (tile.getProperties().get("type", Tile.TileType.class).ordinal() == current) {
                        game.getBatch().draw(
                            (TextureRegion) tile.getProperties().get("texture"),
                            map.indexToCoord(i), 
                            map.indexToCoord(j), 
                            64.0f, 64.0f
                        );
                    }
                }
            }
        }
    }

    @Override
    public void render(int[] layers) {
    }
}
