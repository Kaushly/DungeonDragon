package ru.dungeon.dragon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameMap {
    public static final int CELLS_X = 15;
    public static final int CELLS_Y = 15;
    private byte[][] data;

    private TextureRegion grass;
    private TextureRegion road;

    public GameMap(TextureAtlas atlas) {
        this.data = new byte[CELLS_X][CELLS_Y];
        grass = atlas.findRegion("grass");
        road = atlas.findRegion("road");
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < CELLS_X; i++) {
            for (int j = 0; j < CELLS_Y; j++) {
                batch.draw(grass, i * 64, j * 64);
            }
        }
    }

    public byte[][] getData() {
        return data;
    }
}
