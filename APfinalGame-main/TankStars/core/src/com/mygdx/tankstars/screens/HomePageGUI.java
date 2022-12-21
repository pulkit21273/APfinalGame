package com.mygdx.tankstars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankstars.tankstars;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import static java.lang.System.exit;

public class HomePageGUI implements Screen, Serializable {

    transient private Texture homeimg;
    private final tankstars game;
    transient private OrthographicCamera camera;

    public Texture getHomeimg() {
        return homeimg;
    }

    public void setHomeimg(Texture homeimg) {
        this.homeimg = homeimg;
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

//    public void deserialize() throws IOException, ClassNotFoundException {
//        FileInputStream fin = new FileInputStream("savedgame.txt");
//        ObjectInputStream in = new ObjectInputStream(fin);
//        GameScreenGUI recieved = (GameScreenGUI) in.readObject();
////        System.out.println(recieved);
////        System.out.println(recieved.getSelectedt1());
////        System.out.println(recieved.getSelectedt1().getT1().getName());
//        recieved.setCamera(camera);
//        recieved.getGame().setBatch(new SpriteBatch());
//        recieved.getGame().setThemesound(Gdx.audio.newMusic(Gdx.files.internal("mainmusic.mp3")));
//
//        recieved.setGameimage(new Texture("background.png"));
//        recieved.setGroundimg(new Texture("groundgametexture.png"));
//        recieved.setTank1wins(new Texture("win1.png"));
//        recieved.setTank2wins(new Texture("win2.png"));
//        recieved.setFirebutton(new Texture("firebutton.png"));
//        recieved.setDot(new Texture("dot.png"));
//        recieved.setFont(new BitmapFont());
//        recieved.getPausesc().setCamera(camera);
//        recieved.getPausesc().setPauseMenu(new Texture("pausemenu.png"));
//        recieved.setTank1wins(new Texture("win1.png"));
//        recieved.setTank2wins(new Texture("win2.png"));
//        recieved.getSelectedt1().setCamera(camera);
//        recieved.getSelectedt1().setChooset1(new Texture("choosetank1.png"));
//        recieved.getSelectedt2().setCamera(camera);
//        recieved.getSelectedt2().setChooset2(new Texture("choosetank2.png"));
//        recieved.getGame().getHomepg().setCamera(camera);
//        recieved.getGame().getHomepg().setHomeimg(new Texture("homescreen.png"));
////        recieved.getL().setTankimage();
//        if (recieved.getSelectedt1().getT1().getName().equals("Frost Tank"))
//        {
//            //recieved.getL().setTankimage(new Texture("Frosttank.png"));
//           // recieved.getL().getCurrentweapon().setWeaponimg(new Texture("fireball.png"));
//            recieved.getSelectedt1().getT1().setTankimage(new Texture("Frosttank.png"));
//            Weapon w = new Weapon();
//            w.setAssociatedtank(recieved.getSelectedt1().getT1());
//            recieved.getSelectedt1().getT1().setCurrentweapon(w);
//            recieved.getSelectedt1().getT1().getCurrentweapon().setWeaponimg(new Texture("fireball.png"));
//
//        }
//        if (recieved.getL().getName().equals("Coalition"))
//        {
//            recieved.getL().setTankimage(new Texture("coalition.png"));
//            recieved.getL().getCurrentweapon().setWeaponimg(new Texture("fireball.png"));
//        }
//        if (recieved.getL().getName().equals("Toxic"))
//        {
//            recieved.getL().setTankimage(new Texture("toxic.png"));
//            recieved.getL().getCurrentweapon().setWeaponimg(new Texture("fireball.png"));
//        }
//        if (recieved.getSelectedt2().getT2().getName().equals("Frost Tank"))
//        {
////            recieved.getR().setTankimage(new Texture("Frosttankinv.png"));
////            recieved.getR().getCurrentweapon().setWeaponimg(new Texture("fireball.png"));
//            Weapon w = new Weapon();
//            w.setAssociatedtank(recieved.getSelectedt2().getT2());
//            recieved.getSelectedt2().getT2().setCurrentweapon(w);
//            recieved.getSelectedt2().getT2().setTankimage(new Texture("Frosttankinv.png"));
//            recieved.getSelectedt2().getT2().getCurrentweapon().setWeaponimg(new Texture("fireball.png"));
//        }
//        if (recieved.getR().getName().equals("Coalition"))
//        {
//            recieved.getR().setTankimage(new Texture("coalitioninv.png"));
//            recieved.getR().getCurrentweapon().setWeaponimg(new Texture("fireball.png"));
//        }
//        if (recieved.getR().getName().equals("Toxic"))
//        {
//            recieved.getR().setTankimage(new Texture("toxicinv.png"));
//            recieved.getR().getCurrentweapon().setWeaponimg(new Texture("fireball.png"));
//        }
//
//
//
//        game.setScreen(recieved);
//    }
    public HomePageGUI(tankstars game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1238, 500);
        game.batch = new SpriteBatch();
        homeimg = new Texture("homescreen.png");

        game.themesound = Gdx.audio.newMusic(Gdx.files.internal("mainmusic.mp3"));

//        game.themesound.setLooping(true);
//        game.themesound.play();

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
        game.batch.draw(homeimg, 0, 0);
        game.batch.end();

        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() >= 705 && Gdx.input.getX() <= 1170 && Gdx.input.getY() >= 62 && Gdx.input.getY() <= 162) {
                ChooseTank1GUI cht1 = new ChooseTank1GUI(game);
                game.setScreen(cht1);
            }
        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
//            if(Gdx.input.getX()>=705 && Gdx.input.getX()<=1170 && Gdx.input.getY()>=55 && Gdx.input.getY()<455){
//                exit(1);
//            try {
//                deserialize();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//            }

        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() >= 705 && Gdx.input.getX() <= 1170 && Gdx.input.getY() >= 355 && Gdx.input.getY() < 455) {
                exit(1);
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
