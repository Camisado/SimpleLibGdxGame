package com.example.LibGdxGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Created by Юрий on 14.08.2014.
 */
public class DesktopStarter {

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Drop";
        cfg.width = 800;
        cfg.height = 480;
        new LwjglApplication(new LibGdxGame(), cfg);
    }
}
