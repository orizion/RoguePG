package ch.games.roguepg.game;                                                                                                                                

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

/* Entry point after DesktopLauncher */
public class GameScreen implements Screen {

    /*initialise game object and set active screen (see constructor)*/
    final RoguePG rpg;
    Character player;
    Rectangle player2;
    Stage world;
    OrthographicCamera camera;
    
    public GameScreen(final RoguePG game) {
        
        rpg = game;
        
        player = new Character(512/2, 512/2);
        player2 = new Rectangle();
        player2.x = 100;
        player2.y = 100;
        player2.height = 64;
        player2.width = 64;

        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 512);
        
        world = new Stage();
        world.addActor(player);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        
        rpg.batch.begin();
        
        world.act();

        
        /* Make camera follow player */
        camera.position.set(player.getX() + player.getHeight() / 2, player.getY() + player.getWidth() / 2, 0); 
        camera.update();
        
        rpg.batch.setProjectionMatrix(camera.combined);

        //Get Coordinates of the bottom left Corner of the Camera
        float camCornerX = camera.position.x- 512/2;
        float camCornerY = camera.position.y - 512/2;
        
        //Background, draw the Background in the visible area only
        rpg.batch.draw(rpg.bg, camCornerX, camCornerY, 512, 512,0,0,2,2);
        //User
        rpg.batch.draw(player.getSprite(), player.getX(), player.getY());
        //Use other Texture too show movement of player sprite, preferably change texture to distinguish from player
        rpg.batch.draw(rpg.img, 100 , 100);

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
