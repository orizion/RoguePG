package ch.games.roguepg.game;                                                                                                                                

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

/* Entry point after DesktopLauncher */
public class GameScreen implements Screen {

    /*initialise game object and set active screen (see constructor)*/
    final RoguePG rpg;
    Rectangle player;
    OrthographicCamera camera;
    
    public GameScreen(final RoguePG game) {
        
        rpg = game;
        
        player = new Rectangle();
        player.height = 64;
        player.width = 64;
        player.x = 0;
        player.y = 0;
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 512);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        
        rpg.batch.begin();
                
        /* Make camera follow player */
        camera.position.set(player.x + 32, player.y + 32, 0); 
        camera.update();
        
        rpg.batch.setProjectionMatrix(camera.combined);
        rpg.batch.draw(rpg.img, player.x , player.y);

        rpg.batch.end();

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
