package ru.samsung.sunbox2d;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class DynamicObjectCircle {
    public float x, y;
    public float radius;
    public TextureRegion img;
    public Body body;

    public DynamicObjectCircle(World world, float x, float y, float radius, TextureRegion img) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.img = img;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public boolean hit(Vector3 t){
        Array<Fixture> fixtures = body.getFixtureList();
        for(Fixture f: fixtures) {
            if(f.testPoint(t.x, t.y)) {
                return true;
            }
        }
        return false;
    }

    public float getX(){
        return body.getPosition().x-radius;
    }
    public float getY(){
        return body.getPosition().y-radius;
    }
    public float getWidth(){
        return radius*2;
    }
    public float getHeight(){
        return radius*2;
    }
    public float getAngle(){
        return body.getAngle()* MathUtils.radiansToDegrees;
    }
}
