package com.mygdx.game;

import static com.mygdx.game.SunBox2D.TYPE_BRICK;
import static com.mygdx.game.SunBox2D.TYPE_SMILE;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class DynamicBody {
    private float x, y;
    private float r;
    private float width, height;
    private Body body;
    public int type;

    DynamicBody(World world, float x, float y, float r){
        type = TYPE_SMILE;
        this.x = x;
        this.y = y;
        this.r = r;
        width = height = r*2;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(r);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.2f; // плотность
        fixtureDef.friction = 0.4f; // трение
        fixtureDef.restitution = 0.8f; // упругость

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    DynamicBody(World world, float x, float y, float width, float height){
        type = TYPE_BRICK;
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
        fixtureDef.density = 0.2f; // плотность
        fixtureDef.friction = 0.4f; // трение
        fixtureDef.restitution = 0.9f; // упругость

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    public float getX() {
        return body.getPosition().x-width/2;
    }

    public float getY() {
        return body.getPosition().y-height/2;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public  float getAngle() {
        return body.getAngle() * MathUtils.radiansToDegrees;
    }
}
