package com.example.LibGdxGame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.example.LibGdxGame.LibGdxGame;

/**
 * Created by Yuri on 18.08.2014.
 */
public class PauseScreen implements Screen, InputProcessor {

    public final LibGdxGame game;
    private OrthographicCamera camera;
    int score;

    public PauseScreen(LibGdxGame game, int score) {
        this.game = game;
        this.score = score;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Your score: " + score, 800/2 - 50, 480/2 + 10);
        game.font.draw(game.batch, "Tap anywhere to continue!", 800/2 - 90, 480/2 - 10);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(game.gameScreen);
            dispose();
        }
    }

    @Override
    public void resize(int i, int i2) {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int i) {
        if(i == Input.Keys.BACK || i == Input.Keys.ESCAPE) {
            game.setScreen(new MainMenuScreen(game));
        }
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        if(i == Input.Keys.BACK || i == Input.Keys.ESCAPE) {
            dispose();
        }
        return true;
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
