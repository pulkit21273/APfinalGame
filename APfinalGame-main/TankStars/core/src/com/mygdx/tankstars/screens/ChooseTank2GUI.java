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

import java.io.Serial;
import java.io.Serializable;

public class ChooseTank2GUI implements Screen, Serializable {

    transient private Texture chooset2;
    private final tankstars game;
    private Tank t2;
    private ChooseTank1GUI s1;

    public Texture getChooset2() {
        return chooset2;
    }

    public void setChooset2(Texture chooset2) {
        this.chooset2 = chooset2;
    }

    public tankstars getGame() {
        return game;
    }

    public void setS1(ChooseTank1GUI s1) {
        this.s1 = s1;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    transient private OrthographicCamera camera;

    public ChooseTank1GUI getS1() {
        return s1;
    }

    public ChooseTank2GUI(tankstars game, ChooseTank1GUI s1) {
        this.s1 = s1;
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1238, 500);
        game.batch = new SpriteBatch();
        chooset2 = new Texture("choosetank2.png");
    }


    @Override
    public void show() {

    }

    public Tank getT2() {
        return t2;
    }

    public void setT2(Tank t2) {
        this.t2 = t2;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.6f, 0.46f, 0.32f, 0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(chooset2,0,0);
        game.batch.end();

        if(Gdx.input.justTouched()){
//
            game.themesound.stop();
            if (Gdx.input.getX()>=930 && Gdx.input.getX()<=1150 && Gdx.input.getY()>=20 && Gdx.input.getY()<=160)
            {
                Tank t = new Tank("Coalition",game);
                this.t2=t;
                this.t2.setTankimage(new Texture("coalitioninv.png"));

            }
            if (Gdx.input.getX()>=930 && Gdx.input.getX()<=1150 && Gdx.input.getY()>=175 && Gdx.input.getY()<=320)
            {
                Tank t = new Tank("Frost Tank",game);
                this.t2=t;
                this.t2.setTankimage(new Texture("Frosttankinv.png"));
                //System.out.println("Inside Frost tank " + t.getName());
            }
            if (Gdx.input.getX()>=930 && Gdx.input.getX()<=1150 && Gdx.input.getY()>=330 && Gdx.input.getY()<=480)
            {
                Tank t = new Tank("Toxic",game);
                this.t2=t;
                this.t2.setTankimage(new Texture("toxicinv.png"));
                //System.out.println("Inside Toxic " + t.getName());
            }

//
            GameScreenGUI gamesc = new GameScreenGUI(game,this);
            game.setScreen(gamesc);

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
