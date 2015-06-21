package ch.games.roguepg.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ch.games.roguepg.game.RoguePGGame;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = RoguePGGame.TITLE;
        config.width= RoguePGGame.V_WIDTH; 
        config.height = RoguePGGame.V_HEIGHT; 
        new LwjglApplication(new RoguePGGame(), config);
    }
}