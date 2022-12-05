package com.mygdx.tankstars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankstars.tankstars;

public class HomePageGUI implements Screen {

    Texture homeimg;
    private final tankstars game;
    private OrthographicCamera camera;


    public HomePageGUI(tankstars game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1238, 500);
        game.batch = new SpriteBatch();
        homeimg = new Texture("homescreen.png");

        game.themesound = Gdx.audio.newMusic(Gdx.files.internal("mainmusic.mp3"));

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
        game.batch.draw(homeimg,0,0);
        game.batch.end();

        if(Gdx.input.justTouched()){


            if(Gdx.input.getX()>=705 && Gdx.input.getX()<=1170 && Gdx.input.getY()>=62 && Gdx.input.getY()<=162){
            ChooseTank1GUI cht1 = new ChooseTank1GUI(game);
            game.setScreen(cht1);
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
