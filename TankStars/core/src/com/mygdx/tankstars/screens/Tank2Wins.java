package com.mygdx.tankstars.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankstars.tankstars;

public class Tank2Wins implements Screen {
    transient private Texture img;
    private final tankstars game;
    transient private OrthographicCamera camera;
    public Tank2Wins(tankstars game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1238, 500);
        game.batch = new SpriteBatch();
        img = new Texture("win2.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.6f, 0.46f, 0.32f, 0);
        camera.update();
        game.batch.begin();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.draw(img,0,-160);
        game.batch.end();
        if(Gdx.input.justTouched()){
            if (Gdx.input.getX() >= 455 && Gdx.input.getX() <= 770 && Gdx.input.getY() >= 210  && Gdx.input.getY() <= 310)
            {
                //System.out.println(game.getChoosetank2gui());
                //GameScreenGUI newgame = new GameScreenGUI(game,game.getChoosetank2gui());
                //System.out.println("Tank 2 wins 1");
                game.setScreen(new ChooseTank1GUI(game));
                //System.out.println("Tank 2 wins 2");
            }
            if (Gdx.input.getX() >= 455 && Gdx.input.getX() <= 770 && Gdx.input.getY() >= 330  && Gdx.input.getY() <= 430)
            {
                HomePageGUI home1 = new HomePageGUI(game);
                game.setScreen(home1);

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
