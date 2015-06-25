package ch.games.roguepg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Monster extends Entity {
    Vector2 direction;

    public Monster(){
        spriteSheet = new Texture("enemy.png");
        frames = TextureRegion.split(spriteSheet, 64, 64)[0];
        animation = new Animation(0.25f, frames);
        direction = new Vector2(0, 0);       
    }
    
    @Override
    public void act(float delta) {
        body.applyForce(direction, body.getLocalCenter(), true);
    }
}
