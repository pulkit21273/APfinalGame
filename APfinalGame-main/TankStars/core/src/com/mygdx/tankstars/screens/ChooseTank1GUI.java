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

import java.io.Serializable;

public class ChooseTank1GUI implements Screen, Serializable {

    transient private Texture chooset1;
    private Tank t1;
    private final tankstars game;
    transient private OrthographicCamera camera;

    public Texture getChooset1() {
        return chooset1;
    }

    public void setChooset1(Texture chooset1) {
        this.chooset1 = chooset1;
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

    public Tank getT1() {
        return t1;
    }

    public void setT1(Tank t1) {
        this.t1 = t1;
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
//            if (Gdx.input.getX()>=214 && Gdx.input.getX()<=492 && Gdx.input.getY()>=318 && Gdx.input.getY()<=420)
//            {
                if (Gdx.input.getX()>=930 && Gdx.input.getX()<=1150 && Gdx.input.getY()>=20 && Gdx.input.getY()<=160)
                {
                    Tank t = new Tank("Coalition",game);
                    this.t1=t;
                    this.t1.setTankimage(new Texture("coalition.png"));
                    //System.out.println("Inside coalition " + t.getName());
                }
                if (Gdx.input.getX()>=930 && Gdx.input.getX()<=1150 && Gdx.input.getY()>=175 && Gdx.input.getY()<=320)
                {
                    Tank t = new Tank("Frost Tank",game);
                    this.t1=t;
                    this.t1.setTankimage(new Texture("Frosttank.png"));
                    //System.out.println("Inside Frost tank " + t.getName());
                }
                if (Gdx.input.getX()>=930 && Gdx.input.getX()<=1150 && Gdx.input.getY()>=330 && Gdx.input.getY()<=480)
                {
                    Tank t = new Tank("Toxic",game);
                    this.t1=t;
                    this.t1.setTankimage(new Texture("toxic.png"));
                    //System.out.println("Inside Toxic " + t.getName());
                }

                ChooseTank2GUI cht2= new ChooseTank2GUI(game,this);
                game.setScreen(cht2);
            }
//        }
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
