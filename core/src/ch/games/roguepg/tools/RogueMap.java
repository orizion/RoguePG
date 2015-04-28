package ch.games.roguepg.tools;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;

import ch.games.roguepg.game.RoguePG;

public class RogueMap extends Map {
    private final RoguePG game;
    public final int TILE_SIZE;
    /* Contains indices of Tile instances according to position in map */
    private int[][] tileIndices; 
    private final MapLayer tileLayer;
    private final MapLayer objectLayer;
    private final MapLayer actorLayer;
    
    private final Tile dirtTile, grassTile;
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


        tileLayer = new MapLayer();
        objectLayer = new MapLayer();
        actorLayer = new MapLayer();

        getLayers().add(tileLayer); 
        getLayers().add(objectLayer); 
        getLayers().add(actorLayer); 
        dirtTile = new Tile(Tile.TileType.DIRT, this.game.getImpassable(), "DIRT", this);
        grassTile = new Tile(Tile.TileType.GRASS, this.game.getGrass(), "GRASS", this);
        generateMap(mapXSize, mapYSize);

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
    
    public int[][] getTileIndices() {
        return tileIndices;
    }
    

    public void generateMap(int mapXSize,int mapYSize) {
        tileIndices = new int[mapXSize][mapYSize];
        /* All array elements are initially zero, which equates to the standard tile grass.
           Generate the map by setting zeroes to other tile numbers. */
        
       tileIndices[0][0] = 1;
    }	
}
