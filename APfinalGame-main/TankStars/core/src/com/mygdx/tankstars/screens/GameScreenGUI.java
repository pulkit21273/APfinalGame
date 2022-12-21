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

import java.io.Serial;
import java.io.Serializable;
import java.util.*;



class Ground implements Serializable{

    private static Ground gen = null;

    public static Ground getInstance(){
        if(gen == null){
            gen = new Ground();
        }
        return gen;
    }
    private Ground(){}
    public static int generateY(float x)
    {
        x=x+55;
        double y = 4.412 * Math.pow((x/247.6),0.5) * Math.pow(((x/247.6) -1),2) * ((x/247.6)-5) * ((x/247.6)-6);
        return (int)y;
    }
}
class Airdrop extends GameElements implements Collision,Serializable{

    public Texture getDropimg() {
        return dropimg;
    }

    public void setDropimg(Texture dropimg) {
        this.dropimg = dropimg;
    }

    public Weapon getSpecialweapon() {
        return specialweapon;
    }

    public void setSpecialweapon(Weapon specialweapon) {
        this.specialweapon = specialweapon;
    }

    private Texture dropimg;
    private Weapon specialweapon;

    public Airdrop()
    {
        specialweapon = new Weapon();
        specialweapon.setWeaponimg(new Texture("specialball.png"));
        specialweapon.setDamage(40);
    }
    @Override
    public boolean hasCollided(GameElements T, int dropx, int turn) {

        Tank t = (Tank) T;

            if (dropx == t.getX() ) {
                System.out.println("Chalgya DROP!");
                specialweapon.setAssociatedtank(t);
                t.setCurrentweapon(specialweapon);
                return true;
            }

        return false;
    }

}
class Weapon implements Collision,Serializable{
    transient private Texture weaponimg = new Texture("fireball.png");
    private String name;
    private double maxvelocity = 20;
    private int damage=20;

    public void setWeaponimg(Texture weaponimg) {
        this.weaponimg = weaponimg;
    }

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
    public boolean hasCollided(GameElements other, int weaponx ,int turn) {
        Tank othertank = (Tank)other;
        if(turn==1){
            if(weaponx>=othertank.getX() && weaponx<=othertank.getX()+125 && generateTrajectoryY(weaponx)>=othertank.getY() && generateTrajectoryY(weaponx)<=othertank.getY()+75){
                othertank.setX(othertank.getX()+40);
                othertank.setY(Ground.generateY(othertank.getX()));
                if (othertank.getHealth()>0)
                {
                    othertank.setHealth(othertank.getHealth()-damage);
                }


                return true;
            }
        }else{
            if(weaponx>=othertank.getX()-30 && weaponx<=othertank.getX()+150 && generateTrajectoryY2(weaponx)>=othertank.getY() && generateTrajectoryY2(weaponx)<=othertank.getY()+75){
                othertank.setX(othertank.getX()-40);
                othertank.setY(Ground.generateY(othertank.getX()));
                if (othertank.getHealth()>0)
                {
                    othertank.setHealth(othertank.getHealth()-damage);
                }

                return true;
            }
        }
        return false;
    }
    public int generateTrajectoryY(int x){

        double cv = (associatedtank.getPower()*maxvelocity)/40;
        int y= associatedtank.getY()+ (int) (((x-associatedtank.getX())*Math.tan(0.01745*associatedtank.getCurrentangle()))-((0.1*(x-associatedtank.getX())*(x-associatedtank.getX()))/(2*cv*cv*Math.cos(0.01745*associatedtank.getCurrentangle()*Math.cos(0.01745*associatedtank.getCurrentangle())))));
        return y;
    }
    public int generateTrajectoryY2(int x){
        x=x+110;
        double cv = (associatedtank.getPower()*maxvelocity)/40;
        int y= associatedtank.getY()+ (int) (((x-associatedtank.getX())*Math.tan(0.01745*associatedtank.getCurrentangle()))-((0.1*(x-associatedtank.getX())*(x-associatedtank.getX()))/(2*cv*cv*Math.cos(0.01745*associatedtank.getCurrentangle()*Math.cos(0.01745*associatedtank.getCurrentangle())))));
        //return y - 250;
        return y+50;
    }
}

interface Collision{
    public boolean hasCollided(GameElements g,int x,int y);
}

class Tank extends GameElements implements Serializable{
    private tankstars game;
    transient private Texture tankimage;

    public Texture getTankimage() {
        return tankimage;
    }

    public void setTankimage(Texture tankimage) {
        this.tankimage = tankimage;
    }

    private String Name;
    private int health=100;
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
    public void fire(int turn,Tank t) throws InterruptedException {
        int i = this.getX();
        if(turn==1){
            while (i<1240)
            {

                if (this.getCurrentweapon().generateTrajectoryY(i) < Ground.generateY(i)-100||this.getCurrentweapon().hasCollided(t,i,turn))
                {
                    break;
                }
                game.batch.draw(currentweapon.getWeaponimg(),i,currentweapon.generateTrajectoryY(i));
                for (long j =0;j<1000000L;j++)
                {

                }
                i+=20;
            }

        }
        else{
           while(i>0){
               if (this.getCurrentweapon().hasCollided(t,i,turn))
               {

                   break;
               }
                game.batch.draw(currentweapon.getWeaponimg(),i,currentweapon.generateTrajectoryY2(i));

               for (long j =0;j<1000000L;j++)
               {

               }
                i-=20;
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

abstract class GameElements implements Serializable{
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
public class GameScreenGUI implements Screen, Serializable {

    transient private Texture gameimage;
    transient private Texture firebutton;
    transient private Texture dot;

    private int turn = 1;

    private ChooseTank1GUI selectedt1;
    private ChooseTank2GUI selectedt2;

    private ArrayList<GameElements> gameelements;
    transient private BitmapFont font;
    private final tankstars game;
    transient private OrthographicCamera camera;
    public PauseMenuGUI pausesc;

    private Tank l;

    private Tank r;

    transient private Texture ltank;
    transient private Texture rtank;
    transient private Texture groundimg;
    transient private Texture tank1wins;
    transient private Texture tank2wins;

    private int i = 0;
    private int flag = 0;
    private boolean dropflag;

    public Texture getGameimage() {
        return gameimage;
    }

    public void setGameimage(Texture gameimage) {
        this.gameimage = gameimage;
    }

    public Texture getFirebutton() {
        return firebutton;
    }

    public void setFirebutton(Texture firebutton) {
        this.firebutton = firebutton;
    }

    public Texture getDot() {
        return dot;
    }

    public void setDot(Texture dot) {
        this.dot = dot;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public ChooseTank1GUI getSelectedt1() {
        return selectedt1;
    }

    public void setSelectedt1(ChooseTank1GUI selectedt1) {
        this.selectedt1 = selectedt1;
    }

    public ChooseTank2GUI getSelectedt2() {
        return selectedt2;
    }

    public void setSelectedt2(ChooseTank2GUI selectedt2) {
        this.selectedt2 = selectedt2;
    }

    public ArrayList<GameElements> getGameelements() {
        return gameelements;
    }

    public void setGameelements(ArrayList<GameElements> gameelements) {
        this.gameelements = gameelements;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
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

    public PauseMenuGUI getPausesc() {
        return pausesc;
    }

    public void setPausesc(PauseMenuGUI pausesc) {
        this.pausesc = pausesc;
    }

    public Tank getL() {
        return l;
    }

    public void setL(Tank l) {
        this.l = l;
    }

    public Tank getR() {
        return r;
    }

    public void setR(Tank r) {
        this.r = r;
    }

    public Texture getLtank() {
        return ltank;
    }

    public void setLtank(Texture ltank) {
        this.ltank = ltank;
    }

    public Texture getRtank() {
        return rtank;
    }

    public void setRtank(Texture rtank) {
        this.rtank = rtank;
    }

    public Texture getGroundimg() {
        return groundimg;
    }

    public void setGroundimg(Texture groundimg) {
        this.groundimg = groundimg;
    }

    public Texture getTank1wins() {
        return tank1wins;
    }

    public void setTank1wins(Texture tank1wins) {
        this.tank1wins = tank1wins;
    }

    public Texture getTank2wins() {
        return tank2wins;
    }

    public void setTank2wins(Texture tank2wins) {
        this.tank2wins = tank2wins;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public GameScreenGUI(tankstars game, ChooseTank2GUI s2) {

        System.out.println("I am in Gamescreen");
        game.setGamesc(this);
        game.setChoosetank2gui(s2);
        this.game = game;
        this.selectedt2 = s2;
        this.selectedt1 = s2.getS1();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1238, 500);
        game.batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(1.5f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        gameimage = new Texture("background.png");
        groundimg = new Texture("groundgametexture.png");
        tank1wins = new Texture("win1.png");
        tank2wins = new Texture("win2.png");


        firebutton = new Texture("firebutton.png");
        dot = new Texture("dot.png");
        font = new BitmapFont();
        font.getData().setScale(1.5f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        l = selectedt1.getT1();
        ltank = selectedt1.getT1().getTankimage();
        r = selectedt2.getT2();
        rtank = selectedt2.getT2().getTankimage();
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


        r = new Tank("toxic", game);
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

    public void addGameElement(GameElements g) {
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

        if (r.getHealth()!=0 && l.getHealth()!=0)
        {
            game.batch.draw(gameimage, 0, 0);
            game.batch.draw(ltank, l.getX(), l.getY());
            game.batch.draw(groundimg, 0, 0);
            game.batch.draw(rtank, r.getX(), r.getY());

            font.draw(game.batch, "Tank 1", 390, 480);
            font.draw(game.batch, l.getHealth() + "", 390, 450);
            font.draw(game.batch, "Tank 2", 832, 480);
            font.draw(game.batch, r.getHealth() + "", 832, 450);

            font.draw(game.batch, "fuel: "+l.getFuel(), l.getX()+15, l.getY()+100);
            font.draw(game.batch, "fuel: "+r.getFuel(), r.getX()+15, r.getY()+100);
            game.batch.draw(firebutton, 950, 20);
        }

        if (r.getHealth()<=0)
        {

            game.batch.end();
            Tank1Wins t = new Tank1Wins(game);
            game.setScreen(t);
            game.batch.begin();
        }
        if (l.getHealth()<=0)
        {

            game.batch.end();
            Tank2Wins t = new Tank2Wins(game);
            game.setScreen(t);
            game.batch.begin();
        }

        if (turn == 1) {
            l.aim1();
            if (r.getHealth()!=0 && l.getHealth()!=0) {
                game.batch.draw(dot, l.getX() + 110, l.getCurrentweapon().generateTrajectoryY(l.getX() + 110));
                game.batch.draw(dot, l.getX() + 120, l.getCurrentweapon().generateTrajectoryY(l.getX() + 120));
                game.batch.draw(dot, l.getX() + 130, l.getCurrentweapon().generateTrajectoryY(l.getX() + 130));
                game.batch.draw(dot, l.getX() + 140, l.getCurrentweapon().generateTrajectoryY(l.getX() + 140));
                game.batch.draw(dot, l.getX() + 150, l.getCurrentweapon().generateTrajectoryY(l.getX() + 150));
                game.batch.draw(dot, l.getX() + 160, l.getCurrentweapon().generateTrajectoryY(l.getX() + 160));
                game.batch.draw(dot, l.getX() + 170, l.getCurrentweapon().generateTrajectoryY(l.getX() + 170));
                game.batch.draw(dot, l.getX() + 180, l.getCurrentweapon().generateTrajectoryY(l.getX() + 180));
                game.batch.draw(dot, l.getX() + 190, l.getCurrentweapon().generateTrajectoryY(l.getX() + 190));
            }
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
                    l.fire(turn, r);
                    if (r.getHealth() == 0) {
                        flag = 1;


                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                r.setFuel(100);
                l.setFuel(100);
                l.setCurrentangle(45);
                r.setCurrentangle(10);
                l.setPower(20);
                r.setPower(20);
                if (turn == 1) {
                    turn = 2;
                } else {
                    turn = 1;
                }
            }
        }
        else if (turn == 2) {
            r.aim2();
            if (r.getHealth()!=0 && l.getHealth()!=0) {
                game.batch.draw(dot, r.getX() - 20 + 60, r.getCurrentweapon().generateTrajectoryY2(r.getX() - 20 + 60));
                game.batch.draw(dot, r.getX() - 30 + 60, r.getCurrentweapon().generateTrajectoryY2(r.getX() - 30 + 60));
                game.batch.draw(dot, r.getX() - 40 + 60, r.getCurrentweapon().generateTrajectoryY2(r.getX() - 40 + 60));
                game.batch.draw(dot, r.getX() - 50 + 60, r.getCurrentweapon().generateTrajectoryY2(r.getX() - 50 + 60));
                game.batch.draw(dot, r.getX() - 60 + 60, r.getCurrentweapon().generateTrajectoryY2(r.getX() - 60 + 60));
                game.batch.draw(dot, r.getX() - 70 + 60, r.getCurrentweapon().generateTrajectoryY2(r.getX() - 70 + 60));
                game.batch.draw(dot, r.getX() - 80 + 60, r.getCurrentweapon().generateTrajectoryY2(r.getX() - 80 + 60));
                game.batch.draw(dot, r.getX() - 90 + 60, r.getCurrentweapon().generateTrajectoryY2(r.getX() - 90 + 60));
                game.batch.draw(dot, r.getX() - 100 + 60, r.getCurrentweapon().generateTrajectoryY2(r.getX() - 100 + 60));
            }
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
                    r.fire(turn, l);
                    if (l.getHealth() == 0) {
                        flag = 2;

                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                r.setFuel(100);
                l.setFuel(100);
                l.setCurrentangle(45);
                r.setCurrentangle(10);
                l.setPower(20);
                r.setPower(20);
                if (turn == 1) {
                    turn = 2;
                } else {
                    turn = 1;
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {

                game.batch.end();

                pausesc = new PauseMenuGUI(game);


            game.setScreen(pausesc);
            game.batch.begin();
        }
        Airdrop drop = new Airdrop();
        drop.setDropimg(new Texture("airdrop.png"));
        if (Gdx.input.isKeyJustPressed(Input.Keys.T))
        {
            dropflag = true;

        }
        if(dropflag){
            game.batch.draw(drop.getDropimg(),700,Ground.generateY(700));
        }
        if(drop.hasCollided(l,700,1)==true)
        {
            dropflag = false;
        }
        if(drop.hasCollided(r,700,2)==true)
        {
            dropflag = false;
        }

        game.batch.end();


    }

//}



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
