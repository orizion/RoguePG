package ch.games.roguepg.tools;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class SteeringAgent implements Steerable<Vector2> {

    private final Body body;
    private SteeringBehavior<Vector2> steeringBehavior;
    private float maxLinearSpeed = 5f;
    private float maxLinearAcceleration = 5f;

    public SteeringAgent(Body body) {
        this.body = body;
        //Default steering. Change with setSteeringBehavior
        steeringBehavior = new Wander<Vector2>(this)
            .setWanderOffset(0.1f)
            .setWanderRadius(0.1f)
            .setWanderRate(0.1f);
    }

    public void applySteering(SteeringAcceleration<Vector2> steering, float delta) {
        // Update linear velocity and calculate impulse to apply to position.
        Vector2 linearVelocity = body.getLinearVelocity();

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
    }

    public void setSteeringBehavior(SteeringBehavior<Vector2> steeringBehavior) {
        this.steeringBehavior = steeringBehavior;
    }

    public SteeringBehavior getSteeringBehavior() {
        return steeringBehavior;
    }

    // Steerable and Limiter method implementations
    @Override
    public Vector2 getPosition() {
        return body.getPosition();
    }

    @Override
    public Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
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
