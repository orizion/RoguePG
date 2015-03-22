package ch.games.roguepg.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RoguePG extends Game {
    private SpriteBatch batch;
    Texture img, grassTexture;
    private TextureRegion grass;

    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("player.gif");
        grassTexture = new Texture("grass.png");
        grass = new TextureRegion(grassTexture, 0, 0, 64, 64);
        this.setScreen(new GameScreen(this));
    }
    
    public TextureRegion getGrass(){
        return grass;
    }
    public SpriteBatch getBatch(){
        return batch;
    }
    @Override
    public void render () {
        super.render();
    }
}