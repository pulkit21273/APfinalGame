package com.mygdx.tankstars.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankstars.tankstars;

public class PauseMenuGUI implements Screen {

    Texture PauseMenu;
    private final tankstars game;
    private OrthographicCamera camera;
    //public PauseMenu pausesc;

    public PauseMenuGUI(tankstars game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1238, 500);
        game.batch = new SpriteBatch();
        PauseMenu = new Texture("pausemenu.png");

        game.themesound.setLooping(true);
        game.themesound.play();

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.6f, 0.46f, 0.32f, 0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(PauseMenu,0,0);
        game.batch.end();

        if(Gdx.input.justTouched()){
            if (Gdx.input.getX()>=502 && Gdx.input.getX()<=784 && Gdx.input.getY()>=190 && Gdx.input.getY()<=275)
            {
                game.themesound.stop();
                GameScreenGUI gamesc = new GameScreenGUI(game);
                game.setScreen(gamesc);
            }
            if (Gdx.input.getX()>=502 && Gdx.input.getX()<=784 && Gdx.input.getY()>=390 && Gdx.input.getY()<=475)
            {
                game.themesound.stop();
                HomePageGUI hui = new HomePageGUI(game);
                game.setScreen(hui);

            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

