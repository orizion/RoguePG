package ch.games.roguepg.tools;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;

import ch.games.roguepg.game.RoguePG;
import java.util.ArrayList;
import java.util.Random;

public class RogueMap extends Map {
    private final RoguePG game;
    public final int TILE_SIZE;
    /* Contains indices of Tile instances according to position in map */
    private final int[][] tileIndices; 
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
        tileIndices = generateMap(mapXSize, mapYSize);

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
    

    public int[][] generateMap(int mapXSize,int mapYSize) {
         /* All array elements are initially zero, which equates to the standard tile(dirt).
        Generate the map by setting zeroes to other tile numbers. */
        int[][] tileIndices = new int[mapXSize][mapYSize];
        ArrayList<Room> roomArray = new ArrayList<Room>();
        Random random = new Random();

        /* Rooms */
        OUTER:
        for(int tries = 100; tries > 0; tries--){
            Room room = new Room(mapXSize, mapYSize);

            /* Check if this room would trigger an out of bounds exception. If so, skip this iteration. */
            if(room.getX() + room.getWidth() > mapXSize | room.getY() + room.getHeight() > mapYSize){
                continue;
            }

            /* Check if this room would overlap another room and skip this iteration if so. */                                
            //should also check for directly adjacent, but zhis would cause overflow. Exception?
            for(int i = 0; i < room.getWidth(); i++){
                for(int j = 0; j < room.getHeight(); j++){
                    if(tileIndices[room.getX()+i][room.getY()+j] != 0){
                        continue OUTER;
                    }
                }
            }

            /* Modify tileIndices */
            for(int i = 0; i < room.getWidth(); i++){
                for(int j = 0; j < room.getHeight(); j++){
                    tileIndices[room.getX() + i][room.getY() + j] = 1;
                }
            }
            roomArray.add(room);
        }
        /*Connections*/
        for(int i = 0; i<10; i++){
        int startRoom = random.nextInt(roomArray.size()-1 - 0) + 0;
        int endRoom = random.nextInt(roomArray.size()-1 - 0) + 0;
        int startX = random.nextInt(roomArray.get(startRoom).getX()+roomArray.get(startRoom).getWidth());
        int startY = random.nextInt(roomArray.get(startRoom).getY()+roomArray.get(startRoom).getHeight());
        int endX = random.nextInt(roomArray.get(endRoom).getX()+roomArray.get(endRoom).getWidth());
        int endY = random.nextInt(roomArray.get(endRoom).getY()+roomArray.get(endRoom).getHeight());
        /* Randomly choose between x and y axis, 50:50 chance */
        boolean moveInX = (random.nextDouble() >= 0.5f);

        while(true){
            tileIndices[startX][startY] = 1;
            if(startX == endX && startY == endY){
                break;
            }
            if(moveInX){
                if (startX < endX) startX++;
                else if (startX > endX) startX--;
            } else {
                if (startY < endY) startY++;
                else if (startY > endY) startY--;
               }
            if(random.nextDouble()<0.1f){moveInX = !moveInX; }
             }
        }
        return tileIndices;


    }	
}
