package ru.dungeon.dragon.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import ru.dungeon.dragon.GameMap;
import ru.dungeon.dragon.utils.Hero;

public class GameController {
    private BulletController bulletController;
    private GameMap gameMap;
    private Hero hero;
    private EnemyController enemyController;
    private int cursorX, cursorY;

    public BulletController getBulletController() {
        return bulletController;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Hero getHero() {
        return hero;
    }

    public EnemyController getEnemyController() {
        return enemyController;
    }

    public int getCursorX() {
        return cursorX;
    }

    public int getCursorY() {
        return cursorY;
    }



    public GameController(TextureAtlas atlas) {
        this.gameMap = new GameMap(atlas);
        this.bulletController = new BulletController(atlas);
        this.hero = new Hero(atlas, this);
        this.enemyController = new EnemyController(atlas, this);
        for (int i = 0; i < 6; i++) {
            int x = MathUtils.random(1, GameMap.getCellsX() - 1);
            int y = MathUtils.random(1, GameMap.getCellsY() - 1);
            if(gameMap.isCellPassable(x,y)) {
                this.enemyController.activate(x, y);
            }
        }
    }

    public void update(float dt) {
        cursorX = (Gdx.input.getX() / GameMap.CELL_SIZE);
        cursorY = ((680 - Gdx.input.getY()) / GameMap.CELL_SIZE);

        bulletController.update(dt);
        hero.update(dt);
        enemyController.update(dt);
    }
}
