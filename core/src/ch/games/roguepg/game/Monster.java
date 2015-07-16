package ch.games.roguepg.game;

import ch.games.roguepg.tools.SteeringAgent;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Monster extends Entity {
    private final SteeringAgent steeringAgent;
    private StateMachine stateMachine;
    private Player target;

    public Monster(Player target) {
        spriteSheet = new Texture("enemy.png");
        frames = TextureRegion.split(spriteSheet, 64, 64)[0];
        animation = new Animation(0.25f, frames);
        this.target = target;
        steeringAgent = new SteeringAgent(body);
        /* First set State to NONE because otherwise PATROL's enter function won't execute */
        stateMachine = new DefaultStateMachine(this, MonsterState.NONE);
        MessageManager.getInstance().addListener(stateMachine, 1);
        stateMachine.changeState(MonsterState.PATROL);
    }
    
    @Override
    public void act(float delta) {
        stateMachine.update();
        SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());
        if (steeringAgent.getSteeringBehavior() != null) {
            // Calculate steering acceleration
            steeringAgent.getSteeringBehavior().calculateSteering(steeringOutput);

            /* Check for running against walls here */
            
            // Apply steering acceleration to move this agent
            steeringAgent.applySteering(steeringOutput, delta);
        }
    }

    public SteeringAgent getSteeringAgent() {
        return steeringAgent;
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }
    public Player getTarget(){
        return target;
    }
}
