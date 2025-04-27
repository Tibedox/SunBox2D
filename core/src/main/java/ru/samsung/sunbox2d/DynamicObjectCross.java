package ru.samsung.sunbox2d;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class DynamicObjectCross {
    public float x, y;
    public float width, height;
    public Body body;

    public DynamicObjectCross(World world, float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2, height/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        shape.dispose();

        PolygonShape shape1 = new PolygonShape();
        shape1.setAsBox(height/2, width/2);

        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = shape1;
        fixtureDef1.density = 0.5f;
        fixtureDef1.friction = 0.4f;
        fixtureDef1.restitution = 0.5f;

        body.createFixture(fixtureDef1);
        shape1.dispose();
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
}
