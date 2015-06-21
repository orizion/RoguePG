package ch.games.roguepg.game;

import java.util.Random;

import ch.games.roguepg.tools.RogueMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 
 * @author Pascal, Stefan Defines the Player class
 */
public class Character extends Actor {

    private final Texture sprite;
    // Could be changed by ingame items or conditions -> removed final
    private int speed;
    private final OrthographicCamera camera;
    private final Body body;
    private final BodyDef bDef;

    public Character() {
        sprite = new Texture("player.gif");
        speed = 50;
        camera = new OrthographicCamera(RoguePGGame.V_WIDTH, RoguePGGame.V_HEIGHT);

        // Box2D definitions
        bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.linearDamping = 10f;

        // Only for testing, will be removed later
        setPosition(RogueMap.tileIndices.length / 2, RogueMap.tileIndices[0].length / 2);

        // Testing different locations for the player until a passable area is found
        Random rnd = new Random();
        while (RogueMap.tileIndices[(int) bDef.position.x][(int) bDef.position.y] == 0) {
            setPosition(rnd.nextFloat() * RogueMap.tileIndices.length, rnd.nextFloat() * RogueMap.tileIndices[0].length);
        }
        // Box2D definitions
        body = RoguePGGame.world.createBody(bDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.25f, 0.25f);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        body.createFixture(fDef);
    }

    /**
     * Positions the player in the Map
     * 
     * @param x The new x coordinate of the player
     * @param y The new y coordinate of the player
     */
    public void setPosition(float x, float y) {
        bDef.position.set(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite,
        // why -0.5 ?
                (body.getPosition().x - 0.5f) * RoguePGGame.PPM, (body.getPosition().y - 0.5f) * RoguePGGame.PPM);
    }

    @Override
    public void act(float delta) {
        /* Capture Input for movement */
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) | Gdx.input.isKeyPressed(Input.Keys.A)) {
            body.applyForce(new Vector2(-speed, 0), body.getLocalCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) | Gdx.input.isKeyPressed(Input.Keys.D)) {
            body.applyForce(new Vector2(speed, 0), body.getLocalCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) | Gdx.input.isKeyPressed(Input.Keys.W)) {
            body.applyForce(new Vector2(0, speed), body.getLocalCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) | Gdx.input.isKeyPressed(Input.Keys.S)) {
            body.applyForce(new Vector2(0, -speed), body.getLocalCenter(), true);
        }

        /* Make camera follow player */
        camera.position.set(body.getPosition().x * RoguePGGame.PPM, body.getPosition().y * RoguePGGame.PPM, 0);
    }

    /**
     * 
     * @return the camera of the player
     */
    OrthographicCamera getCamera() {
        return camera;
    }
}
