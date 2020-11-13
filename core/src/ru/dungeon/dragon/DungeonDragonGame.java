package ru.dungeon.dragon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class DungeonDragonGame extends ApplicationAdapter {
	SpriteBatch batch;
	TextureAtlas atlas;
	Hero hero;
	GameMap gameMap;
	BulletController bulletController;

	@Override
	public void create () {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("texture/game.pack");
		bulletController = new BulletController(atlas);
		gameMap = new GameMap(atlas);
		hero = new Hero(atlas, bulletController);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		gameMap.render(batch);
		hero.render(batch);
		bulletController.render(batch);
		batch.end();
	}

	private void update(float dt) {
		bulletController.update(dt);
		hero.update(dt);
	}

	@Override
	public void dispose () {
		batch.dispose();
		atlas.dispose();
	}
}
