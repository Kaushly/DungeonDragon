package ru.dungeon.dragon.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import ru.dungeon.dragon.controller.GameController;
import ru.dungeon.dragon.GameMap;

public class Hero extends Unit {

    private TextureRegion textureExp;
    private float movementTime;
    private float movementMaxTime;
    private int targetX;
    private int targetY;
    private int expMax;
    private int actPointsMax;
    private int actPoints;

    public Hero(TextureAtlas atlas, GameController gc) {
        super(gc, 1, 1, 10);
        this.down = atlas.findRegion("down");
        this.up = atlas.findRegion("up");
        this.left = atlas.findRegion("left");
        this.right = atlas.findRegion("right");
        this.textureHp = atlas.findRegion("hp");
        this.textureExp = atlas.findRegion("hp");
        this.direction = Direction.DOWN;
        this.movementMaxTime = 0.3f;
        this.targetX = getCellX();
        this.targetY = getCellY();
        this.hp = hpMax;
        this.damage = 1;
        this.expMax = 10; // потом можно привязать к уровню
        this.exp = 0;
        this.level = 1;
        this.actPointsMax = 5;
        this.actPoints = actPointsMax;
        this.font = new BitmapFont();
    }

    public void update(float dt) {
        if (isActive() && actPoints > 0) {
            checkMovement(dt);
        }
        if (actPoints <= 0) {
            actPoints = actPointsMax;
        }
    }

    public void render(SpriteBatch batch) {
        if (isActive()) {
            float px = cellX * GameMap.CELL_SIZE;
            float py = cellY * GameMap.CELL_SIZE;
            if (!isStayStill()) {
                px = cellX * GameMap.CELL_SIZE + (targetX - cellX) * (movementTime / movementMaxTime) * GameMap.CELL_SIZE;
                py = cellY * GameMap.CELL_SIZE + (targetY - cellY) * (movementTime / movementMaxTime) * GameMap.CELL_SIZE;
            }
            switch (direction) {
                case UP:
                    batch.draw(up, px, py);
                    break;
                case DOWN:
                    batch.draw(down, px, py);
                    break;
                case LEFT:
                    batch.draw(left, px, py);
                    break;
                case RIGHT:
                    batch.draw(right, px, py);
                    break;
            }
            statistics(batch, px, py);
        }
    }

    public boolean isStayStill() {
        return cellY == targetY && cellX == targetX;
    }

    private void checkMovement(float dt) {
        if (Gdx.input.justTouched() && isStayStill()) {
            if (Math.abs(gc.getCursorX() - cellX) + Math.abs(gc.getCursorY() - cellY) == 1) {
                targetX = gc.getCursorX();
                targetY = gc.getCursorY();
            }
        }
// Необходимо для определения какая текстура
        directionCalculation();

        Enemy e = gc.getEnemyController().getEnemyInCell(targetX, targetY);
        if (e != null) {
            targetX = cellX;
            targetY = cellY;
            e.takeDamage(damage);
            actPoints--;
            killing(e);
            if (MathUtils.random(0, 100) < 25) {
                takeDamage(e.damage);
            }

        }

        if (!gc.getGameMap().isCellPassable(targetX, targetY)) {
            targetX = cellX;
            targetY = cellY;
        }

        if (!isStayStill()) {
            movementTime += dt;
            if (movementTime > movementMaxTime) {
                movementTime = 0;
                cellX = targetX;
                cellY = targetY;
                actPoints--;
            }
        }
    }

    private void directionCalculation() {
        if (cellX != targetX) {
            direction = targetX - cellX > 0 ? Direction.RIGHT : Direction.LEFT;
        } else if (cellY != targetY) {
            direction = targetY - cellY > 0 ? Direction.UP : Direction.DOWN;
        }
    }

    private void killing(Enemy e) {
        if (e.hp <= 0) {
            exp += e.exp;
            if (exp >= expMax) {
                exp = exp - expMax;
                level++;
                actPoints = actPointsMax;
            }
        }
    }

    private void statistics(SpriteBatch batch, float px, float py) {
        batch.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        batch.draw(textureHp, px + 1, py + 51, 58, 10);
        batch.setColor(0.7f, 0.0f, 0.0f, 1.0f);
        batch.draw(textureHp, px + 2, py + 52, 56, 8);
        batch.setColor(0.0f, 1.0f, 0.0f, 1.0f);
        batch.draw(textureHp, px + 2, py + 52, (float) hp / hpMax * 56, 8);
        batch.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        batch.draw(textureExp, px + 1, py + 43, 58, 10);
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        batch.draw(textureExp, px + 2, py + 44, 56, 8);
        batch.setColor(0.0f, 0.0f, 1.0f, 1.0f);
        batch.draw(textureExp, px + 2, py + 44, (float) exp / expMax * 56, 8);
        font.setColor(0.0f, 1.0f, 0.0f, 1.0f);
        font.draw(batch, "" + hp + "", px + 45, py + 14);
        font.setColor(0.0f, 0.0f, 1.0f, 1.0f);
        font.draw(batch, "" + exp + "", px, py + 14);
        font.setColor(0.0f, 0.0f, 0.0f, 0.7f);
        font.draw(batch, "Action points: " + actPoints + " !", GameMap.CELL_SIZE / 2.0f, Gdx.graphics.getHeight() - GameMap.CELL_SIZE / 2.0f);
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }
}
