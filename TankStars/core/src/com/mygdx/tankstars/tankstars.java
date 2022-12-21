package com.mygdx.tankstars;

import java.io.Serializable;
import java.util.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankstars.screens.*;


public class tankstars extends Game implements Serializable {

	transient public SpriteBatch batch;

	private ChooseTank2GUI choosetank2gui;

	private LoadGame loadgame;

	public GameScreenGUI gamesc;
	public HomePageGUI homepg;

	public SpriteBatch getBatch() {
		return batch;
	}

	public LoadGame getLoadgame() {
		return loadgame;
	}

	public void setLoadgame(LoadGame loadgame) {
		this.loadgame = loadgame;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public Music getThemesound() {
		return themesound;
	}

	public void setThemesound(Music themesound) {
		this.themesound = themesound;
	}

	transient public Music themesound;

	public GameScreenGUI getGamesc() {
		return gamesc;
	}

	public HomePageGUI getHomepg() {
		return homepg;
	}

	public void setHomepg(HomePageGUI homepg) {
		this.homepg = homepg;
	}

	public ChooseTank2GUI getChoosetank2gui() {
		return choosetank2gui;
	}

	public void setChoosetank2gui(ChooseTank2GUI choosetank2gui) {
		this.choosetank2gui = choosetank2gui;
	}

	public void setGamesc(GameScreenGUI gamesc) {
		this.gamesc = gamesc;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		homepg = new HomePageGUI((this));
		loadgame = new LoadGame(this);
		setScreen(homepg);
	}

	@Override
	public void render () {
		super.render();
	}
	@Override
	public void dispose(){
		batch.dispose();
	}
}
