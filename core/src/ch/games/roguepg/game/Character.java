package ch.games.roguepg.game;

import ch.games.roguepg.tools.RogueMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.Random;

public class Character extends Actor {

    private final Texture sprite;
    private final int speed;
    private final OrthographicCamera camera;

    public Character() {
        sprite = new Texture("player.gif");
        setBounds(
                RogueMap.tileIndices.length / 2, 
                RogueMap.tileIndices[0].length / 2,
                sprite.getWidth(), 
                sprite.getHeight()
        );
        Random rnd = new Random();
        while (RogueMap.tileIndices[(int) getX()][(int)getY()] == 0) {
            setPosition(
                    rnd.nextFloat() * (RogueMap.tileIndices.length), 
                    rnd.nextFloat() * (RogueMap.tileIndices[0].length)
            );
        }
        speed = 50;
        camera = new OrthographicCamera(1920, 1080);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, getX() * RoguePG.PPM, getY() * RoguePG.PPM);
    }

    @Override
    public void act(float delta) {
        float oldX = getX();
        float oldY = getY();

        /* Capture Input for movement */
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)
                | Gdx.input.isKeyPressed(Input.Keys.A)) {
            setX(getX() - speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                | Gdx.input.isKeyPressed(Input.Keys.D)) {
            setX(getX() + speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)
                | Gdx.input.isKeyPressed(Input.Keys.W)) {
            setY(getY() + speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)
                | Gdx.input.isKeyPressed(Input.Keys.S)) {
            setY(getY() - speed * Gdx.graphics.getDeltaTime());
        }

        /* Make camera follow player */
        camera.position.set(getX() * RoguePG.PPM + getHeight() / 2, getY() * RoguePG.PPM + getWidth() / 2, 0);
    }

    OrthographicCamera getCamera() {
        return camera;
    }
}
