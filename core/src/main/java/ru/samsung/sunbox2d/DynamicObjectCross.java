package ru.samsung.sunbox2d;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class DynamicObjectCross {
    public float x, y;
    public float width, height;

    public DynamicObjectCross(World world, float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

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
}
