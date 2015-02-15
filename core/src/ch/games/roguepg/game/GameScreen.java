package ch.games.roguepg.game;                                                                                                                                

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;

/* Entry point after DesktopLauncher */
public class GameScreen implements Screen {

    /*initialise game object and set active screen (see constructor)*/
    final RoguePG rpg;

    public GameScreen(final RoguePG game) {
                
        rpg = game;

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

            
        rpg.batch.begin();        
        rpg.batch.draw(rpg.img, 0, 0);
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
