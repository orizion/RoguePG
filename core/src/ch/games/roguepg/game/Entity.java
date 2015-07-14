package ch.games.roguepg.game;

import java.util.Random;

import ch.games.roguepg.tools.RogueMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor {

    public Body body;
    public int speed;
    public Animation animation;
    public float frameTime;
    public Texture spriteSheet;
    public TextureRegion[] frames;

    public Entity() {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.linearDamping = 10f;

        // This belonngs in a Method in the RogueMap class
        Random rnd = new Random();
        // Still placing entities in non passable territory
        while (RogueMap.tileIndices[(int) bDef.position.x][(int) bDef.position.y] == 0) {
            bDef.position.set(rnd.nextInt(RogueMap.tileIndices.length), rnd.nextInt(RogueMap.tileIndices[0].length));
        }
        RogueMap.tileIndices[(int) bDef.position.x][(int) bDef.position.y] = 2;
        System.out.println("Index Type: " + RogueMap.tileIndices[(int) bDef.position.x][(int) bDef.position.y]);
        body = RoguePG.world.createBody(bDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.25f, 0.25f);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        body.createFixture(fDef);
        speed = 50;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        frameTime += Gdx.graphics.getDeltaTime();
        /*
         * - 0.5 meters because body's position is in local center, while the draw method starts at
         * the bottom left corner.
         */
        batch.draw(animation.getKeyFrame(frameTime, true), (body.getPosition().x - 0.5f) * RoguePG.PPM,
                (body.getPosition().y - 0.5f) * RoguePG.PPM);
    }
}
