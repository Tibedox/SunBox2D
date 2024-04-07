package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class SunBox2D extends ApplicationAdapter {
	// глобальные константы
	public static final float WORLD_WIDTH = 16, WORLD_HEIGHT = 9;

	// системные объекты
	SpriteBatch batch;
	OrthographicCamera camera;
	World world;
	Box2DDebugRenderer debugRenderer;

	// ресурсы
	Texture imgBeton;

	// наши объекты и переменные
	StaticBody floor;
	StaticBody wallLeft, wallRight;

	DynamicBody[] balls = new DynamicBody[25];
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		world = new World(new Vector2(0, -9.8f), true);
		debugRenderer = new Box2DDebugRenderer();

		imgBeton = new Texture("beton.png");

		floor = new StaticBody(world, 8, 0.5f, 16, 1);
		wallLeft = new StaticBody(world, 0.5f, 5, 1, 8);
		wallRight = new StaticBody(world, 15.5f, 5, 1, 8);
		for (int i = 0; i < balls.length; i++) {
			balls[i] = new DynamicBody(world, WORLD_WIDTH/2+MathUtils.random(-0.01f, 0.01f), WORLD_HEIGHT+i, 0.4f);
		}
	}

	@Override
	public void render () {
		world.step(1/60f, 6, 2);
		ScreenUtils.clear(0.2f, 0, 0.3f, 1);
		debugRenderer.render(world, camera.combined);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		/*for (int i = 0; i < balls.length; i++) {
			batch.draw(img, balls[i].getX(), balls[i].getY(), balls[i].getWidth(), balls[i].getHeight());
		}*/
		batch.draw(imgBeton, floor.getX(), floor.getY(), floor.getWidth(), floor.getHeight());
		batch.draw(imgBeton, wallLeft.getX(), wallLeft.getY(), wallLeft.getWidth(), wallLeft.getHeight());
		batch.draw(imgBeton, wallRight.getX(), wallRight.getY(), wallRight.getWidth(), wallRight.getHeight());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		imgBeton.dispose();
		world.dispose();
		debugRenderer.dispose();
	}
}
