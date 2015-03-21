package ch.games.roguepg.tools;

public class Tile {
	public static enum TileType {
		GRASS,
		DIRT,
		STONE,
		WATER,
	}
	private TileType type;
	private int x;
	private int y;
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public TileType getType() {
		return type;
	}
	public Tile(TileType type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}
}
