package ch.games.roguepg.tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapRenderer;

public class Tile extends MapObject{
	public static enum TileType {
		GRASS,
		DIRT,
		STONE,
		WATER,
	}
	public Tile(TileType type,TextureRegion texture) {
	    getProperties().put("type", type);
		getProperties().put("texture", texture);
	}
	public Tile(TileType type,TextureRegion texture,String name) {
	    this(type,texture);
	    this.setName(name);
	}
}
