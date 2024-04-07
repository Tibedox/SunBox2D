package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class SunBox2D extends ApplicationAdapter {
	// глобальные константы
	public static final float WORLD_WIDTH = 16, WORLD_HEIGHT = 9;
	public static final int TYPE_SMILE = 0, TYPE_BRICK = 1;

	// системные объекты
	SpriteBatch batch;
	OrthographicCamera camera;
	World world;
	Box2DDebugRenderer debugRenderer;

	// ресурсы
	Texture imgBeton;
	Texture imgSmileTexture;
	Texture imgBrickTexture;
	TextureRegion imgSmile;
	TextureRegion imgBrick;
	TextureRegion img;

	// наши объекты и переменные
	StaticBody floor;
	StaticBody wallLeft, wallRight;

	DynamicBody[] balls = new DynamicBody[50];
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		world = new World(new Vector2(0, -9.8f), true);
		debugRenderer = new Box2DDebugRenderer();

		imgBeton = new Texture("beton.png");
		imgSmileTexture = new Texture("smile.png");
		imgBrickTexture = new Texture("brick.png");
		imgSmile = new TextureRegion(imgSmileTexture, 0, 0, 128, 128);
		imgBrick = new TextureRegion(imgBrickTexture, 0, 0, 100, 100);

		floor = new StaticBody(world, 8, 0.5f, 16, 1);
		wallLeft = new StaticBody(world, 0.5f, 5, 1, 8);
		wallRight = new StaticBody(world, 15.5f, 5, 1, 8);
		for (int i = 0; i < balls.length; i++) {
			if(i%2 == 0) {
				balls[i] = new DynamicBody(world, WORLD_WIDTH / 2 + MathUtils.random(-0.01f, 0.01f), WORLD_HEIGHT + i, 0.4f);
			} else {
				balls[i] = new DynamicBody(world, WORLD_WIDTH / 2 + MathUtils.random(-0.01f, 0.01f), WORLD_HEIGHT + i, 1, 0.5f);
			}
		}
	}

	@Override
	public void render () {
		world.step(1/60f, 6, 2);
		ScreenUtils.clear(0.2f, 0, 0.3f, 1);
		debugRenderer.render(world, camera.combined);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (DynamicBody b: balls) {
			if(b.type == TYPE_SMILE) img = imgSmile;
			else img = imgBrick;
			batch.draw(img, b.getX(), b.getY(), b.getWidth()/2, b.getHeight()/2, b.getWidth(), b.getHeight(), 1, 1, b.getAngle());
		}
		batch.draw(imgBeton, floor.getX(), floor.getY(), floor.getWidth(), floor.getHeight());
		batch.draw(imgBeton, wallLeft.getX(), wallLeft.getY(), wallLeft.getWidth(), wallLeft.getHeight());
		batch.draw(imgBeton, wallRight.getX(), wallRight.getY(), wallRight.getWidth(), wallRight.getHeight());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		imgBeton.dispose();
		imgSmileTexture.dispose();
		imgBrickTexture.dispose();
		world.dispose();
		debugRenderer.dispose();
	}
}
