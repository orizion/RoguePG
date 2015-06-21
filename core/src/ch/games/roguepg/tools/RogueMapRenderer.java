package ch.games.roguepg.tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ch.games.roguepg.game.RoguePGGame;
import com.badlogic.gdx.maps.MapObject;


public class RogueMapRenderer implements MapRenderer{
    private final RogueMap map;
    
    public RogueMapRenderer(RogueMap map){
        this.map = map;
    }
    
    @Override
    public void setView(OrthographicCamera camera) {
    }

    @Override
    public void setView(Matrix4 projectionMatrix, float viewboundsX, float viewboundsY, float viewboundsWidth, float viewboundsHeight) {    
    }

    @Override
    public void render() {
        for (int i=0; i < RogueMap.tileIndices.length; i++) {
            for (int j = 0; j < RogueMap.tileIndices[0].length; j++) {
                /* Check all ints in tileIndeces, and print textures by comparing to TileType enum. */
                int current = RogueMap.tileIndices[i][j];

                for (MapObject tile : map.getLayers().get(0).getObjects()) {
                    if (tile.getProperties().get("type", Tile.TileType.class).ordinal() == current) {
                        RoguePGGame.batch.draw(
                            (TextureRegion) tile.getProperties().get("texture"),
                            RogueMap.indexToCoord(i), 
                            RogueMap.indexToCoord(j), 
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
