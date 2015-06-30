package ch.games.roguepg.game;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Monster extends Entity implements Steerable<Vector2> {

    private final SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());
    private SteeringBehavior<Vector2> steeringBehavior;
    private final Vector2 position, linearVelocity;
    private float maxLinearSpeed, maxLinearAcceleration;

    public Monster() {
        spriteSheet = new Texture("enemy.png");
        frames = TextureRegion.split(spriteSheet, 64, 64)[0];
        animation = new Animation(0.25f, frames);

        position = new Vector2(body.getPosition().x, body.getPosition().y);
        linearVelocity = new Vector2();
        maxLinearSpeed = speed;
        maxLinearAcceleration = 1f;
        steeringBehavior = new Wander<Vector2>(this)
            .setWanderOffset(90)
            .setWanderRadius(40)
            .setWanderRate(MathUtils.PI / 5)
            .setAlignTolerance(0.001f); //to prevent wobbling
    }

    @Override
    public void act(float delta) {
        if (steeringBehavior != null) {
            // Calculate steering acceleration
            steeringBehavior.calculateSteering(steeringOutput);

            /* Check for running against walls here */
            
            // Apply steering acceleration to move this agent
            applySteering(steeringOutput, delta);
        }
    }

    private void applySteering(SteeringAcceleration<Vector2> steering, float delta) {
        // Update linear velocity and calculate impulse to apply to position.
        this.linearVelocity.mulAdd(steering.linear, delta).limit(this.getMaxLinearSpeed());

        float velocityX = linearVelocity.x;
        float desiredVelocityX = Math.max(velocityX - maxLinearSpeed, Math.min(Math.signum(steering.linear.x) * maxLinearAcceleration, velocityX + maxLinearSpeed));
        desiredVelocityX = Math.min(desiredVelocityX, maxLinearSpeed);
        float velocityChangeX = desiredVelocityX - velocityX;
        float impulseX = body.getMass() * velocityChangeX;

        float velocityY = linearVelocity.y;
        float desiredVelocityY = Math.max(velocityY - maxLinearSpeed, Math.min(Math.signum(steering.linear.y) * maxLinearAcceleration, velocityY + maxLinearSpeed));
        desiredVelocityY = Math.min(desiredVelocityY, maxLinearSpeed);
        float velocityChangeY = desiredVelocityY - velocityY;
        float impulseY = body.getMass() * velocityChangeY;

        body.applyLinearImpulse(impulseX, impulseY, body.getWorldCenter().x, body.getWorldCenter().y, true);

        position.x = body.getPosition().x;
        position.y = body.getPosition().y;
    }

    public void setSteeringBehavior(SteeringBehavior<Vector2> steeringBehavior) {
        this.steeringBehavior = steeringBehavior;
    }

    // Steerable and Limiter method implementations
    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

    @Override
    public Vector2 newVector() {
        return new Vector2();
    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return (float) Math.atan2(-vector.x, vector.y);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        outVector.x = -(float) Math.sin(angle);
        outVector.y = (float) Math.cos(angle);
        return outVector;
    }

    @Override
    public float getMaxLinearSpeed() {
        return maxLinearSpeed;
    }

    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {
        this.maxLinearSpeed = maxLinearSpeed;
    }

    @Override
    public float getMaxLinearAcceleration() {
        return maxLinearAcceleration;
    }

    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {
        this.maxLinearAcceleration = maxLinearAcceleration;
    }

    @Override
    public float getOrientation() {
        return 0;
    }

    @Override
    public float getAngularVelocity() {
        return 0;
    }

    @Override
    public float getBoundingRadius() {
        return 0;
    }

    @Override
    public boolean isTagged() {
        return false;
    }

    @Override
    public void setTagged(boolean tagged) {
    }

    @Override
    public float getMaxAngularSpeed() {
        return 0;
    }

    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {
    }

    @Override
    public float getMaxAngularAcceleration() {
        return 0;
    }

    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {
    }
}
