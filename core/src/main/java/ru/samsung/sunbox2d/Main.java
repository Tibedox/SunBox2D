package ru.samsung.sunbox2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    public static final float W_WIDTH = 16, W_HEIGHT = 9;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private KinematicObject platform;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Box2D.init();
        world = new World(new Vector2(0, -10), true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, W_WIDTH, W_HEIGHT);

        debugRenderer = new Box2DDebugRenderer();
        StaticObject floor = new StaticObject(world, W_WIDTH/2, 1, W_WIDTH/2-0.1f, 0.3f);
        StaticObject wall1 = new StaticObject(world, 1, 5, 0.3f, 3.5f);
        StaticObject wall2 = new StaticObject(world, W_WIDTH-1, 5, 0.3f, 3.5f);
        DynamicObjectCircle[] balls = new DynamicObjectCircle[50];
        for(int i=0; i<balls.length; i++) {
            balls[i] = new DynamicObjectCircle(world, 8 + MathUtils.random(-0.1f, 0.1f), 4.5f + i, 0.4f);
        }
        DynamicObjectBox[] boxes = new DynamicObjectBox[50];
        for(int i=0; i<boxes.length; i++) {
            boxes[i] = new DynamicObjectBox(world, 6 + MathUtils.random(-0.3f, 0.3f), 4.5f + i, 1, 0.5f);
        }
        DynamicObjectTriangle[] triangles = new DynamicObjectTriangle[50];
        for (int i = 0; i < triangles.length; i++) {
            triangles[i] = new DynamicObjectTriangle(world, 10+MathUtils.random(-0.1f, 0.1f), 4.5f+i, 0.7f, 0.7f);
        }
        DynamicObjectCross[] crosses = new DynamicObjectCross[50];
        for (int i = 0; i < crosses.length; i++) {
            crosses[i] = new DynamicObjectCross(world, 4 + MathUtils.random(-0.3f, 0.3f), 4.5f + i, 0.8f, 0.2f);
        }
        platform = new KinematicObject(world, -3, 4, 5, 1);
    }

    @Override
    public void render() {
        // события
        platform.move();

        // отрисовка
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
