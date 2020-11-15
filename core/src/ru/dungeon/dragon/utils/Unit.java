package ru.dungeon.dragon.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.dungeon.dragon.controller.GameController;

public abstract class Unit {
    // Тут в зависимости от того куда мы идем меняется тектура,
    // а не просто угол поворота(если идем вверх то видим  - спину, вниз - лицо ...)
    GameController gc;
    TextureRegion down;
    TextureRegion up;
    TextureRegion left;
    TextureRegion right;
    TextureRegion textureHp;
    BitmapFont font;
    Direction direction;
    int hpMax;
    int hp;
    int damage;
    int cellX;
    int cellY;
    int exp;
    int level;

    public int getCellX() {
        return cellX;
    }

    public int getCellY() {
        return cellY;
    }

    public Unit(GameController gc, int cellX, int cellY, int hpMax) {
        this.gc = gc;
        this.hpMax = hpMax;
        this.cellX = cellX;
        this.cellY = cellY;
    }

    public void activate(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
        this.hp = hpMax;
    }

    public boolean isActive() {
        return hp > 0;
    }

    public boolean takeDamage(int amount) {
        hp -= amount;
        return hp <= 0;
    }

    public abstract void update(float dt);
}
