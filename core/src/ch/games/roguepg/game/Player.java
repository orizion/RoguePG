package ch.games.roguepg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {
    private final OrthographicCamera camera;

    public Player (){
        camera = new OrthographicCamera(RoguePG.V_WIDTH, RoguePG.V_HEIGHT);
        spriteSheet = new Texture("player.gif");
        frames = TextureRegion.split(spriteSheet, 64, 64)[0];
        animation = new Animation(0.25f, frames);
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
