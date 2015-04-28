package ch.games.roguepg.tools;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;

/**
 * 
 * @author Stefan
 * Class which holds the Texture and the Type of a tile to identify and render it in the game
 * A Tile object represents a type of tile which is to use for a map, not an individual tile, therefore
 * one should only create one Tile object instance per type of Tile to use 
 */
public class Tile extends MapObject{

    public static enum TileType {
        GRASS,
        DIRT,
        STONE,
        WATER;
    }
    
    public Tile(TileType type,TextureRegion texture, RogueMap map) {
        getProperties().put("type", type);
        getProperties().put("texture", texture);
        map.getLayers().get(0).getObjects().add(this);
    }
    
    public Tile(TileType type,TextureRegion texture,String name, RogueMap map) {
        this(type, texture, map);
        this.setName(name);
        map.getLayers().get(0).getObjects().add(this);
    }
    
    
}