package ch.games.roguepg.tools;

import ch.games.roguepg.game.Monster;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.utils.Collision;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;

public class Box2dRaycastCollisionDetector implements RaycastCollisionDetector<Vector2> {
    
    World world;
    Box2dRaycastCallback callback;
    Monster owner;
  
    public Box2dRaycastCollisionDetector(World world, Monster owner) {
        this(world, new Box2dRaycastCallback(owner));
    }

    public Box2dRaycastCollisionDetector(World world, Box2dRaycastCallback callback) {
        this.world = world;
        this.callback = callback;
    }

    @Override
    public boolean collides(Ray<Vector2> ray) {
        return findCollision(null, ray);
    }

    @Override
    public boolean findCollision(Collision<Vector2> outputCollision, Ray<Vector2> inputRay) {
        callback.collided = false;
        if (!inputRay.start.epsilonEquals(inputRay.end, MathUtils.FLOAT_ROUNDING_ERROR)) {
            callback.outputCollision = outputCollision;
            world.rayCast(callback, inputRay.start, inputRay.end);
        }
        return callback.collided;
    }

    public static class Box2dRaycastCallback implements RayCastCallback {

        public Collision<Vector2> outputCollision;
        public boolean collided;
        private final Monster owner;

        public Box2dRaycastCallback(Monster owner) {
            this.owner = owner;
        }

        @Override
        public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
            if (outputCollision != null) {
                outputCollision.set(point, normal);
            }
            if (fixture.getBody().getUserData() == "player") {
                /* Send a message (int 1) to the owner. */
                //Later have an enum defining different messages.
                MessageManager.getInstance().dispatchMessage(null, owner.getStateMachine(), 1);
            }
            collided = true;
            return fraction;
        }
    }
}
