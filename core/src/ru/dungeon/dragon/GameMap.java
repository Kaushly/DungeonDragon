package ru.dungeon.dragon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class GameMap {
    public static final int CELLS_X = 15;
    public static final int CELLS_Y = 15;
    public static final int CELL_SIZE = 60;

    private byte[][] data;
    private int[] x;
    private int[] y;
    private int count;

    private TextureRegion floor;
    private TextureRegion road;
    private TextureRegion rock;

    private final int TERRAIN_GRASS = 0;
    private final int TERRAIN_WALL = 1;


    public static int getCellsX() {
        return CELLS_X;
    }

    public static int getCellsY() {
        return CELLS_Y;
    }

    public GameMap(TextureAtlas atlas) {
        this.data = new byte[CELLS_X][CELLS_Y];
        floor = atlas.findRegion("floor");
        rock = atlas.findRegion("rock");
        count = MathUtils.random(15, (data.length * data.length) / 5);
        x = new int[count];
        y = new int[count];
        generationRock();

    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < CELLS_X; i++) {
            for (int j = 0; j < CELLS_Y; j++) {
                    batch.draw(floor, i * CELL_SIZE, j * CELL_SIZE);
                    data[i][j] = TERRAIN_GRASS;
            }
        }
        for (int i = 0; i < count; i++) {
            batch.draw(rock, x[i] * CELL_SIZE, y[i] * CELL_SIZE);
            data[x[i]][y[i]] = TERRAIN_WALL;
        }
    }

    public void generationRock() {
        for (int i = 0; i < count; i++) {
            x[i] = MathUtils.random(1,CELLS_X - 1);
            y[i] = MathUtils.random(1,CELLS_Y - 1);
        }
    }

    public boolean isCellPassable(int cx, int cy) {
        if (cx < 0 || cx > getCellsX() - 1 || cy < 0 || cy > getCellsY() - 1) {
            return false;
        }
        for (int i = 0; i < x.length - 1; i++) {
            for (int j = 0; j < y.length - 1; j++) {
                if (data[cx][cy] == TERRAIN_WALL) {
                    return false;
                }
            }
        }
        return true;
    }

    public byte[][] getData() {
        return data;
    }
}
