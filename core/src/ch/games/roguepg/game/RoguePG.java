package ch.games.roguepg.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RoguePG extends Game {
    SpriteBatch batch;
    Texture img;

    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("placeholder.gif");
        this.setScreen(new GameScreen(this));
    }

    @Override
    public void render () {
        super.render();
    }
}
