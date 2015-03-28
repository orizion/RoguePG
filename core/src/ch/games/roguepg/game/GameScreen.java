package ch.games.roguepg.game;                                                                                                                                

import ch.games.roguepg.tools.RogueMapRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

import ch.games.roguepg.tools.RogueMap;

/* Entry point after DesktopLauncher */
public class GameScreen implements Screen {

    /* initialise game object and set active screen (see constructor) */
    final RoguePG roguePG;
    
    Character player;
    Stage world;
    OrthographicCamera camera;
    RogueMap rogueMap;
    RogueMapRenderer renderer;
    
    public GameScreen(final RoguePG game) {
        
        roguePG = game;
        
        player = new Character(512/2, 512/2);
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 512);
        
        rogueMap = new RogueMap(roguePG, 64, 512, 512);
        
        renderer = new RogueMapRenderer(rogueMap, roguePG);
        renderer.setView(camera);
        
        world = new Stage();
        world.addActor(player);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        
        roguePG.getBatch().begin();
       
        world.act();
        
        /* Make camera follow player */
        camera.position.set(player.getX() + player.getHeight() / 2, player.getY() + player.getWidth() / 2, 0); 
        camera.update();
        
        roguePG.getBatch().setProjectionMatrix(camera.combined);
        
        renderer.render();

        //User
        roguePG.getBatch().draw(player.getSprite(), player.getX(), player.getY());

        roguePG.getBatch().end();
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
