package com.example.LibGdxGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.LibGdxGame.screens.GameOverScreen;
import com.example.LibGdxGame.screens.GameScreen;
import com.example.LibGdxGame.screens.MainMenuScreen;
import com.example.LibGdxGame.screens.PauseScreen;

/**
 * Created by Юрий on 14.08.2014.
 */
public class LibGdxGame extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

    public GameScreen gameScreen;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
