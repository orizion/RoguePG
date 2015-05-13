package ch.games.roguepg.game;

import ch.games.roguepg.tools.RogueMap;
import ch.games.roguepg.tools.RogueMapRenderer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class RoguePG extends Game {
    public static SpriteBatch batch;
    public static Texture grassTexture, dirtTexture;;
    public static  TextureRegion grass, dirt;
    private Character player;
    private Stage world;
    static OrthographicCamera camera;
    private RogueMap rogueMap;
    private RogueMapRenderer renderer;   
    private ScreenViewport viewport;


    @Override
    public void create () {
        grassTexture = new Texture("grass.png");//TODO:move into TextureAtlas
        grass = new TextureRegion(grassTexture, 0, 0, 64, 64);
        dirtTexture = new Texture("dirt.png");
        dirt = new TextureRegion(dirtTexture, 0, 0, 64, 64);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        viewport = new ScreenViewport(camera);
        rogueMap = new RogueMap(64, 64, 64);
        renderer = new RogueMapRenderer(rogueMap);
        player = new Character();
        world = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(world);
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
        batch.begin();
        renderer.render();
        batch.end();
        world.draw();
    }
}