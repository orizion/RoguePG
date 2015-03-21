package ch.games.roguepg.tools;

import com.badlogic.gdx.maps.tiled.TiledMap;

import ch.games.roguepg.game.RoguePG;

public class MapGenerator {
    private TiledMap map;
	private Tile[][] tiles;
	public final int TILE_SIZE;
	private final RoguePG game;
	
	public MapGenerator(final RoguePG game,final int size, int sizeX,int sizeY) {
		this.game = game;
		this.TILE_SIZE = size;
		this.tiles = new Tile[sizeX][sizeY];
	}
	public float indexToCoord(int index) {
		return index*TILE_SIZE;
	}
	public int coordToIndex(float coord) {
		return (int) (coord / TILE_SIZE);
	}
	public Tile getTileAt(float x, float y) {
		return tiles[coordToIndex(x)][coordToIndex(y)];
	}
	public Tile[][] getTiles() {
		return tiles;
	}
}
