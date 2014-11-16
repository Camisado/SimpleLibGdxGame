package com.example.LibGdxGame.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.example.LibGdxGame.LibGdxGame;
import com.example.LibGdxGame.screens.GameScreen;

/**
 * Created by Yuri on 15.08.2014.
 */
public class MainMenuScreen implements Screen, InputProcessor {

    private final LibGdxGame game;
    private OrthographicCamera camera;

    public MainMenuScreen(LibGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Drop!!! ", 800/2 - 50, 480/2 + 10);
        game.font.draw(game.batch, "Tap anywhere to begin!", 800/2 - 60, 480/2 - 10);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.gameScreen = new GameScreen(game);
            game.setScreen(game.gameScreen);
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

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
        if (i == Input.Keys.BACK || i == Input.Keys.ESCAPE) {
            Gdx.app.exit();
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
