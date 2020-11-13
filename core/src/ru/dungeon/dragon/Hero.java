package ru.dungeon.dragon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Hero {

    private TextureRegion heroDown;
    private TextureRegion heroUp;
    private TextureRegion heroLeft;
    private TextureRegion heroRight;
    private Vector2 position;
    private Direction direction;
    private boolean auto;
    private int step;

    private final int CENTRE = 32;
    private BulletController bulletController;

    public Hero(TextureAtlas atlas, BulletController bulletController) {
        this.heroDown = atlas.findRegion("down");
        this.heroUp = atlas.findRegion("up");
        this.heroLeft = atlas.findRegion("left");
        this.heroRight = atlas.findRegion("right");
        this.position = new Vector2(CENTRE, CENTRE);
        this.direction = Direction.DOWN;
        this.bulletController = bulletController;
        this.step = 64;
    }

    public void update(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            direction = Direction.UP;
            position.y += step * direction.getY();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            direction = Direction.DOWN;
            position.y += step * direction.getY();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            direction = Direction.LEFT;
            position.x += step * direction.getX();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            direction = Direction.RIGHT;
            position.x += step * direction.getX();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            auto = !auto;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (auto == true) {
                for (int i = 0; i < 2; i++) {
                bulletController.activate(position.x, position.y, 600 * direction.getX() + i * 10, 600 * direction.getY() + i * 10, direction.getAngle());
                }
            } else {
                bulletController.activate(position.x, position.y, 600 * direction.getX(), 600 * direction.getY(), direction.getAngle());
            }
        }
        outScreen();
    }

    private void outScreen() {
        if(position.x < 0){
            position.x = CENTRE;
        }
        if(position.x > GameMap.CELLS_X * 64){
            position.x = GameMap.CELLS_X * 64 - CENTRE;
        }
        if(position.y < 0) {
            position.y = CENTRE;
        }
        if(position.y > GameMap.CELLS_Y * 64){
            position.y = GameMap.CELLS_Y * 64 - CENTRE;
        }
        if(position.x > Gdx.graphics.getWidth()){
            position.x = (Gdx.graphics.getWidth() % GameMap.CELLS_X - 1) * 64 - CENTRE;
        }
        if(position.y > Gdx.graphics.getHeight()){
            position.y = (Gdx.graphics.getHeight() % GameMap.CELLS_Y - 1) * 64 - CENTRE;
        }
    }


    public void render(SpriteBatch batch) {
        if (direction == Direction.DOWN) {
            batch.draw(heroDown, position.x - CENTRE, position.y - CENTRE);
        } else if (direction == Direction.UP) {
            batch.draw(heroUp, position.x - CENTRE, position.y - CENTRE);
        } else if (direction == Direction.LEFT) {
            batch.draw(heroLeft, position.x - CENTRE, position.y - CENTRE);
        } else {
            batch.draw(heroRight, position.x - CENTRE, position.y - CENTRE);
        }
    }
}
