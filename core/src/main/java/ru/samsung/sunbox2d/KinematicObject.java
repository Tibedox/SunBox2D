package ru.samsung.sunbox2d;

import static ru.samsung.sunbox2d.Main.W_WIDTH;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class KinematicObject {
    public float x, y;
    public float width, height;
    private float vx = 2;
    private float va = 4;
    Body body;

    public KinematicObject(World world, float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2, height/2);
        body.createFixture(shape, 0);

        shape = new PolygonShape();
        shape.setAsBox(height/2, width/2);
        body.createFixture(shape, 0);

        shape.dispose();

        body.setLinearVelocity(vx, 0);
        body.setAngularVelocity(va);
    }

    public void move(){
        x = body.getPosition().x;
        if(x > W_WIDTH+width || x < -width) {
            vx = -vx;
            va = -va;
            body.setLinearVelocity(vx, 0);
            body.setAngularVelocity(va);
        }
    }
}
