package ru.dungeon.dragon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class GameMap {
    public static final int CELLS_X = 15;
    public static final int CELLS_Y = 15;
    private byte[][] data;
    private byte[] x;
    private byte[] y;
    private int count;

    private TextureRegion grass;
    private TextureRegion road;

    public GameMap(TextureAtlas atlas) {
        this.data = new byte[CELLS_X][CELLS_Y];
        grass = atlas.findRegion("grass");
        road = atlas.findRegion("road");
        count = MathUtils.random(15, (data.length * data.length) / 2);
        x = new byte[count];
        y = new byte[count];
        generationRoad();

    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < CELLS_X; i++) {
            for (int j = 0; j < CELLS_Y; j++) {
                batch.draw(grass, i * 64, j * 64);
            }
        }
        for (int i = 0; i < count; i++) {
            batch.draw(road, x[i] * 64, y[i] * 64);
        }
    }

    public void generationRoad(){
        for (int i = 0; i < count; i++) {
            x[i] = (byte) MathUtils.random(CELLS_X - 1);
            y[i] = (byte) MathUtils.random(CELLS_Y - 1);
        }
    }

    public byte[][] getData() {
        return data;
    }
}
