package ch.games.roguepg.tools;

import com.badlogic.gdx.maps.tiled.TiledMap;

import ch.games.roguepg.game.RoguePG;
/**
 * 
 * @author Stefan
 *
 */
public class RogueMap {
    private final RoguePG game;
    
    private Tile[] tiles;
	public final int TILE_SIZE;
	//Containing indices of Tile instances according to position in map
	private int[][] map;
	
	/**
	 * 
	 * @param game
	 * @param tileSize
	 * @param mapXSize
	 * @param mapYSize
	 * @param tiles
	 */
	public RogueMap(final RoguePG game,final int tileSize, int mapXSize,int mapYSize,Tile[] tiles) {
		this.game = game;
		this.TILE_SIZE = tileSize;
		this.tiles = tiles;
		map = new int[mapXSize][mapYSize];
	}
	public float indexToCoord(int index) {
		return index*TILE_SIZE;
	}
	public int coordToIndex(float coord) {
		return (int) (coord / TILE_SIZE);
	}
	public Tile getTileAt(float x, float y) {
		return tiles[map[coordToIndex(x)][coordToIndex(y)]];
	}
	
	public void generateMap() {
	    //set the tile types here 
	}
	
	
	
}
