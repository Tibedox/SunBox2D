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
	public static final float WORLD_WIDTH = 16, WORLD_HEIGHT = 9;

	SpriteBatch batch;
	OrthographicCamera camera;
	World world;
	Box2DDebugRenderer debugRenderer;

	Texture img;

	StaticBody floor;
	StaticBody wallLeft, wallRight;

	DynamicBody[] ball = new DynamicBody[25];
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		world = new World(new Vector2(0, -9.8f), true);
		debugRenderer = new Box2DDebugRenderer();

		img = new Texture("badlogic.jpg");

		floor = new StaticBody(world, WORLD_WIDTH/2, 0.5f, WORLD_WIDTH, 1);
		wallLeft = new StaticBody(world, 0.5f, WORLD_HEIGHT/2, 1, WORLD_HEIGHT);
		wallRight = new StaticBody(world, WORLD_WIDTH-0.5f, WORLD_HEIGHT/2, 1, WORLD_HEIGHT);
		for (int i = 0; i < ball.length; i++) {
			ball[i] = new DynamicBody(world, WORLD_WIDTH/2+MathUtils.random(-0.01f, 0.01f), WORLD_HEIGHT+i, 0.4f);
		}

	}

	@Override
	public void render () {
		world.step(1/60f, 6, 2);
		ScreenUtils.clear(0.2f, 0, 0.3f, 1);
		debugRenderer.render(world, camera.combined);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		/*for (int i = 0; i < ball.length; i++) {
			batch.draw(img, ball[i].getX(), ball[i].getY(), ball[i].getWidth(), ball[i].getHeight());
		}*/
		//batch.draw(img, floor.getX(), floor.getY(), floor.getWidth(), floor.getHeight());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		world.dispose();
		debugRenderer.dispose();
	}
}
