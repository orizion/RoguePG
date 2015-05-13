package ch.games.roguepg.game;                                                                                                             

import ch.games.roguepg.tools.RogueMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.Random;

public class Character extends Actor{
    private final Texture sprite;
    private final int speed;
    public Character(){
        sprite =  new Texture("player.gif");
        setBounds(RogueMap.tileIndices.length*64/2, RogueMap.tileIndices[0].length*64/2, sprite.getWidth(), sprite.getHeight());  
        Random rnd = new Random();
        while (RogueMap.tileIndices[RogueMap.coordToIndex(getX())][RogueMap.coordToIndex(getY())] == 0){
           setX(rnd.nextFloat()*(64*64));
           setY(rnd.nextFloat()*(64*64));
        }

        speed = 500;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(sprite, getX(), getY());
    }

    @Override
    protected void positionChanged(){
        /* Make camera follow player */
        RoguePG.camera.position.set(getX() + getHeight() / 2, getY() + getWidth() / 2, 0);
    }

    @Override
    public void act(float delta) {
        float oldX = getX();
        float oldY = getY();
        
        /* Capture Input for movement */
        //TODO: convert to actions
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)
            | Gdx.input.isKeyPressed(Input.Keys.A)) {
            setX(getX() - speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)
            | Gdx.input.isKeyPressed(Input.Keys.D)) {
            setX(getX() + speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)
           | Gdx.input.isKeyPressed(Input.Keys.W)){
            setY(getY() + speed * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)
           | Gdx.input.isKeyPressed(Input.Keys.S)){
            setY(getY() - speed * Gdx.graphics.getDeltaTime());
        }
        if(isCollided()){
            setX(oldX);
            setY(oldY);
        }
    }   
        
    public boolean isCollided(){
        /* Check only positions around player for collision */
        /*future: Check list of impassable (eg. is 1, 2, 54 or 89) / have second tileIndices be generated based on that list.*/
        //check for direction, so only one coordinate gets reset to oldX/Y --> movement along walls
        float x = getX(); 
        float y = getY();
     
       if(RogueMap.tileIndices[RogueMap.coordToIndex(x+20)][RogueMap.coordToIndex(y+10)] == 0){ //bottom left corner
           return true;
       }
       else if(RogueMap.tileIndices[RogueMap.coordToIndex(x+40)][RogueMap.coordToIndex(y+10)] == 0){
           return true;
       }      
       else if(RogueMap.tileIndices[RogueMap.coordToIndex(x+20)][RogueMap.coordToIndex(y+40)] == 0){
           return true;
       }
        else if(RogueMap.tileIndices[RogueMap.coordToIndex(x+40)][RogueMap.coordToIndex(y+40)] == 0){
           return true;
       }
       else{ return false; }
    }
}                    
