package ch.games.roguepg.tools;

import ch.games.roguepg.game.RoguePG;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.ArrayList;
import java.util.Random;

public class RogueMap extends Actor {
    /* Contains indices of Tile instances according to position in map */
    public static int[][] tileIndices;
    TextureAtlas atlas;

    public RogueMap(int mapXSize, int mapYSize) {
        atlas = new TextureAtlas("tiles.atlas");
        generateMap(mapXSize, mapYSize);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < RogueMap.tileIndices.length; i++) {
            for (int j = 0; j < RogueMap.tileIndices[0].length; j++) {
                int current = RogueMap.tileIndices[i][j];
                batch.draw(atlas.getRegions().get(current),
                    i*RoguePG.PPM,
                    j*RoguePG.PPM,
                    RoguePG.PPM,
                    RoguePG.PPM
                );
            }
        }
    }

    public void generateMap(int mapXSize, int mapYSize) {
        /*
         * All array elements are initially zero, which equates to the standard tile(dirt). Generate
         * the map by setting zeroes to other tile numbers.
         */
        tileIndices = new int[mapXSize][mapYSize];
        ArrayList<Room> roomArray = new ArrayList<Room>();
        Random random = new Random();

        /* Rooms */
        OUTER:
        for (int tries = 100; tries > 0; tries--) {
            Room room = new Room(mapXSize, mapYSize);

            /*
             * Check if this room would touch or go over the map boundaries.
             * If so, skip this iteration.
             */
            if (room.getX() + room.getWidth() > mapXSize-2 | room.getY() + room.getHeight() > mapYSize-2 | room.getX() == 0 | room.getY()== 0) {
                continue;
            }

            /* Check if this room would overlap another room and skip this iteration if so. */
            // should also check for directly adjacent, but this would cause overflow. Exception?
            for (int i = 0; i < room.getWidth(); i++) {
                for (int j = 0; j < room.getHeight(); j++) {
                    if (tileIndices[room.getX() + i][room.getY() + j] != 0) {
                        continue OUTER;
                    }
                }
            }

            /* Modify tileIndices */
            for (int i = 0; i < room.getWidth(); i++) {
                for (int j = 0; j < room.getHeight(); j++) {
                    tileIndices[room.getX() + i][room.getY() + j] = 1;
                }
            }
            roomArray.add(room);
        }
        /* Connections */

        for (int i = 0; i < 10; i++) {
            // from + rndGenerator.nextInt(to - from + 1)
            int startRoom = 0 + random.nextInt((roomArray.size() - 1) - 0);
            int endRoom = 0 + random.nextInt((roomArray.size() - 1) - 0);
            int startX = roomArray.get(startRoom).getX() + random.nextInt(roomArray.get(startRoom).getWidth() + 1);
            int startY = roomArray.get(startRoom).getY() + random.nextInt(roomArray.get(startRoom).getHeight() + 1);
            int endX = roomArray.get(endRoom).getX() + random.nextInt(roomArray.get(endRoom).getWidth() + 1);
            int endY = roomArray.get(endRoom).getY() + random.nextInt(roomArray.get(endRoom).getHeight() + 1);
            // Randomly choose between x and y axis, 50:50 chance
            boolean moveInX = (random.nextDouble() >= 0.5f);

            while (true) {
                tileIndices[startX][startY] = 1;
                if (startX == endX && startY == endY) {
                    break;
                }
                if (moveInX) {
                    if (startX < endX) {
                        startX++;
                    } else if (startX > endX) {
                        startX--;
                    }
                } else {
                    if (startY < endY) {
                        startY++;
                    } else if (startY > endY) {
                        startY--;
                    }
                }
                if (random.nextDouble() < 0.1f) {
                    moveInX = !moveInX;
                }
            }
        }
    }
}
