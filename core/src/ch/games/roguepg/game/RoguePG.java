package ch.games.roguepg.game;

import ch.games.roguepg.tools.RogueMap;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.behaviors.Flee;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class RoguePG extends Game {

    public static final String TITLE = "RoguePG";
    public static final int V_WIDTH = 1920;
    public static final int V_HEIGHT = 1080;
    public static final float PPM = 64;
    public static final World world = new World(new Vector2(0, 0), true);

    private RogueMap rogueMap;
    private Player player;
    private Monster monster, monster1, monster2, monster3;
    private SpriteBatch batch;
    private ScreenViewport viewport;
    private Stage stage;

    @Override
    public void create() {
        Box2D.init();
        Gdx.input.setCursorCatched(true);
        rogueMap = new RogueMap(32, 32);
        player = new Player();
        monster = new Monster();
        monster1 = new Monster();
        monster2 = new Monster();
        monster3 = new Monster();
        monster.getSteeringAgent().setSteeringBehavior(new Seek(monster.getSteeringAgent(), monster1.getSteeringAgent()));
        monster1.getSteeringAgent().setSteeringBehavior(new Flee(monster1.getSteeringAgent(), monster.getSteeringAgent()));
        monster3.getSteeringAgent().setSteeringBehavior(new Seek(monster3.getSteeringAgent(), player.getSteeringAgent()));
        batch = new SpriteBatch();
        batch.setProjectionMatrix(player.getCamera().combined);
        viewport = new ScreenViewport(player.getCamera());
        stage = new Stage(viewport, batch);
        stage.addActor(rogueMap);
        stage.addActor(player);
        stage.addActor(monster);
        stage.addActor(monster1);
        stage.addActor(monster2);
        stage.addActor(monster3);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.141f, 0.182f, 0.205f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        world.step(1 / 60f, 6, 2);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}
