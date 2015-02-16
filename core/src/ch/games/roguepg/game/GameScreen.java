package ch.games.roguepg.game;                                                                                                                                

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
        
        //create Placeholder for Player
        player = new Rectangle();
        player.height = 64;
        player.width = 64;
        //Set Player in the Middle of the Screen
        player.x = 512/2;
        player.y = 512/2;
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 512);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        
        rpg.batch.begin();
        
        //Capture Input for movement
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) 
            | Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)
            | Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.x += 200 * Gdx.graphics.getDeltaTime();
        }   
        if (Gdx.input.isKeyPressed(Input.Keys.UP) 
           | Gdx.input.isKeyPressed(Input.Keys.W)){
            player.y += 200 * Gdx.graphics.getDeltaTime();
        } 
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) 
           | Gdx.input.isKeyPressed(Input.Keys.S)){
            player.y -= 200 * Gdx.graphics.getDeltaTime();
        }

                
        /* Make camera follow player */
        camera.position.set(player.x + player.height / 2, player.y + player.width / 2, 0); 
        camera.update();
        
        rpg.batch.setProjectionMatrix(camera.combined);

        //Get Coordinates of the bottom left Corner of the Camera
        float camCornerX = camera.position.x- 512/2;
        float camCornerY = camera.position.y - 512/2;
        
        //Background, draw the Background in the visible area only
        rpg.batch.draw(rpg.bg, camCornerX, camCornerY, 512, 512,0,0,2,2);
        //User
        rpg.batch.draw(rpg.img, player.x , player.y);
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
