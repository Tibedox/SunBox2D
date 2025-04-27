package ru.samsung.sunbox2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    public static final float W_WIDTH = 16, W_HEIGHT = 9;
    private SpriteBatch batch;
    private Vector3 touch;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private KinematicObject platform;
    DynamicObjectCircle[] balls = new DynamicObjectCircle[5];
    DynamicObjectBox[] boxes = new DynamicObjectBox[5];
    DynamicObjectTriangle[] triangles = new DynamicObjectTriangle[5];
    DynamicObjectCross[] crosses = new DynamicObjectCross[5];
    Texture circleRed, circleGreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, W_WIDTH, W_HEIGHT);
        touch = new Vector3();
        Box2D.init();
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();
        Gdx.input.setInputProcessor(new MyInputProcessor());

        circleRed = new Texture("red_circle.png");
        circleGreen = new Texture("green_circle.png");
        TextureRegion cRed = new TextureRegion(circleRed, 256, 256);
        TextureRegion cGreen = new TextureRegion(circleGreen, 256, 256);

        StaticObject floor = new StaticObject(world, W_WIDTH/2, 1, W_WIDTH/2-0.1f, 0.3f);
        StaticObject wall1 = new StaticObject(world, 1, 5, 0.3f, 3.5f);
        StaticObject wall2 = new StaticObject(world, W_WIDTH-1, 5, 0.3f, 3.5f);

        for(int i=0; i<balls.length; i++) {
            balls[i] = new DynamicObjectCircle(world, 8 + MathUtils.random(-0.1f, 0.1f), 4.5f + i, 0.4f, MathUtils.randomBoolean() ? cRed : cGreen);
        }
        for(int i=0; i<boxes.length; i++) {
            boxes[i] = new DynamicObjectBox(world, 6 + MathUtils.random(-0.3f, 0.3f), 4.5f + i, 1, 0.5f);
        }
        for (int i = 0; i < triangles.length; i++) {
            triangles[i] = new DynamicObjectTriangle(world, 10+MathUtils.random(-0.1f, 0.1f), 4.5f+i, 0.7f, 0.7f);
        }
        for (int i = 0; i < crosses.length; i++) {
            crosses[i] = new DynamicObjectCross(world, 4 + MathUtils.random(-0.3f, 0.3f), 4.5f + i, 0.8f, 0.2f);
        }
        platform = new KinematicObject(world, -3, 4, 5, 1);
    }

    @Override
    public void render() {
        // касания

        // события
        platform.move();

        // отрисовка
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        debugRenderer.render(world, camera.combined);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        /*for(int i=0; i<balls.length; i++) {
            batch.draw(balls[i].img, balls[i].getX(), balls[i].getY(),
                balls[i].getWidth()/2, balls[i].getHeight()/2,
                balls[i].getWidth(), balls[i].getHeight(), 1, 1, balls[i].getAngle());
        }*/
        batch.end();
        world.step(1/60f, 6, 2);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    class MyInputProcessor implements InputProcessor{
        Vector3 touchStartPos = new Vector3();
        Vector3 touchFinishPos = new Vector3();

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            touchStartPos.set(screenX, screenY, 0);
            camera.unproject(touchStartPos);
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            touchFinishPos.set(screenX, screenY, 0);
            camera.unproject(touchFinishPos);
            Vector3 swipe = new Vector3(touchFinishPos).sub(touchStartPos);
            balls[0].body.applyLinearImpulse(new Vector2(swipe.x, swipe.y), balls[0].body.getPosition(), true);
            return false;
        }

        @Override
        public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(float amountX, float amountY) {
            return false;
        }
    }
}
