package ru.dungeon.dragon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private TextureRegion bullet;
    private Vector2 position;
    private Vector2 velocity;
    private float angle;
    private boolean active;

    public Bullet(TextureRegion textureRegion) {
        this.bullet = textureRegion;
        this.position = new Vector2(0,0);
        this.velocity = new Vector2(0,0);

    }

    public boolean isActive(){
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public void activate(float x, float y, float vx, float vy, float angle) {
        active = true;
        position.set(x, y);
        velocity.set(vx, vy);
        this.angle = angle;
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        if (position.x < 0 || position.x > 1280 || position.x > GameMap.CELLS_X * 64 || position.y < 0 || position.y > 720 || position.y > GameMap.CELLS_Y * 64) {
            deactivate();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(bullet, position.x - 8, position.y - 8,8,8,16,16,1,1,angle);
    }
}
