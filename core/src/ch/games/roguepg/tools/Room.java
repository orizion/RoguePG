package ch.games.roguepg.tools;

import java.util.Random;

public class Room {
    private final int x, y, width, height;
    Random random = new Random();
    private boolean connected;

    public Room(int mapXSize, int mapYSize){
        x = random.nextInt(mapXSize-1 - 0) + 0;
        y = random.nextInt(mapYSize-1 - 0) + 0;

        width = random.nextInt(5 - 2) + 2;
        height = random.nextInt(5 - 2) + 2;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

 
    public boolean isConnected() {
        return connected;
    }


    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
