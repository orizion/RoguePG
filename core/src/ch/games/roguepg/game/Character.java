package ch.games.roguepg.game;                                                                                                             

import ch.games.roguepg.tools.RogueMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Character extends Actor{
    private final Texture sprite;
    private final int speed;
    public Character(){
        sprite =  new Texture("player.gif");
        setBounds(RogueMap.tileIndices.length*64/2, RogueMap.tileIndices[0].length*64/2, sprite.getWidth(), sprite.getHeight());    
        speed = 500;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(sprite, getX(), getY());
    }

    @Override
    protected void positionChanged(){
    }

    @Override
    public void act(float delta) {
        /* Capture Input for movement */
        //TODO: convert to actions
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)
            | Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.setX(this.getX() - speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)
            | Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.setX(this.getX() + speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)
           | Gdx.input.isKeyPressed(Input.Keys.W)){
            this.setY(this.getY() + speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)
           | Gdx.input.isKeyPressed(Input.Keys.S)){
            this.setY(this.getY() - speed * Gdx.graphics.getDeltaTime());
        }
    }
}
