package com.mygdx.tankstars;

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


public class tankstars extends Game {

	public SpriteBatch batch;

	public GameScreenGUI gamesc;

	public Music themesound;


	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new HomePageGUI(this));
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
