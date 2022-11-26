package com.mygdx.tankstars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankstars.tankstars;

import java.util.ArrayList;



    class Ground{

        public static int generateY(float x)
        {
            x=x+55;
            double y = 4.412 * Math.pow((x/247.6),0.5) * Math.pow(((x/247.6) -1),2) * ((x/247.6)-5) * ((x/247.6)-6);
            return (int)y;
        }
    }

    class Weapon{
    }


    class Tank {
        private tankstars game;
        private String Name;
        private int health;
        private int currentangle;
        private int power;
        private int fuel;
        private Weapon currentweapon;
        private ArrayList<Weapon> arsenal;

        private Rectangle tankrectangle;

        public void setTankrectangle(Rectangle tankrectangle) {
            this.tankrectangle = tankrectangle;
        }

        public Rectangle getTankrectangle() {
            return tankrectangle;
        }
    }
    public class GameScreenGUI implements Screen {

        Texture gameimage;
        private final tankstars game;
        private OrthographicCamera camera;
        public PauseMenuGUI pausesc;

        private Tank l;

        private Tank r;

        private Texture ltank;
        private Texture rtank;
        private Texture groundimg;
        float Xidx,Yidx;
        float Xtk,Ytk;


    public GameScreenGUI(tankstars game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1238, 500);
        game.batch = new SpriteBatch();
        gameimage = new Texture("background.png");
        groundimg = new Texture("ground.png");
        ltank = new Texture("Frosttank.png");
        rtank = new Texture("toxic.png");



        l = new Tank();
        Rectangle tank1 = new Rectangle();
        tank1.x = 245;
        tank1.y = Ground.generateY(245);
        tank1.width = 125;
        tank1.height = 75;
        l.setTankrectangle(tank1);
        Xidx=245;
        Yidx=Ground.generateY(Xidx);
        Xtk=800;
        Ytk=Ground.generateY(800);

        r = new Tank();
        Rectangle tank2 = new Rectangle();
        tank2.x = 800;
        tank2.y = Ground.generateY(800);
        tank2.width = 125;
        tank2.height = 75;
        r.setTankrectangle(tank2);
        Xtk=800;
        Ytk=Ground.generateY(Xtk);


    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.6f, 0.46f, 0.32f, 0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
//            System.out.println("hello pressed Left");
            Xidx=Xidx-5;
            Yidx=Ground.generateY(Xidx);
            l.getTankrectangle().width -= 5;
            l.getTankrectangle().height = Ground.generateY(l.getTankrectangle().width);
//            System.out.println(l.getTankrectangle().width);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.A))
        {
            Xtk-=5;
            Ytk=Ground.generateY(Xtk);
            r.getTankrectangle().width -= 5;
            r.getTankrectangle().height = Ground.generateY(r.getTankrectangle().width);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            System.out.println("hello pressed Left");
            Xidx = Xidx + 5;
            Yidx = Ground.generateY(Xidx);
            l.getTankrectangle().width += 5;
            l.getTankrectangle().height = Ground.generateY(l.getTankrectangle().width);
//            System.out.println(l.getTankrectangle().width);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.D))
        {
            Xtk+=5;
            Ytk=Ground.generateY(Xtk);
            r.getTankrectangle().width += 5;
            r.getTankrectangle().height = Ground.generateY(r.getTankrectangle().width);
        }



        game.batch.begin();
        game.batch.draw(gameimage,0,0);
        game.batch.draw(ltank,Xidx,Yidx);
        game.batch.draw(groundimg,0,0);
        game.batch.draw(rtank,Xtk,Ytk);
        game.batch.end();

        if(Gdx.input.justTouched()){
            if (Gdx.input.getX()>=0 && Gdx.input.getX()<=80 && Gdx.input.getY()>=15 && Gdx.input.getY()<=67)
                pausesc = new PauseMenuGUI(game);
                game.setScreen(pausesc);
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
