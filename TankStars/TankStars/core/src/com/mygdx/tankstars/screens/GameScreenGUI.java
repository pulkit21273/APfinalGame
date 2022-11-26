package com.mygdx.tankstars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

class Weapon implements Collision{
    private String name;
    private int maxvelocity;
    private int damage;
    private int damageradius;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxvelocity() {
        return maxvelocity;
    }

    public void setMaxvelocity(int maxvelocity) {
        this.maxvelocity = maxvelocity;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamageradius() {
        return damageradius;
    }

    public void setDamageradius(int damageradius) {
        this.damageradius = damageradius;
    }

    @Override
    public boolean hasCollided(GameElements e) {
        //code elided
        return false;
    }
}

interface Collision{
    public boolean hasCollided(GameElements g);
}

class Tank extends GameElements{
    private tankstars game;
    private String Name;
    private int health;
    private int currentangle;
    private int power;
    private int fuel = 100;
    private Weapon currentweapon;
    private ArrayList<Weapon> arsenal;
    private Rectangle tankrectangle;
    

    public Tank(String name) {
        Name = name;

    }
    public void setTankrectangle(Rectangle tankrectangle) {
        this.tankrectangle = tankrectangle;
    }

    public tankstars getGame() {
        return game;
    }

    public void setGame(tankstars game) {
        this.game = game;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCurrentangle() {
        return currentangle;
    }

    public void setCurrentangle(int currentangle) {
        this.currentangle = currentangle;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public Weapon getCurrentweapon() {
        return currentweapon;
    }

    public void setCurrentweapon(Weapon currentweapon) {
        this.currentweapon = currentweapon;
    }

    public ArrayList<Weapon> getArsenal() {
        return arsenal;
    }

    public void setArsenal(ArrayList<Weapon> arsenal) {
        this.arsenal = arsenal;
    }

    public Rectangle getTankrectangle() {
        return tankrectangle;
    }
}

abstract class GameElements{
    int X;
    int Y;

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }
}
public class GameScreenGUI implements Screen {

    Texture gameimage;

    private int turn;

    private ChooseTank1GUI selectedt1;
    private ChooseTank2GUI selectedt2;

    private ArrayList<GameElements> gameelements;
    private BitmapFont font;
    private final tankstars game;
    private OrthographicCamera camera;
    public PauseMenuGUI pausesc;

    private Tank l;

    private Tank r;

    private Texture ltank;
    private Texture rtank;
    private Texture groundimg;

    public GameScreenGUI(tankstars game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1238, 500);
        game.batch = new SpriteBatch();
        gameimage = new Texture("background.png");
        groundimg = new Texture("ground.png");
        ltank = new Texture("Frosttank.png");
        rtank = new Texture("toxic.png");
        font = new BitmapFont();
        font.getData().setScale(1.5f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        l = new Tank("frost");
        Rectangle tank1 = new Rectangle();
        tank1.x = 245;

        tank1.y = Ground.generateY(245);
        tank1.width = 125;
        tank1.height = 75;
        l.setTankrectangle(tank1);
        l.setX(245);
        l.setY(Ground.generateY(l.getX()));


        r = new Tank("toxic");
        Rectangle tank2 = new Rectangle();
        tank2.x = 800;
        tank2.y = Ground.generateY(800);
        tank2.width = 125;
        tank2.height = 75;
        r.setTankrectangle(tank2);
        r.setX(800);
        r.setY(Ground.generateY(r.getX()));

        gameelements = new ArrayList<GameElements>();
        gameelements.add(l);
        gameelements.add(r);

    }
    public void addGameElement(GameElements g){
        gameelements.add(g);
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
//            if(l.getFuel()<=0){
//                font.draw(game.batch,"Empty Fuel",390,480);
//            }

            if(l.getX()>-20 && l.getFuel()>0){
                l.setX(l.getX()-3);
                l.setFuel(l.getFuel()-1);
                l.setY(Ground.generateY(l.getX()));
                l.getTankrectangle().width -= 3;
                l.getTankrectangle().height = Ground.generateY(l.getTankrectangle().width);
            }

//            System.out.println(l.getTankrectangle().width);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.A))
        {
//            if(r.getFuel()<=0){
//                font.draw(game.batch,"Empty Fuel",390,480);
//            }

            if(r.getX()>-20 && r.getFuel()>0){
                r.setX(r.getX()-3);
                r.setFuel(r.getFuel()-1);
                r.setY(Ground.generateY(r.getX()));
                r.getTankrectangle().width -= 3;
                r.getTankrectangle().height = Ground.generateY(r.getTankrectangle().width);
            }


        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            if(l.getFuel()<=0){
//                font.draw(game.batch,"Empty Fuel",390,480);
//            }
//            System.out.println("hello pressed Left");
            if(l.getX()<1130 && l.getFuel()>0){
                l.setX(l.getX()+3);
                l.setFuel(l.getFuel()-1);
                l.setY(Ground.generateY(l.getX()));
                l.getTankrectangle().width += 3;
                l.getTankrectangle().height = Ground.generateY(l.getTankrectangle().width);
//            System.out.println(l.getTankrectangle().width);
            }


        }
        if (Gdx.input.isKeyPressed(Input.Keys.D))
        {
//            if(r.getFuel()<=0){
//                font.draw(game.batch,"Empty Fuel",390,480);
//            }
            if(r.getX()<1130 && r.getFuel()>0){
                r.setX(r.getX()+3);
                r.setFuel(r.getFuel()-1);
                r.setY(Ground.generateY(r.getX()));
                r.getTankrectangle().width += 3;
                r.getTankrectangle().height = Ground.generateY(r.getTankrectangle().width);
            }

        }



        game.batch.begin();
        game.batch.draw(gameimage,0,0);
        game.batch.draw(ltank,l.getX(),l.getY());
        game.batch.draw(groundimg,0,0);
        game.batch.draw(rtank,r.getX(),r.getY());
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
