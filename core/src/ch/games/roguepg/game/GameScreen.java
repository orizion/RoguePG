package ch.games.roguepg.game;                                                                                                                                

import ch.games.roguepg.tools.RogueMapRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

import ch.games.roguepg.tools.RogueMap;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/* Entry point after DesktopLauncher */
public class GameScreen implements Screen {
    /* initialise game object and set active screen (see constructor) */
    final RoguePG roguePG;
    Character player;
    Stage world;
    OrthographicCamera camera;
    RogueMap rogueMap;
    RogueMapRenderer renderer;
    ScreenViewport viewport;
    
    public GameScreen(final RoguePG game) {
        
        roguePG = game;
                
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        viewport = new ScreenViewport(camera);

        rogueMap = new RogueMap(roguePG, 64, 64, 64);
        /* Set player at center of map */
        player = new Character();

        renderer = new RogueMapRenderer(rogueMap, roguePG);
        renderer.setView(camera);
        
        world = new Stage(viewport, game.getBatch());
        world.addActor(player);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
       
        world.act();
        
        /* Make camera follow player */
        camera.position.set(player.getX() + player.getHeight() / 2, player.getY() + player.getWidth() / 2, 0); 
        camera.update();

        roguePG.getBatch().setProjectionMatrix(camera.combined);
        roguePG.getBatch().begin();
        renderer.render();
        roguePG.getBatch().end();
        world.draw();

    }    

    @Override
    public void show() {
    }    

    @Override
    public void resize(int width, int height) { 
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
