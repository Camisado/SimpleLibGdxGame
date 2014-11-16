package com.example.LibGdxGame.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Yuri on 16.08.2014.
 */
public class RectActor extends Actor {

    Texture texture;
    Rectangle bounds;

    public RectActor(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.setPosition(x, y);
        this.setSize(width, height);
        this.bounds = new Rectangle(x, y, width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void moveX(float i) {
        bounds.x += i;
        setX(getX() + i);
    }

    public void moveY(float i) {
        bounds.y += i;
        setY(getY() + i);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
