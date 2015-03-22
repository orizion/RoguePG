package ch.games.roguepg.tools;

import ch.games.roguepg.game.RoguePG;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;

public class RogueMap extends Map {
    private final RoguePG game;
    public final int TILE_SIZE;
    //Containing indices of Tile instances according to position in map
    private final int[][] tileIndeces; 
    private final MapLayer tileLayer;
    private final MapLayer objectLayer;
    private final MapLayer actorLayer;
    
    private final Tile grassTile;
    /* To be added:
    private final Tile dirtTile;
    private final Tile stoneTile;
    private final Tile waterTile;
    */
    
    /**
     * @param game
     * @param tileSize
     * @param mapXSize
     * @param mapYSize 
     */
    public RogueMap(final RoguePG game,final int tileSize, int mapXSize,int mapYSize) {
        this.game = game;
        this.TILE_SIZE = tileSize;

        tileIndeces = new int[mapXSize][mapYSize];

        tileLayer = new MapLayer();
        objectLayer = new MapLayer();
        actorLayer = new MapLayer();

        getLayers().add(tileLayer); 
        getLayers().add(objectLayer); 
        getLayers().add(actorLayer); 

        grassTile = new Tile(Tile.TileType.GRASS, this.game.getGrass(), "grass");
        getLayers().get(0).getObjects().add(grassTile);
        /* Repeat for all tile types. Add function to simplify? */        
    }
    
    public float indexToCoord(int index) {
            return index*TILE_SIZE;
    }
    
    public int coordToIndex(float coord) {
            return (int) (coord / TILE_SIZE);
    }
    
    public Tile getTileAt(float x, float y) {
        return (Tile) this.getLayers().get(0).getObjects().get(tileIndeces[coordToIndex(x)][coordToIndex(y)]);
    }

    public void generateMap() {
        //set the tile types here 
    }	
}