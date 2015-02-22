package ch.games.roguepg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Character extends Actor{
    Texture sprite;
    Rectangle bounds;
    int speed;

    public Character(float x, float y){
        sprite = new Texture("player.gif");
        this.setBounds(x, y, 64, 64);
        bounds = new Rectangle();
        bounds.x = this.getX();
        bounds.y = this.getY();
        bounds.width = this.getWidth();
        bounds.height = this.getHeight();
        
        speed = 200;
    }
    
    public Texture getSprite() {
        return sprite;
    }

    @Override
    public void act(float delta) {
        /* Capture Input for movement */
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
