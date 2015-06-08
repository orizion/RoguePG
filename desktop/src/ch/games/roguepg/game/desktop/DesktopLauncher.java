package ch.games.roguepg.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ch.games.roguepg.game.RoguePG;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = RoguePG.TITLE;
        config.width= RoguePG.V_WIDTH; 
        config.height = RoguePG.V_HEIGHT; 
        new LwjglApplication(new RoguePG(), config);
    }
}