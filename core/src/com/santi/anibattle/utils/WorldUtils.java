package com.santi.anibattle.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.santi.anibattle.box2d.EnemyUserData;
import com.santi.anibattle.box2d.GroundUserData;
import com.santi.anibattle.box2d.PlayerUserData;
import com.santi.anibattle.enums.EnemyType;

public class WorldUtils {

    public static World createWorld(){
        return new World(Constants.WORLD_GRAVITY,true);
    }

    public static Body createGround(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X,Constants.GROUND_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH / 2, Constants.GROUND_HEIGHT / 2);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        body.setUserData(new GroundUserData(Constants.GROUND_WIDTH,Constants.GROUND_HEIGHT));
        shape.dispose();
        return body;
    }

    public static Body createPlayer(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.PLAYER_X, Constants.PLAYER_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.PLAYER_WIDTH / 2, Constants.PLAYER_HEIGHT / 2);
        Body body = world.createBody(bodyDef);
        body.setGravityScale(Constants.PLAYER_GRAVITY_SCALE);
        body.createFixture(shape, Constants.PLAYER_DENSITY);
        body.resetMassData();
        body.setUserData(new PlayerUserData(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT));
        shape.dispose();
        return body;
    }

    public static Body createEnemy(World world, EnemyType enemyType) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(enemyType.getX(), enemyType.getY()));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(enemyType.getWidth() / 2, enemyType.getHeight() / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, enemyType.getDensity());
        body.resetMassData();
        EnemyUserData userData = new EnemyUserData(enemyType.getWidth(), enemyType.getHeight(), enemyType.getAtlas());
        body.setUserData(userData);
        shape.dispose();
        return body;
    }

}
