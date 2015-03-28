package ch.games.roguepg.tools;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;

import ch.games.roguepg.game.RoguePG;

public class RogueMap extends Map {
    private final RoguePG game;
    public final int TILE_SIZE;
    //Containing indices of Tile instances according to position in map
    private final int[][] tileIndices; 
    private final int maxXIndex, maxYIndex;
    private final MapLayer tileLayer;
    private final MapLayer objectLayer;
    private final MapLayer actorLayer;
    
    private final Tile impassableTile, grassTile;
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
        this.maxXIndex = mapXSize/TILE_SIZE;
        this.maxYIndex = mapYSize/TILE_SIZE;

        tileIndices = generateMap();

        tileLayer = new MapLayer();
        objectLayer = new MapLayer();
        actorLayer = new MapLayer();

        getLayers().add(tileLayer); 
        getLayers().add(objectLayer); 
        getLayers().add(actorLayer); 
        
        impassableTile = new Tile(Tile.TileType.IMPASSABLE, this.game.getImpassable(), "impassable");
        getLayers().get(0).getObjects().add(impassableTile);
        getLayers().get(0).getObjects().get("impassable").setOpacity(0.1f); /* These will be invisible */
        grassTile = new Tile(Tile.TileType.GRASS, this.game.getGrass(), "grass");
        getLayers().get(0).getObjects().add(grassTile);
        /* TODO: Repeat for all tile types. Add function to simplify? */        
    }
    
    public float indexToCoord(int index) {
            return index*TILE_SIZE;
    }
    
    public int coordToIndex(float coord) {
            return (int) (coord / TILE_SIZE);
    }
    
    public Tile getTileAt(float x, float y) {
        return (Tile) this.getLayers().get(0).getObjects().get(tileIndices[coordToIndex(x)][coordToIndex(y)]);
    }
    
    public int getTileEnum(int x, int y) {
        return tileIndices[x][y];
    }
    
   public int getMaxXIndex() {
        return maxXIndex;
    }

    public int getMaxYIndex() {
        return maxYIndex;
    }

    public int[][] generateMap() {
        int[][] tileIndices = new int[maxXIndex][maxYIndex];
        /* All array elements are initially zero, which equates to the impassable 
        tile. Generate the map by setting zeroes to other tile numbers. */        
        return tileIndices;
    }	
}