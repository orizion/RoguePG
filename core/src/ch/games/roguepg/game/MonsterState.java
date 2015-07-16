package ch.games.roguepg.game;

import static ch.games.roguepg.game.RoguePG.world;
import ch.games.roguepg.tools.Box2dRaycastCollisionDetector;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.PrioritySteering;
import com.badlogic.gdx.ai.steer.behaviors.RaycastObstacleAvoidance;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.ai.steer.utils.rays.CentralRayWithWhiskersConfiguration;
import com.badlogic.gdx.math.Vector2;

public enum MonsterState implements State<Monster> {
    
    NONE(){
        @Override
        public void enter(Monster entity) {
        }

        @Override
        public void update(Monster entity) {
        }

        @Override
        public void exit(Monster entity) {
        }

        @Override
        public boolean onMessage(Monster entity, Telegram telegram) {
            return true;
        }
    },

    PATROL() {
        @Override
        public void enter(Monster monster) {
            RaycastObstacleAvoidance raycastObstacleAvoidanceSB = new RaycastObstacleAvoidance(monster.getSteeringAgent())
                .setRayConfiguration(new CentralRayWithWhiskersConfiguration(monster.getSteeringAgent(), 100f, 40f, (float) Math.toRadians(35)))
                .setRaycastCollisionDetector(new Box2dRaycastCollisionDetector(world, monster));

            Wander wanderSB = new Wander<Vector2>(monster.getSteeringAgent())
                .setWanderOffset(0.1f)
                .setWanderRadius(0.1f)
                .setWanderRate(0.1f);          
           
            SteeringBehavior steeringBehavior = new PrioritySteering<Vector2>(monster.getSteeringAgent(), 0.0001f)
                .add(raycastObstacleAvoidanceSB)
                .add(wanderSB);
            
            monster.getSteeringAgent().setSteeringBehavior(steeringBehavior);
        }

        @Override
        public void update(Monster monster) {

        }

        @Override
        public void exit(Monster entity) {}

        @Override
        public boolean onMessage(Monster monster, Telegram telegram) {
            monster.getStateMachine().changeState(SEARCHANDDESTROY);
            return true;
        }
    },

    SEARCHANDDESTROY() {
        @Override
        public void enter(Monster monster) {
            System.out.println("Seeking Player");
            
            RaycastObstacleAvoidance raycastObstacleAvoidanceSB = new RaycastObstacleAvoidance(monster.getSteeringAgent())
                .setRayConfiguration(new CentralRayWithWhiskersConfiguration(monster.getSteeringAgent(), 100f, 40f, (float) Math.toRadians(35)))
                .setRaycastCollisionDetector(new Box2dRaycastCollisionDetector(world, monster));
            
            Seek seekSB = new Seek(monster.getSteeringAgent(), monster.getTarget().getSteeringAgent());
            
            SteeringBehavior steeringBehavior = new PrioritySteering<Vector2>(monster.getSteeringAgent(), 0.0001f)
                .add(raycastObstacleAvoidanceSB)
                .add(seekSB);

            monster.getSteeringAgent().setSteeringBehavior(seekSB);
        }

        @Override
        public void update(Monster entity) {
        }

        @Override
        public void exit(Monster entity) {
        }

        @Override
        public boolean onMessage(Monster entity, Telegram telegram) {
            return true;
       }
    },
}
