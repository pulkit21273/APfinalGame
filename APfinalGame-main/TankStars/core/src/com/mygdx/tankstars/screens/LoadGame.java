package com.mygdx.tankstars.screens;
import com.badlogic.gdx.Input;
import com.mygdx.tankstars.tankstars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class LoadGame implements Screen, Serializable{
    private final tankstars game;
    transient private OrthographicCamera camera;

    private Texture loadgame;
    public ArrayList<GameScreenGUI> savedgames = new ArrayList<>();
    public LoadGame(tankstars game)
    {
        this.game=game; camera = new OrthographicCamera();
        camera.setToOrtho(false, 1238, 500);
        game.batch = new SpriteBatch();
        loadgame = new Texture("loadgame.png");

    }

    @Override
    public void show() {

    }
    public void emptyLoadedGames(){
        Iterator iter = savedgames.iterator();
        while(iter.hasNext()){
            iter.remove();
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.6f, 0.46f, 0.32f, 0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(loadgame,0,0);
        game.batch.end();
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            System.out.println("1 pressed");
            game.setScreen(savedgames.get(0));
        } if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
            System.out.println("2 pressed");
            game.setScreen(savedgames.get(1));
        }if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
            System.out.println("3 pressed");
            game.setScreen(savedgames.get(2));
        }
        if(savedgames.size()==4){
            emptyLoadedGames();
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
