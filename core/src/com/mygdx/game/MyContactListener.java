package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {
    SunBox2D main;

    public MyContactListener(SunBox2D main) {
        this.main = main;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Body bodyA = fixtureA.getBody();
        Body bodyB = fixtureB.getBody();

        if(bodyA.getType() == BodyDef.BodyType.DynamicBody && bodyB.getType() == BodyDef.BodyType.DynamicBody) {
            // Далее можно добавить логику для определения момента столкновения
            main.sndKnock.play();
        }

        if(bodyA.getType() == BodyDef.BodyType.DynamicBody && bodyB.getType() == BodyDef.BodyType.StaticBody ||
                bodyA.getType() == BodyDef.BodyType.StaticBody && bodyB.getType() == BodyDef.BodyType.DynamicBody) {
            // Далее можно добавить логику для определения момента столкновения
            main.sndChpok.play();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
