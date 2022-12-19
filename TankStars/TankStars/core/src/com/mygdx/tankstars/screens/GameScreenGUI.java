package com.mygdx.tankstars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankstars.tankstars;

import java.util.*;



class Ground{

    public static int generateY(float x)
    {
        x=x+55;
        double y = 4.412 * Math.pow((x/247.6),0.5) * Math.pow(((x/247.6) -1),2) * ((x/247.6)-5) * ((x/247.6)-6);
        return (int)y;
    }
}

class Weapon implements Collision{
    private Texture weaponimg = new Texture("fireball.png");
    private String name;
    private double maxvelocity = 20;
    private int damage;
    private int damageradius;

    private Tank associatedtank;

    public Texture getWeaponimg() {
        return weaponimg;
    }

    public Tank getAssociatedtank() {
        return associatedtank;
    }

    public void setAssociatedtank(Tank associatedtank) {
        this.associatedtank = associatedtank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxvelocity() {
        return maxvelocity;
    }

    public void setMaxvelocity(double maxvelocity) {
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
    public int generateTrajectoryY(int x){
        //x=x+55;
//        System.out.println("Power: "+associatedtank.getPower());
//        System.out.println("Maxvelocity: "+maxvelocity);
//        System.out.println("Angle: "+associatedtank.getCurrentangle());
        double cv = (associatedtank.getPower()*maxvelocity)/40;
        int y= associatedtank.getY()+ (int) (((x-associatedtank.getX())*Math.tan(0.01745*associatedtank.getCurrentangle()))-((0.1*(x-associatedtank.getX())*(x-associatedtank.getX()))/(2*cv*cv*Math.cos(0.01745*associatedtank.getCurrentangle()*Math.cos(0.01745*associatedtank.getCurrentangle())))));
        //return y - 250;
        return y;
    }
    public int generateTrajectoryY2(int x){
        x=x+110;
        double cv = (associatedtank.getPower()*maxvelocity)/40;
        int y= associatedtank.getY()+ (int) (((x-associatedtank.getX())*Math.tan(0.01745*associatedtank.getCurrentangle()))-((0.1*(x-associatedtank.getX())*(x-associatedtank.getX()))/(2*cv*cv*Math.cos(0.01745*associatedtank.getCurrentangle()*Math.cos(0.01745*associatedtank.getCurrentangle())))));
        //return y - 250;
        return y+30;
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
    private int power=20;
    private int fuel = 100;
    private Weapon currentweapon;
    private ArrayList<Weapon> arsenal;
    private Rectangle tankrectangle;


    public Tank(String name,tankstars game) {
        Name = name;
        this.game = game;
    }
    public void fire(int turn) throws InterruptedException {
        int i = this.getX();
        if(turn==1){
            while (i<1240)
            {
//                if()
//                {
//                    break;
//                }

                System.out.println(this.getCurrentweapon().generateTrajectoryY(i));//13
                System.out.println(Ground.generateY(i-55));//27
                System.out.println(this.getX());//92
                System.out.println(this.getY());//13

                if (this.getCurrentweapon().generateTrajectoryY(i) < Ground.generateY(i-55))
                {
                    System.out.println("Lawda mera lawda");
                    break;
                }
                game.batch.draw(currentweapon.getWeaponimg(),i,currentweapon.generateTrajectoryY(i));
                i+=20;

            }

        }
        else{
           while(i>0){
               if (this.getCurrentweapon().generateTrajectoryY(i) < Ground.generateY(i-55))
               {
                   break;
               }
                game.batch.draw(currentweapon.getWeaponimg(),i,currentweapon.generateTrajectoryY2(i));
                i-=20;
                //Thread.sleep(1000);
            }
        }
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


    public void aim1()
    {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
            currentangle+=1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
        {
            currentangle-=1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
        {
            if(power<40){
                power++;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
        {
            if(power>0){
                power--;
            }
        }
    }
    public void aim2()
    {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
            currentangle-=1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
        {
            currentangle+=1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
        {
            if(power<40){
                power+=1;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
        {
            if(power>0){
                power-=1;
            }
        }
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

    private Texture gameimage;
    private Texture firebutton;
    private Texture dot;

    private int turn=1;

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
    private ShapeRenderer shape;
    private int i=0;

    public GameScreenGUI(tankstars game) {

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1238, 500);
        game.batch = new SpriteBatch();
        shape = new ShapeRenderer();
        gameimage = new Texture("background.png");
        groundimg = new Texture("groundgametexture.png");
        ltank = new Texture("Frosttank.png");
        rtank = new Texture("toxic.png");
        firebutton = new Texture("firebutton.png");
        dot = new Texture("dot.png");
        font = new BitmapFont();
        font.getData().setScale(1.5f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        l = new Tank("frost",game);
        Rectangle tank1 = new Rectangle();
        tank1.x = 245;


        tank1.y = Ground.generateY(245);
        tank1.width = 125;
        tank1.height = 75;
        l.setTankrectangle(tank1);
        l.setX(245);
        l.setY(Ground.generateY(l.getX()));
        l.setCurrentangle(45);
        Weapon w1 = new Weapon();
        w1.setAssociatedtank(l);
        l.setCurrentweapon(w1);


        r = new Tank("toxic",game);
        Rectangle tank2 = new Rectangle();
        tank2.x = 800;
        tank2.y = Ground.generateY(800);
        tank2.width = 125;
        tank2.height = 75;
        r.setTankrectangle(tank2);
        r.setX(800);
        r.setY(Ground.generateY(r.getX()));
        r.setCurrentangle(10);
        Weapon w2 = new Weapon();
        w2.setAssociatedtank(r);
        r.setCurrentweapon(w2);

        gameelements = new ArrayList<GameElements>();
        gameelements.add(l);
        gameelements.add(r);

        turn = 1;

    }
    //public void conductGame(){

    //}


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


            game.batch.begin();
            game.batch.draw(gameimage, 0, 0);
            game.batch.draw(ltank, l.getX(), l.getY());
            game.batch.draw(groundimg, 0, 0);
            game.batch.draw(rtank, r.getX(), r.getY());
//        game.batch.draw(dot,l.getX()+10,100);
//
            game.batch.draw(firebutton, 950, 20);
            //game.batch.end();

            if(turn == 1){
                l.aim1();

                game.batch.draw(dot,l.getX()+110,l.getCurrentweapon().generateTrajectoryY(l.getX()+110));
                game.batch.draw(dot,l.getX()+120,l.getCurrentweapon().generateTrajectoryY(l.getX()+120));
                game.batch.draw(dot,l.getX()+130,l.getCurrentweapon().generateTrajectoryY(l.getX()+130));
                game.batch.draw(dot,l.getX()+140,l.getCurrentweapon().generateTrajectoryY(l.getX()+140));
                game.batch.draw(dot,l.getX()+150,l.getCurrentweapon().generateTrajectoryY(l.getX()+150));
                game.batch.draw(dot,l.getX()+160,l.getCurrentweapon().generateTrajectoryY(l.getX()+160));
                game.batch.draw(dot,l.getX()+170,l.getCurrentweapon().generateTrajectoryY(l.getX()+170));
                game.batch.draw(dot,l.getX()+180,l.getCurrentweapon().generateTrajectoryY(l.getX()+180));
                game.batch.draw(dot,l.getX()+190,l.getCurrentweapon().generateTrajectoryY(l.getX()+190));

                if (Gdx.input.isKeyPressed(Input.Keys.A)) {

                    if (l.getX() > -20 && l.getFuel() > 0) {
                        l.setX(l.getX() - 3);
                        l.setFuel(l.getFuel() - 1);
                        l.setY(Ground.generateY(l.getX()));
                        l.getTankrectangle().width -= 3;
                        l.getTankrectangle().height = Ground.generateY(l.getTankrectangle().width);
                    }


                }

                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    if (l.getX() < 1130 && l.getFuel() > 0) {
                        l.setX(l.getX() + 3);
                        l.setFuel(l.getFuel() - 1);
                        l.setY(Ground.generateY(l.getX()));
                        l.getTankrectangle().width += 3;
                        l.getTankrectangle().height = Ground.generateY(l.getTankrectangle().width);

                    }
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

                    try {
                        l.fire(turn);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    r.setFuel(100);
                    l.setFuel(100);
                    l.setCurrentangle(45);
                    r.setCurrentangle(10);
                    l.setPower(20);
                    r.setPower(20);
                    if(turn==1){
                        turn = 2;
                    }else{
                        turn = 1;
                    }
                }
            }
            else if(turn == 2){
                r.aim2();
                game.batch.draw(dot,r.getX()-20,r.getCurrentweapon().generateTrajectoryY2(r.getX()-20));
                game.batch.draw(dot,r.getX()-30,r.getCurrentweapon().generateTrajectoryY2(r.getX()-30));
                game.batch.draw(dot,r.getX()-40,r.getCurrentweapon().generateTrajectoryY2(r.getX()-40));
                game.batch.draw(dot,r.getX()-50,r.getCurrentweapon().generateTrajectoryY2(r.getX()-50));
                game.batch.draw(dot,r.getX()-60,r.getCurrentweapon().generateTrajectoryY2(r.getX()-60));
                game.batch.draw(dot,r.getX()-70,r.getCurrentweapon().generateTrajectoryY2(r.getX()-70));
                game.batch.draw(dot,r.getX()-80,r.getCurrentweapon().generateTrajectoryY2(r.getX()-80));
                game.batch.draw(dot,r.getX()-90,r.getCurrentweapon().generateTrajectoryY2(r.getX()-90));
                game.batch.draw(dot,r.getX()-100,r.getCurrentweapon().generateTrajectoryY2(r.getX()-100));

                if (Gdx.input.isKeyPressed(Input.Keys.A)) {

                    if (r.getX() > -20 && r.getFuel() > 0) {
                        r.setX(r.getX() - 3);
                        r.setFuel(r.getFuel() - 1);
                        r.setY(Ground.generateY(r.getX()));
                        r.getTankrectangle().width -= 3;
                        r.getTankrectangle().height = Ground.generateY(r.getTankrectangle().width);
                    }


                }
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {

                    if (r.getX() < 1130 && r.getFuel() > 0) {
                        r.setX(r.getX() + 3);
                        r.setFuel(r.getFuel() - 1);
                        r.setY(Ground.generateY(r.getX()));
                        r.getTankrectangle().width += 3;
                        r.getTankrectangle().height = Ground.generateY(r.getTankrectangle().width);
                    }
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

                    try {
                        r.fire(turn);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    r.setFuel(100);
                    l.setFuel(100);
                    l.setCurrentangle(45);
                    r.setCurrentangle(10);
                    l.setPower(20);
                    r.setPower(20);
                    if(turn==1){
                        turn = 2;
                    }else{
                        turn = 1;
                    }
                }
            }

            if (Gdx.input.justTouched()) {
                if (Gdx.input.getX() >= 0 && Gdx.input.getX() <= 80 && Gdx.input.getY() >= 15 && Gdx.input.getY() <= 67)
                    pausesc = new PauseMenuGUI(game);
                game.setScreen(pausesc);
            }
        game.batch.end();
        //shape.end();
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
