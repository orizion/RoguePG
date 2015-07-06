package ch.games.roguepg.tools;

import ch.games.roguepg.game.RoguePG;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.ChainShape;

public class RogueMap extends Actor {
    /* Contains indices of Tile instances according to position in map */
    public static int[][] tileIndices;
    private final TextureAtlas atlas;
    private final ArrayList<Body> bodies;

    public RogueMap(int mapXSize, int mapYSize) {
        atlas = new TextureAtlas("tiles.atlas");
        bodies = new ArrayList<Body>();
        generateMap(mapXSize, mapYSize);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (Body body : bodies) {
            batch.draw(
                atlas.findRegion("dirt"),
                body.getPosition().x * RoguePG.PPM,
                body.getPosition().y * RoguePG.PPM
            );
        }
    }
    private void generateMap(int mapXSize, int mapYSize) {
        tileIndices = new int[mapXSize][mapYSize];
        createConnections(createRooms(mapXSize, mapYSize));
        generateMapBodies();
    }

    public ArrayList<Rectangle> createRooms(int mapXSize, int mapYSize) {
        /*
         * All array elements are initially zero, which equates to the standard tile(dirt). Generate
         * the map by setting zeroes to other tile numbers.
         */

        /* Rooms */
        Random random = new Random();
        ArrayList<Rectangle> roomArray = new ArrayList<Rectangle>();

        OUTER:
        for (int tries = 100; tries > 0; tries--) {
            Rectangle room = new Rectangle(1 + random.nextInt(mapXSize-1), 1 + random.nextInt(mapYSize-1), random.nextInt(5 - 2) + 2, random.nextInt(5 - 2) + 2);
            /*
             * Check if this room would touch or go over the map boundaries.
             * If so, skip this iteration.
             */
            if (room.x + room.width > mapXSize - 2 || room.y + room.height > mapYSize - 2) {
                continue;
            }

            /* Check if this room would overlap another room and skip this iteration if so. */
            // should also check for directly adjacent, but this would cause overflow. Exception?
            for (int i = 0; i < room.getWidth(); i++) {
                for (int j = 0; j < room.getHeight(); j++) {
                    if (tileIndices[((int) room.x + i)][((int) room.y + j)] != 0) {
                        continue OUTER;
                    }
                }
            }

            /* Modify tileIndices */
            for (int i = 0; i < room.getWidth(); i++) {
                for (int j = 0; j < room.getHeight(); j++) {
                    tileIndices[((int) room.x + i)][((int) room.y + j)] = 1;
                }
            }
            roomArray.add(room);
        }
        return roomArray;
    }
    public void createConnections(ArrayList<Rectangle> roomArray){
        /* Connections */
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            
            // from + rndGenerator.nextInt(to - from + 1)
            Rectangle startRoom = roomArray.get(random.nextInt(roomArray.size() - 1));
            Rectangle endRoom = roomArray.get(random.nextInt(roomArray.size() - 1));

            int startX = (int) (startRoom.width / 2 + startRoom.x);
            int startY = (int) (startRoom.height / 2 + startRoom.y);
            int endX = (int) (endRoom.width / 2 + endRoom.x);
            int endY = (int) (endRoom.height / 2 + endRoom.y);
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

    private void generateMapBodies() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        ChainShape shape = new ChainShape();
        /* Set vertices of the box in counter-clockwise order. */
        float[] vertices = {0, 0, 1, 0, 1, 1, 0, 1};
        shape.createLoop(vertices);

        for (int i = 0; i < RogueMap.tileIndices.length; i++) {
            for (int j = 0; j < RogueMap.tileIndices[0].length; j++) {
                if (tileIndices[i][j] == 0) {
                    bodyDef.position.set(i + 0.5f, j + 0.5f);
                    Body body = RoguePG.world.createBody(bodyDef);
                    body.createFixture(shape, 1);
                    bodies.add(body);
                }
            }
        }
    }
}
