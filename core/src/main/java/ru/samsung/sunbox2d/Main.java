package ru.samsung.sunbox2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    public static final float W_WIDTH = 16, W_HEIGHT = 9;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        world = new World(new Vector2(0, -10), true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, W_WIDTH, W_HEIGHT);
        debugRenderer = new Box2DDebugRenderer();
        StaticObject floor = new StaticObject(world, W_WIDTH/2, 1, W_WIDTH/2-0.1f, 0.3f);
        StaticObject wall1 = new StaticObject(world, 1, 5, 0.3f, 3.5f);
        StaticObject wall2 = new StaticObject(world, W_WIDTH-1, 5, 0.3f, 3.5f);
        DynamicObject[] balls = new DynamicObject[50];
        for(int i=0; i<balls.length; i++)
            balls[i] = new DynamicObject(world, 8+MathUtils.random(-0.1f,0.1f), 4.5f+i, 0.4f);

    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        debugRenderer.render(world, camera.combined);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.end();
        world.step(1/60f, 6, 2);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
