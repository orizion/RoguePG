package ch.games.roguepg.game;

import ch.games.roguepg.tools.RogueMap;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class RoguePG extends Game {
    public static SpriteBatch batch;
    private Character player;
    private Stage world;
    static OrthographicCamera camera;
    private RogueMap rogueMap;
    private ScreenViewport viewport;


    @Override
    public void create () {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        viewport = new ScreenViewport(camera);
        rogueMap = new RogueMap(64.0f, 64, 64);
        player = new Character();
        world = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(world);
        world.addActor(rogueMap);
        world.addActor(player);
        camera.position.set(player.getX() + player.getHeight() / 2, 
                player.getY() + player.getWidth() / 2, 0
        );
    }
    @Override
     public void render() {
        camera.update();
        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        world.act(Gdx.graphics.getDeltaTime());
        world.draw();
    }
}
