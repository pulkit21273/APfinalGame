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

import java.io.*;

public class PauseMenuGUI implements Screen, Serializable {

    transient private Texture PauseMenu;
    private final tankstars game;
    transient private OrthographicCamera camera;


    public PauseMenuGUI(tankstars game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1238, 500);
        game.batch = new SpriteBatch();
        PauseMenu = new Texture("pausemenu.png");

        game.themesound.setLooping(true);
        game.themesound.play();

    }
    public void serialize()
    {
        try
        {
            FileOutputStream fout = new FileOutputStream("savedgame.txt");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(game.gamesc);
            out.close();
            fout.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public Texture getPauseMenu() {
        return PauseMenu;
    }

    public void setPauseMenu(Texture pauseMenu) {
        PauseMenu = pauseMenu;
    }

    public tankstars getGame() {
        return game;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
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
                game.setScreen(game.gamesc);

            }
            if (Gdx.input.getX()>=502 && Gdx.input.getX()<=784 && Gdx.input.getY()>=290 && Gdx.input.getY()<=375)
            {
                game.getLoadgame().savedgames.add(game.gamesc);
                System.out.println(game.getLoadgame().savedgames);
               serialize();

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

