package com.example.LibGdxGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.*;
import com.example.LibGdxGame.LibGdxGame;
import com.example.LibGdxGame.actors.RectActor;

import java.util.Iterator;

/**
 * Created by Yuri on 15.08.2014.
 */
public class GameScreen implements Screen, InputProcessor {

    public final LibGdxGame game;

    public Stage stage;
    public RectActor bucket;
    public Array<RectActor> raindrops;

    public Sound dropSound;
    public Sound thunderSounds[];
    public Music rainMusic;
    //public OrthographicCamera camera;

    public long lastDropTime;

    public int score;
    public int fail;
    public final static int GAME_OVER_COUNT_FAIL = 5;

    public GameScreen(LibGdxGame game) {
        this.game = game;
        stage = new Stage(new StretchViewport(800, 480), this.game.batch);
        //stage.getViewport().update(800, 480, false);

        bucket = new RectActor(new Texture(Gdx.files.internal("data/images/bucket.png")), 800/2-64/2, 20, 64, 64);

        stage.addActor(bucket);

        dropSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/drop.wav"));
        thunderSounds = new Sound[3];
        thunderSounds[0] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/thunder1.wav"));
        thunderSounds[1] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/thunder2.wav"));
        thunderSounds[2] = Gdx.audio.newSound(Gdx.files.internal("data/sounds/thunder3.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("data/sounds/rain.mp3"));
        rainMusic.setLooping(true);

        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, 800, 480);

        raindrops = new Array<RectActor>();
        spawnRaindrop();

        score = 0;
        fail = 0;
    }

    private void spawnRaindrop() {
        RectActor drop = new RectActor(new Texture(Gdx.files.internal("data/images/droplet.png")), MathUtils.random(0, 800 - 64), 470, 64, 64);
        raindrops.add(drop);
        stage.addActor(drop);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //camera.update();

        //stage.getBatch().setProjectionMatrix(camera.combined);

        stage.draw();
        //stage.act(Gdx.graphics.getDeltaTime());

        game.batch.begin();
            game.font.draw(game.batch, "Drops Collected: " + score, 0, 480);
            game.font.draw(game.batch, "Drops left: " + (GAME_OVER_COUNT_FAIL - fail), 700, 480);
        game.batch.end();



        if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
            bucket.moveX(Gdx.input.getAccelerometerY() * 2);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            bucket.moveX(-200 * Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            bucket.moveX(200 * Gdx.graphics.getDeltaTime());

        if (bucket.getBounds().x < 0) {
            bucket.getBounds().x = 0;
            bucket.setX(0);
        }
        if (bucket.getBounds().x > 800 - 64) {
            bucket.getBounds().x = 800 - 64;
            bucket.setX(800 - 64);
        }

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
            spawnRaindrop();

        Iterator<RectActor> iter = raindrops.iterator();
        int count = 1;
        while (iter.hasNext()) {
            RectActor raindrop = iter.next();
            raindrop.moveY(-200 * Gdx.graphics.getDeltaTime());
            if (raindrop.getBounds().y + 64 < 0) {
                thunderSounds[MathUtils.random(0, 2)].play();
                fail++;
                iter.remove();
                stage.getActors().removeIndex(count);
                if(fail == GAME_OVER_COUNT_FAIL) {
                    game.setScreen(new GameOverScreen(game, score));
                    dispose();
                }
            }
            if (raindrop.getBounds().overlaps(bucket.getBounds())) {
                score++;
                dropSound.play();
                iter.remove();
                stage.getActors().removeIndex(count);
            }
            count++;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        rainMusic.play();
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
        game.setScreen(new PauseScreen(game, score));
        rainMusic.stop();
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        dropSound.dispose();
        rainMusic.dispose();
    }

    @Override
    public boolean keyDown(int i) {
        if(i == Input.Keys.BACK || i == Input.Keys.ESCAPE) {
            game.setScreen(new PauseScreen(game, score));
            rainMusic.stop();
        }
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
