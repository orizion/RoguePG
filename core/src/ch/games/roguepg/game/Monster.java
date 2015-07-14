package ch.games.roguepg.game;

import ch.games.roguepg.tools.SteeringAgent;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Monster extends Entity {
    private final SteeringAgent steeringAgent;

    public Monster() {
        spriteSheet = new Texture("enemy.png");
        frames = TextureRegion.split(spriteSheet, 64, 64)[0];
        animation = new Animation(0.25f, frames);
        
        steeringAgent = new SteeringAgent(body);
    }
    

    @Override
    public void act(float delta) {
        SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());
        if (steeringAgent.getSteeringBehavior() != null) {
            // Calculate steering acceleration
            steeringAgent.getSteeringBehavior().calculateSteering(steeringOutput);

            /* Check for running against walls here */
            
            // Apply steering acceleration to move this agent
            steeringAgent.applySteering(steeringOutput, delta);
        }
    }

    SteeringAgent getSteeringAgent() {
        return steeringAgent;
    }
}
