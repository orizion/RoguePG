package ch.games.roguepg.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RoguePG extends Game {
    private SpriteBatch batch;
    private Texture grassTexture, dirtTexture;;
    private TextureRegion grass, dirt;

    @Override
    public void create () {
        batch = new SpriteBatch();
        grassTexture = new Texture("grass.png");
        grass = new TextureRegion(grassTexture, 0, 0, 64, 64);
        dirtTexture = new Texture("dirt.png");
        dirt = new TextureRegion(dirtTexture, 0, 0, 64, 64);
        this.setScreen(new GameScreen(this));
    }
    
    public TextureRegion getGrass(){
        return grass;
    }
    
    public TextureRegion getImpassable() {
        return dirt;
    }
    
    public SpriteBatch getBatch(){
        return batch;
    }
    @Override
    public void render () {
        super.render();
    }
}