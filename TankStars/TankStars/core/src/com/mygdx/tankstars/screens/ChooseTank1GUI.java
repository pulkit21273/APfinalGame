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

public class ChooseTank1GUI implements Screen{

    Texture chooset1;
    private final tankstars game;
    private OrthographicCamera camera;

    public ChooseTank1GUI(tankstars game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1238, 500);
        game.batch = new SpriteBatch();
        chooset1 = new Texture("choosetank1.png");
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
        game.batch.draw(chooset1,0,0);
        game.batch.end();

        if(Gdx.input.justTouched()){
            if (Gdx.input.getX()>=214 && Gdx.input.getX()<=492 && Gdx.input.getY()>=318 && Gdx.input.getY()<=420)
            {
                ChooseTank2GUI cht2= new ChooseTank2GUI(game);
                game.setScreen(cht2);
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
