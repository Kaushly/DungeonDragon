package ru.dungeon.dragon.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import ru.dungeon.dragon.GameMap;
import ru.dungeon.dragon.controller.GameController;

public class Enemy extends Unit{

    public Enemy(TextureAtlas atlas, GameController gc) {
        super(gc, 0, 0, 10);
        this.up = atlas.findRegion("eMouseUP");
        this.down = atlas.findRegion("eMouseDown");
        this.left = atlas.findRegion("eMouseLeft");
        this.right = atlas.findRegion("eMouseRight");
        this.textureHp = atlas.findRegion("hp");
        this.hp = -1;
    }

    public void activate(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
        this.hp = hpMax;
        this.exp = MathUtils.random(1, 3);
        this.damage = 1;
    }

    public void update(float dt) {
    }

    public void render(SpriteBatch batch){
        batch.draw(left, cellX * GameMap.CELL_SIZE, cellY * GameMap.CELL_SIZE);
        batch.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        batch.draw(textureHp, cellX * GameMap.CELL_SIZE + 1, cellY * GameMap.CELL_SIZE + 51, 58, 10);
        batch.setColor(0.7f, 0.0f, 0.0f, 1.0f);
        batch.draw(textureHp, cellX * GameMap.CELL_SIZE + 2, cellY * GameMap.CELL_SIZE + 52, 56, 8);
        batch.setColor(0.0f, 1.0f, 0.0f, 1.0f);
        batch.draw(textureHp, cellX * GameMap.CELL_SIZE + 2, cellY * GameMap.CELL_SIZE + 52, (float) hp / hpMax * 56, 8);
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }
}
