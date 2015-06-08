package ch.games.roguepg.game;

import ch.games.roguepg.tools.RogueMap;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class RoguePG extends Game {
    public static final String TITLE = "RoguePG";
    public static final int V_WIDTH = 1920;
    public static final int V_HEIGHT = 1080;
    public static final float PPM = 64;
    
    private RogueMap rogueMap;
    private Character player;
    private SpriteBatch batch;
    private ScreenViewport viewport;
    private Stage world;

    @Override
    public void create() {
        rogueMap = new RogueMap(64, 64);
        player = new Character();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(player.getCamera().combined);
        viewport = new ScreenViewport(player.getCamera());
        world = new Stage(viewport, batch);
        world.addActor(rogueMap);
        world.addActor(player);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        world.act(Gdx.graphics.getDeltaTime());
        world.draw();
    }
}