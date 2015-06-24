package ch.games.roguepg.game;

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
import java.util.Random;

public class Character extends Actor {

    private final Texture sprite;
    private final int speed;
    private final OrthographicCamera camera;
    private final Body body;

    public Character() {
        sprite = new Texture("player.gif");
        speed = 50;
        camera = new OrthographicCamera(RoguePG.V_WIDTH, RoguePG.V_HEIGHT);
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.linearDamping = 10f;
        bDef.position.set(RogueMap.tileIndices.length / 2, RogueMap.tileIndices[0].length / 2);
        Random rnd = new Random();
        while (RogueMap.tileIndices[(int) bDef.position.x][(int) bDef.position.y] == 0) {
            bDef.position.set(
                rnd.nextFloat() * (RogueMap.tileIndices.length),
                rnd.nextFloat() * (RogueMap.tileIndices[0].length)
            );
        }
        body = RoguePG.world.createBody(bDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.25f, 0.25f);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        body.createFixture(fDef);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
            sprite,
            (body.getPosition().x - 0.5f) * RoguePG.PPM,
            (body.getPosition().y - 0.5f) * RoguePG.PPM
        );
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
        camera.position.set(body.getPosition().x * RoguePG.PPM, body.getPosition().y * RoguePG.PPM, 0);
    }

    OrthographicCamera getCamera() {
        return camera;
    }
}
