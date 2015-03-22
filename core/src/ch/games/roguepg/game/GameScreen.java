package ch.games.roguepg.game;                                                                                                                                

import ch.games.roguepg.tools.RogueMapRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import ch.games.roguepg.tools.RogueMap;

/* Entry point after DesktopLauncher */
public class GameScreen implements Screen {

    /* initialise game object and set active screen (see constructor) */
    final RoguePG roguePG;
    
    Character player;
    Rectangle player2;
    Stage world;
    OrthographicCamera camera;
    RogueMap rogueMap;
    RogueMapRenderer renderer;
    
    public GameScreen(final RoguePG game) {
        
        roguePG = game;
        
        player = new Character(512/2, 512/2);
        player2 = new Rectangle();
        player2.x = 100;
        player2.y = 100;
        player2.height = 64;
        player2.width = 64;
        
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

        float oldX = player.getX();
        float oldY = player.getY();
       
        world.act();
        
        if(player2.overlaps(player.bounds)){
            player.setX(oldX);
            player.setY(oldY);
        }
        
        
        /* Make camera follow player */
        camera.position.set(player.getX() + player.getHeight() / 2, player.getY() + player.getWidth() / 2, 0); 
        camera.update();
        
        roguePG.getBatch().setProjectionMatrix(camera.combined);
        
        renderer.render();

        //User
        roguePG.getBatch().draw(player.getSprite(), player.getX(), player.getY());
        //Use other Texture too show movement of player sprite, preferably change texture to distinguish from player
        roguePG.getBatch().draw(roguePG.img, 100 , 100);

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
