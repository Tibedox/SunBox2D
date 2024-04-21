package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MyInputProcessor implements InputProcessor {
    SunBox2D main;
    OrthographicCamera camera;
    Vector3 touch;

    final Vector2 firstTouch = new Vector2();
    DynamicBody bodyTouched;

    public MyInputProcessor(SunBox2D main) {
        this.main = main;
        camera = main.camera;
        touch = main.touch;
    }

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
        touch.set(screenX, screenY, 0);
        camera.unproject(touch);
        for (DynamicBody b: main.balls){
            if(b.hit(touch.x, touch.y)){
                bodyTouched = b;
                firstTouch.set(touch.x, touch.y);
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(bodyTouched != null) {
            touch.set(screenX, screenY, 0);
            camera.unproject(touch);
            Vector2 impulse = new Vector2((-touch.x + firstTouch.x)*2, (-touch.y + firstTouch.y)*2);
            //Vector2 impulse = new Vector2(touch.x - firstTouch.x, touch.y - firstTouch.y);
            bodyTouched.setImpulse(impulse);
            bodyTouched = null;
        }
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
