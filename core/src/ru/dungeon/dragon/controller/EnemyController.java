package ru.dungeon.dragon.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.dungeon.dragon.utils.Enemy;

public class EnemyController {

    private static final int MAX_ENEMY = 100;

    private GameController gc;
    private Enemy[] enemies;

    public Enemy[] getEnemies() {
        return enemies;
    }

    public EnemyController(TextureAtlas atlas, GameController gc) {
        this.gc = gc;
        this.enemies = new Enemy[MAX_ENEMY];
        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy(atlas, gc);
        }
    }

    public void activate(int cellX, int cellY) {
        for (Enemy e : enemies) {
            if (!e.isActive()) {
                e.activate(cellX, cellY);
                return;
            }
        }
    }

    public Enemy getEnemyInCell(int cellX, int cellY) {
        for (Enemy e : enemies) {
            if (e.isActive()) {
                if (e.getCellX() == cellX && e.getCellY() == cellY) {
                    return e;
                }
            }
        }
        return null;
    }

    public void update(float dt) {
        for (Enemy e : enemies) {
            if (e.isActive()) {
                e.update(dt);
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Enemy e : enemies) {
            if (e.isActive()) {
                e.render(batch);
            }
        }
    }
}
