package ru.dungeon.dragon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BulletController {
    private static final int MAX_BULLET = 100;
    private Bullet[] items;
    private TextureRegion region;

    public Bullet[] getItems() {
        return items;
    }

    public BulletController(TextureAtlas atlas) {
        this.region = atlas.findRegion("bullet");
        this.items = new Bullet[MAX_BULLET];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Bullet(region);
        }
    }

    public void activate(float x, float y, float vx, float vy, float angle) {
        for (Bullet b : items) {
            if (!b.isActive()) {
                b.activate(x, y, vx, vy, angle);
                return;
            }
        }
    }

    public void update(float dt){
        for (Bullet b : items){
            if(b.isActive()){
                b.update(dt);
            }
        }
    }

    public void render(SpriteBatch batch){
        for (Bullet b : items){
            if(b.isActive()){
                b.render(batch);
            }
        }
    }
}
