package ru.dungeon.dragon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.dungeon.dragon.controller.GameController;

public class DungeonDragonGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private GameController gc;
	private TextureRegion cursor;


	@Override
	public void create () {
		batch = new SpriteBatch();
		atlas = new TextureAtlas("texture/game.pack");
		cursor = atlas.findRegion("cursor");
		gc = new GameController(atlas);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		gc.update(dt);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		gc.getGameMap().render(batch);
		gc.getHero().render(batch);
		gc.getBulletController().render(batch);
		gc.getEnemyController().render(batch);
		batch.setColor(1,1,1,0.5f);
		batch.draw(cursor, gc.getCursorX() * GameMap.CELL_SIZE, gc.getCursorY() * GameMap.CELL_SIZE);
		batch.setColor(1,1,1,1);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		atlas.dispose();
	}
}
